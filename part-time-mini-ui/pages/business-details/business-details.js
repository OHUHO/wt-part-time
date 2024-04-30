import { getById } from "../../api/business"
import { getJobs } from "../../api/job"

Page({
  data: {
    business:null,
    jobs:null,
    condition:{
      current:1,
      size:9999,
      keywords:'',
      businessId:'',
      status:[2],
    }
  },

  onLoad(options) {
    // this.setData({
    //   ['condition.businessId']:"1782395934693371906"
    // })
    // this.getBusiness('1782395934693371906')
    this.setData({
      ['condition.businessId']:options.businessId
    })
    this.getBusiness(options.businessId)
    this.getJobs()
  },

  // 获取商家详情
  async getBusiness(businessId){
    let res = await getById(businessId,2)
    if(res.code === 200){
      this.setData({
        business:res.data
      })
    }
  },

  // 查询历史兼职
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
    wx.navigateTo({
      url: '/pages/job-details/job-details?jobId=' + e.currentTarget.dataset.value,
    })
  }

})