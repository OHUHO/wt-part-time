import { getJobs } from "../../api/job"
import { getById } from "../../api/jobType"


Page({

  
  data: {
    jobType:null,
    jobs:null,
    condition:{
      current:1,
      size:9999,
      status:[2],
      typeId:'',
    }
  },
  
  onLoad(options) {
    // console.log(options.typeId)
    this.setData({
      ['condition.typeId']:options.typeId
    })
    this.getJobType(options.typeId)
    this.getJobs(options.typeId)
  },

  // 获取兼职种类信息
  async getJobType(typeId){
    let res = await getById(typeId)
    if(res.code === 200){
      this.setData({
        jobType:res.data
      })
    }
  },

  // 获取兼职信息
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