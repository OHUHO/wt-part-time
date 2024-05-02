import { getGoods } from "../../api/experienceGood"
import { getLoves } from "../../api/experienceLove"

Page({
  data: {
    condition:null,
    experience:[]
  },

  onLoad(options) {
    let data = JSON.parse(options.data)
    this.setData({
      condition:data
    })
    // console.log(this.data.condition)
    this.data.condition.key == "love" ? this.getLoves(data) : this.getGoods(data)
  },

  // 获取我的收藏列表
  async getLoves(condition){
    let res = await getLoves(condition)
    if(res.code === 200){
      this.setData({
        experience:res.data.records
      })
    }
  },
  // 获取我的点赞列表
  async getGoods(condition){
    let res = await getGoods(condition)
    if(res.code === 200){
      this.setData({
        experience:res.data.records
      })
    }
  },

  // 详情
  details(e){
    let experienceId = e.currentTarget.dataset.value
    console.log("weeee",experienceId)
    wx.navigateTo({
      url: '/pages/experience-details/experience-details?experienceId=' + experienceId,
    })
  }

})