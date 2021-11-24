// pages/circle/list.js
const util = require('../../utils/util.js');
const api = require('../../config/api.js');

var that;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    status: 1,
    list:[],
    // 当前点击操作面板的索引，每条朋友圈一个面板
    showOperationPannelIndex:-1,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    that = this;
    this.getInformation(),
    console.log(this.data.list)
    
    // 重新渲染
    that.setData({
      list: that.data.list
    })
  },
  // 获取后台数据
  getInformation(e){
    let that = this;
    util.request(api.ListInfoByStatus, {
      status: that.data.status
    }).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          list: res.data.list,
        });
        console.log(that.data.list)
      }
    },
    );
  },
  //控制操作面板展示
  showOperationPannel(e){
    console.log("showOperationPannel",e);
    // 获取点击的索引
    var index = e.currentTarget.dataset.index;
    // 如果正在展示，则关闭
    if(that.data.showOperationPannelIndex == index){
      that.setData({
        // 索引从0开始
        showOperationPannelIndex:-1
      })
    }
    else{
      that.setData({
        // 设置成当前点击的索引
        showOperationPannelIndex:index
      })
    }
  },
})