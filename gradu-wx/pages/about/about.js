const util = require('../../utils/util.js');
const api = require('../../config/api.js');

//获取应用实例
const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: '义工管理系统',
    address: 'https://github.com/gradu',
    latitude: '39.310000',
    longitude: '116.410000',
    phone: '0310-666-8888',
    qq: '824142752'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getAbout()
  },
  getAbout: function () {
    let that = this;
    util.request(api.AboutUrl).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          name: res.data.name,
          address: res.data.address,
          phone: res.data.phone,
          qq: res.data.qq,
          latitude: res.data.latitude,
          longitude: res.data.longitude
        });
      }
    });
  },
  showLocation: function (e) {
    var that = this
    wx.openLocation({
      latitude: parseFloat(that.data.latitude),
      longitude: parseFloat(that.data.longitude),
      name: that.data.name,
      address: that.data.address,
    })
  },
  callPhone: function (e) {
    var that = this
    wx.makePhoneCall({
      phoneNumber: that.data.phone,
    })
  }
})