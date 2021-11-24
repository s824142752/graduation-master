<template>
  <div class="dashboard-editor-container">
    <el-row :gutter="32">
      <el-col :span="9">
        <div class="chart-wrapper-pieCharts">
          <pie-chart />
        </div>
      </el-col>
      <el-col :span="15">
        <div class="chart-wrapper">
          <ve-histogram :extend="chartExtend" :data="chartData" :settings="chartSettings" />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { statDayInfo } from '@/api/stat'
import VeHistogram from 'v-charts/lib/histogram'
import PieChart from './components/DayInfoPieChart'

export default {
  components: { VeHistogram, PieChart },
  data() {
    return {
      chartData: {},
      chartSettings: {},
      chartExtend: {}
    }
  },
  created() {
    statDayInfo().then(response => {
      this.chartData = response.data.data
      this.chartSettings = {
        labelMap: {
          'num': '每日义工数量统计'
        }
      }
      this.chartExtend = {
        xAxis: { boundaryGap: true },
        series: {
          label: { show: true, position: 'top' }
        }
      }
    })
  }

}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;
  margin:auto;

  .chart-wrapper-pieCharts {
    background: #fff;
    padding: 85px 16px 0;
    margin:auto;
    height: 400px;
  }

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
