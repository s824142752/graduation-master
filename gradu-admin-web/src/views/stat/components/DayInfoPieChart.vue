<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { statDayInfoByEChart } from '@/api/stat'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chartData: [],
      chart: null
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      var echartData = []
      statDayInfoByEChart().then(response => {
        console.log(response.data.data.rows)
        for (let i = 0; i < response.data.data.rows.length; i++) {
          echartData[i] = response.data.data.rows[i]
        }
        this.chart = echarts.init(this.$el, 'macarons')

        this.chart.setOption({
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'center',
            data: []
          },
          series: [
            {
              name: '华航每日做义工人数比例',
              type: 'pie',
              // radius: [15, 95], // 圆环
              radius: '60%', // 实心圆
              center: ['50%', '38%'],
              data: echartData,
              animationEasing: 'cubicInOut',
              animationDuration: 2600
            }
          ]
        })
      })
    }
  }
}
</script>
