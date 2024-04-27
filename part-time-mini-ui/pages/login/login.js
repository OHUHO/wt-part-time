import { uploadFile } from "../../api/common"
import { login,getById } from "../../api/user"

// pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    motto:'京茶吉鹿',
    backgroundImg:'/static/images/login.jpg',
    portrait:'',
    username:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
  },

  //暂不登录
  noLogin(){
    wx.switchTab({
      url: '/pages/index/index',
    })
  },

  // 快捷登录
  async loginHandler() {
    wx.getUserProfile({
      desc: '用户信息用于完善资料', 
      success: (e) => {
        var user = e.userInfo
        // 上传头像
        this.uploadAvatar(user.avatarUrl)
        this.setData({
          username: user.nickName
        })
        wx.login({
          success: (res) => {
            wx.showLoading({
              title: '登录中……',
            })
            //获取登录的临时凭证code
            let data = {
              code: res.code,
              username: this.data.username,
              portrait: this.data.portrait
            }
            // 用户登录
            this.login(data)
          },
        })
      }
    })
  },

  // 上传头像
  async uploadAvatar(avatarUrl){
    let res = await uploadFile(avatarUrl)
    res = JSON.parse(res)
    if(res.code === 200){
      this.setData({
        portrait: res.data
      })
    }
  },
  // 登录
  async login(data){
    // console.log("current login user form",data)
    let res = await login(data)
    // console.log("登录成功",res)
    if(res.code === 200){
      console.log("current login user info ",res.data)
      // 获取用户信息
      let resp = await getById(res.data.id)
      if(resp.code === 200){
        // 保存用户信息
        wx.setStorageSync('userInfo', resp.data)
        wx.showToast({
          title: '登录成功',
          icon:'success',
        })
        wx.switchTab({
          url: '/pages/index/index',
        })
      }
    }
  },
})
