
const app = getApp()
// pages/welcome/welcome.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    time: 6,
    isSkip: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // && app.globalData.businesses.lenght !=0
    if(app.globalData.carousels.lenght != 0 
      && app.globalData.jobTypes.lenght != 0){
      this.setData({
        isSkip: true
      })
    }
    this.setData({
      time: --this.data.time
    })
    if(this.data.time == 0){
      this.setData({
        time: 0,
      })
      var user = wx.getStorageSync("userInfo");
      // console.log("user---",user)
      if(user.length == 0){
        wx.navigateTo({
          url: '/pages/login/login',
        })
      }else{
        wx.switchTab({
          url: '/pages/index/index',
        })
      }
    }
    var that = this
    setTimeout(function() {
      that.onLoad();
    }, 1000)
  },

  // 跳过
  skip(){
    var user = wx.getStorageSync("userInfo");
    if(user.length == 0){
      wx.navigateTo({
        url: '/pages/login/login',
      })
    }else{
      wx.switchTab({
        url: '/pages/index/index',
      })
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {
    return {
      title: "京茶吉鹿🦌",
      imageUrl: "/static/images/logo.png"
    }
  }
})