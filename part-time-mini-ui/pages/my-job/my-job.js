import { getJobs } from "../../api/job"

Page({
  data: {
    jobs:[],
    condition:{
      current:1,
      size:9999,
      buinesssId:wx.getStorageSync('userInfo').id
    }
  },

  onLoad(options) {
    this.getJobs()
  },
  // 获取兼职
  async getJobs(){
    let res = await getJobs(this.data.condition)
    if(res.code === 200){
      this.setData({
        jobs:res.data.records
      })
    }
  },
  // 详情
  details(e){
    wx.navigateTo({
      url: '/pages/my-job-details/my-job-details?jobId=' + e.currentTarget.dataset.value,
    })
  }
  
})