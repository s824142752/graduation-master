<template>
  <div class="dashboard-editor-container">

    <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="peoples" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">学生人数</div>
            <count-to :start-val="0" :end-val="userTotal" :duration="2600" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('messages')">
          <div class="card-panel-icon-wrapper icon-message">
            <svg-icon icon-class="message" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">义工信息数量</div>
            <count-to :start-val="0" :end-val="processedTotal" :duration="3000" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <!--      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">-->
      <!--        <div class="card-panel" @click="handleSetLineChartData('purchases')">-->
      <!--          <div class="card-panel-icon-wrapper icon-money">-->
      <!--            <svg-icon icon-class="message" class-name="card-panel-icon" />-->
      <!--          </div>-->
      <!--          <div class="card-panel-description">-->
      <!--            <div class="card-panel-text">待处理信息数量</div>-->
      <!--            <count-to :start-val="0" :end-val="pendingTotal" :duration="3200" class="card-panel-num" />-->
      <!--          </div>-->
      <!--        </div>-->
      <!--      </el-col>-->
    </el-row>
    <div>
      <el-row :gutter="40" class="panel-group">
        <el-col :xs="24" :sm="24" :lg="24" class="card-panel-col-system">
          <div class="card-panel-system">
            <div class="card-panel-description-system">
              <div class="card-panel-text-sys">系统使用说明书</div>
              <h2>后端系统:</h2>
              <div class="card-panel-text-content1">
                &emsp;&emsp;1.安装 MySQL8.0 数据库「3306端口」 <br>
                &emsp;&emsp;3.导入数据库内容 <br>
                &emsp;&emsp;4.使用Maven安装所有需要的依赖 <br>
                &emsp;&emsp;5.启动后端服务 <br>
              </div>
              <h2>前端系统:</h2>
              <div class="card-panel-text-content2">
                后端管理页前端：<br>
                &emsp;&emsp;1.在开发者工具中导入gradu-admin-web为项目; <br>
                &emsp;&emsp;2.输入 npm install，等待依赖环境node_modules安装完成「npm环境自行安装、若是安装速度过慢，请自行查询使用cnpm的安装方式」; <br>
                &emsp;&emsp;3.在控制台输入 npm run dev，等待运行完成; <br>
                &emsp;&emsp;4.在浏览器输入 http://localhost:9527,进入登录页。 <br>
                &emsp;&emsp;
                <img src="../../assets/picture_doc/login.png" style="max-height: 288px" alt="">
                &emsp;<br>
                微信小程序端：<br>
                &emsp;&emsp;1.在微信开发者工具导入gradu-wx项目，配置添加自己的APPID; <br>
                &emsp;&emsp;2.项目配置,启用“不校验合法域名、web-view（业务域名）、TLS 版本以及 HTTPS 证书”; <br>
                &emsp;&emsp;3.点击“编译”，即可在微信开发工具预览效果。<br>
                &emsp;&emsp;
                <img src="../../assets/picture_doc/login-wx.png" style="max-height: 288px" alt="">
                &emsp;<br>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { info } from '@/api/dashboard'
import CountTo from 'vue-count-to'

export default {
  components: {
    CountTo
  },
  data() {
    return {
      userTotal: 0,
      processedTotal: 0,
      pendingTotal: 0
    }
  },
  created() {
    info().then(response => {
      this.userTotal = response.data.data.userTotal
      this.processedTotal = response.data.data.processedTotal
    })
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

.panel-group {
  margin-top: 18px;

  .card-panel-col{
    margin-bottom: 32px;
  }
  .card-panel-col-system{
    margin-bottom: 450px;
  }
  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);
    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-people {
         background: #40c9c6;
      }
      .icon-message {
        background: #36a3f7;
      }
      .icon-money {
        background: #f4516c;
      }
      .icon-shoppingCard {
        background: #34bfa3
      }
    }
    .icon-people {
      color: #40c9c6;
    }
    .icon-message {
      color: #36a3f7;
    }
    .icon-money {
      color: #f4516c;
    }
    .icon-shoppingCard {
      color: #34bfa3
    }
    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }
    .card-panel-icon {
      float: left;
      font-size: 48px;
    }
    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;
      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }
      .card-panel-num {
        font-size: 20px;
      }
    }
  }
  .card-panel-system {
    height: 1880px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);
    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-people {
        background: #40c9c6;
      }
      .icon-message {
        background: #36a3f7;
      }
      .icon-money {
        background: #f4516c;
      }
      .icon-shoppingCard {
        background: #34bfa3
      }
    }
    .icon-people {
      color: #40c9c6;
    }
    .icon-message {
      color: #36a3f7;
    }
    .icon-money {
      color: #f4516c;
    }
    .icon-shoppingCard {
      color: #34bfa3
    }
    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }
    .card-panel-icon {
      float: left;
      font-size: 48px;
    }
    .card-panel-description-system {
      float: left;
      font-weight: bold;
      margin: 26px;
      margin-left: 26px;
      .card-panel-text-sys {
        height: 30px;
        line-height: 18px;
        color: rgb(0, 0, 0);
        font-size: 36px;
        margin-bottom: 12px;
      }
      .card-panel-num {
        font-size: 20px;
      }
      .card-panel-text-title1 {
        height: 20px;
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 20px;
        margin-bottom: 12px;
      }
      .card-panel-text-title2 {
        height: 20px;
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 20px;
        margin-bottom: 12px;
      }
      .card-panel-text-content1 {
        height: 100px;
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 15px;
        margin-bottom: 12px;
      }
      .card-panel-text-content2 {
        height: 30px;
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 15px;
        margin-bottom: 12px;
      }
    }
  }
}
</style>
