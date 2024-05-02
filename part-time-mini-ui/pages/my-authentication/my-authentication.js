import { getById } from "../../api/business"


Page({
  data: {
    business:null,
  },

  onLoad(options) {
    this.getBusiness()
  },

  // 获取我的认证信息
  async getBusiness(){
    let userId = wx.getStorageSync('userInfo').id
    let res  = await getById(userId,'')
    if(res.code === 200){
      this.setData({
        business:res.data
      })
    }
  },

})