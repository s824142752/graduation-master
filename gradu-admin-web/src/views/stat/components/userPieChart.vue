<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { statDiffCollegeUserByEcharts } from '@/api/stat'

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
      var echartNameData = []
      statDiffCollegeUserByEcharts().then(response => {
        for (let i = 0; i < response.data.data.rows.length; i++) {
          echartData[i] = response.data.data.rows[i]
        }
        for (let i = 0; i < response.data.data.rows.length; i++) {
          echartNameData[i] = response.data.data.rows[i].name
        }
        console.log(echartNameData)
        this.chart = echarts.init(this.$el, 'macarons')

        this.chart.setOption({
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            left: 'center',
            bottom: '10',
            data: echartNameData
            // data: ['计算机学院', '遥感学院']
          },
          series: [
            {
              name: '华航各二级学院参加义工人数比例',
              type: 'pie',
              roseType: 'radius',
              radius: [15, 95],
              center: ['50%', '38%'],
              data: echartData,
              // data: [
              //   { name: '计算机学院', value: 320 },
              //   { name: '遥感学院', value: 240 },
              //   { name: '外国语学院', value: 149 },
              //   { name: '文理学院', value: 100 },
              //   { name: '宇航学院', value: 59 }
              // ],
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
