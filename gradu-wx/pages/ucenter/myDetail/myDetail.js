const util = require('../../../utils/util.js');
const api = require('../../../config/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    nickName: '宋永涛',
    username: '2017405A710',
    gender: '男',
    birthday: '1998-05-02',
    mobile: '17349866263',
    college: '北华航天工业学院',
    secondCollege: '计算机学院',
    major: '软件工程',
    majorDirection: '企业',
    classBanji: 'B17533'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  },
  getMyDetail: function () {

    //获取用户的登录信息
    if (app.globalData.hasLogin) {
      let userInfo = wx.getStorageSync('userInfo');
      this.setData({
        userInfo: userInfo,
        hasLogin: true
      });
      let that = this;
      util.request(api.MyDetail).then(function (res) {
        if (res.errno === 0) {
          that.setData({
            username: res.data.username,
          });
          console.log(res)
        }
      });
    }

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})