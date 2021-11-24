<template>
  <div class="dashboard-editor-container">
    <el-row :gutter="32">
      <el-col :span="9">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
      <el-col :span="15">
        <div class="chart-wrapper">
          <ve-histogram
            :extend="chartCollegeExtend"
            :data="chartCollegeData"
            :settings="chartCollegeSettings"
          />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PieChart from './components/userPieChart'
import VeHistogram from 'v-charts/lib/histogram'
import { statDiffCollegeUser } from '@/api/stat'
import { themeColors } from '@/views/stat/globalTheme'

export default {
  components: { VeHistogram, PieChart },
  data() {
    return {
      /*
      * Students from different colleges
      * */
      chartCollegeData: {},
      chartCollegeSettings: {},
      chartCollegeExtend: {}
    }
  },

  created() {
    statDiffCollegeUser().then(response => {
      this.chartCollegeData = response.data.data
      console.log(response.data.data)
      console.log(this.chartCollegeData.rows)
      this.chartCollegeSettings = {
        labelMap: {
          'value': '学院人数统计'
        }
      }
      this.chartCollegeExtend = {
        xAxis: {
          axisLine: { show: true },
          boundaryGap: true
        },
        yAxis: {
          axisLine: { show: false }, // 是否显示y轴线条
          splitLine: { show: true }, // 纵坐标网格线设置，同理横坐标
          position: 'left' // 线条位置
        },
        series: {
          label: {
            show: true,
            position: 'top'
          }
        }
      }
      this.colors = themeColors
    })
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;
  margin:auto;

  .chart-wrapper {
    background: #fff;
    padding: 32px 16px 0;
    margin:auto;
    height: 400px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
