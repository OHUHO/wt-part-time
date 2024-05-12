import { getExperience } from "../../api/experience"

// pages/experience/experience.js
Page({

  data: {
    userInfo:wx.getStorageSync('userInfo'),
    experience:[],
    condition:{
      current:1,
      size:9999,
      keywords:''
    }
  },

  onLoad(options) {
    this.getExperience()
  },

  // 获取经验
  async getExperience(){
    let res = await getExperience(this.data.condition);
    if(res.code === 200){
      this.setData({
        experience:res.data.records
      })
    }
  },
  // 发布经验
  publish(){
    wx.navigateTo({
      url: '/pages/publish-experience/publish-experience',
    })
  },
  // 详情
  details(e){
    let experienceId = e.currentTarget.dataset.value
    // console.log("weeee",experienceId)
    wx.navigateTo({
      url: '/pages/experience-details/experience-details?experienceId=' + experienceId,
    })
  },
  // tab切换时的钩子函数
  onTabItemTap(){
    this.onLoad()
  }
})