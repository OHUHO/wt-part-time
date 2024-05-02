import { getFans } from "../../api/fans"

Page({
  data: {
    condition:null,
    fans:null,
  },

  onLoad(options) {
    let data = JSON.parse(options.data)
    this.setData({
      condition:data
    })
    // console.log(this.data.condition)
    this.getFans(data)
  },

  // 获取粉丝或关注列表
  async getFans(data){
    let res = await getFans(data)
    if(res.code === 200){
      this.setData({
        fans:res.data.records
      })
    }
  }

})