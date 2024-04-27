import { getRegistration } from "../../api/registration"

Page({
  data: {
    jobs:[],
    condition:{
      current:1,
      size:9999,
      userId:wx.getStorageSync('userInfo').id
    },
    currentTime:new Date().getFullYear() + '/'+ new Date().getMonth().toString().padStart(2, '0') + '/' + new Date().getDate().toString().padStart(2, '0')
  },

  onLoad(options) {
    this.getJobs()
  },
  // 获取兼职
  async getJobs(){
    let res = await getRegistration(this.data.condition)
    if(res.code === 200){
      this.setData({
        jobs:res.data.records
      })
    }
  },
  // 详情
  details(e){
    wx.navigateTo({
      url: '/pages/my-join-details/my-join-details?jobId=' + e.currentTarget.dataset.value,
    })
  }
  
})