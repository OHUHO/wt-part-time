import { getById } from "../../api/business"
import { getJobs } from "../../api/job"


Page({

  data: {
    userInfo: wx.getStorageSync('userInfo'),
    business: {},
    jobs:[],
    condition:{
      current:1,
      size:9999,
    }
  },
  async onLoad(options) {
    if(this.data.userInfo.business){
      // 查询商家信息
      let res = await getById(this.data.userInfo.id,2)
      if(res.code === 200){
        this.setData({
          business: res.data
        })
      }
    }
    this.getJobs()
  },
  // 发布兼职
  publishJob(){
    wx.navigateTo({
      url: '/pages/publish-job/publish-job',
    })
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
  // 兼职详情
  details(e){
    // console.log("e.currentTarget.detaset.value",e.currentTarget.dataset.value)
    wx.navigateTo({
      url: '/pages/job-details/job-details?jobId=' + e.currentTarget.dataset.value,
    })
  }
})