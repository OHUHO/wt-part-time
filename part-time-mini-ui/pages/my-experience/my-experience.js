import { getExperience } from "../../api/experience"

Page({
  data: {
    experience:null,
  },

  onLoad(options) {
    this.getExperience()
  },

  // 查询我的经验
  async getExperience(){
    let condition = {
      current:1,
      size:9999,
      userId:wx.getStorageSync('userInfo').id
    }
    let res = await getExperience(condition)
    if(res.code === 200){
      this.setData({
        experience:res.data.records
      })
    }
  },

  // 详情
  details(e){
    let experienceId = e.currentTarget.dataset.value
    wx.navigateTo({
      url: '/pages/experience-details/experience-details?experienceId=' + experienceId,
    })
  }

})