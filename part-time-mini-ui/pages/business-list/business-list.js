import { getBusiness } from "../../api/business"

Page({
  data: {
    businesses:null
  },

  onLoad(options) {
    this.getBusiness()
  },

  // 获取商家排行榜
  async getBusiness(){
    let condition = {
      current:1,
      size:9999,
      keywords:'',
      status:[2]
    }
    let res = await getBusiness(condition)
    if(res.code === 200){
      this.setData({
        businesses:res.data.records
      })
    }
  },

  // 商家详情
  businessDetails(e){
    var businessId = e.currentTarget.dataset.value;
    wx.navigateTo({
      url: '/pages/business-details/business-details?businessId='+businessId,
    })
  }

})