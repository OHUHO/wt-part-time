import { getJobs } from "../../api/job";

// pages/search/search.js
Page({
  data: {
    condition:{
      current:1,
      size:9999,
      keywords:'',
    },
    searchEd:false,
    jobs:[]
  },

  onLoad(options) {
    
  },

  setInput(e) {
    let value = e.detail.detail.value;
    this.setData({
      ['condition.keywords']: value
    })
  },
  enter(e) {
    if (this.data.condition.keywords.length < 2) {
      wx.showModal({
        title: '提示',
        content: '字数太少，至少输入两个字',
        showCancel: false
      })
      return;
    }
    this.search()
  },
  clear(){
    this.setData({
      ['condition.keywords']: '',
      searchEd:false,
    })
  },

  // 搜索
  async search(){
    // console.log(this.data.condition)
    let res = await getJobs(this.data.condition)
    if(res.code === 200){
      this.setData({
        searchEd:true,
        jobs:res.data.records
      })
    }
  },

  // 详情
  details(e){
    wx.navigateTo({
      url: '/pages/job-details/job-details?jobId=' + e.currentTarget.dataset.value,
    })
  }

})