// pages/experience/experience.js
Page({

  data: {
    userInfo:wx.getStorageSync('userInfo')

  },

  onLoad(options) {

  },

  // 发布经验
  publish(){
    wx.navigateTo({
      url: '/pages/publish-experience/publish-experience',
    })
  },

})