import { getById } from "../../api/job"
import { registrationEd, saveJRegistration } from "../../api/registration"

Page({
  data: {
    job:null,
    userInfo:wx.getStorageSync('userInfo'),
    // 已经报名兼职
    isRegistration:false
  },
  onLoad(options) {
    // console.log(options.jobId)
    this.getJob(options.jobId)
    // 查询当前用户是否已报名该活动
    this.getRegistration(options.jobId)
  },
  // 获取兼职详情
  async getJob(jobId){
    let res = await getById(jobId)
    if(res.code === 200){
      this.setData({
        job:res.data
      })
    }
  },
  // 获取兼职报名信息
  async getRegistration(jobId){
    let res = await registrationEd(jobId,this.data.userInfo.id)
    if(res.code === 200 && res.data){
      this.setData({
        isRegistration:true
      })
    }
  },

  reminder(){
    wx.showToast({
      title: '待添加...',
      icon:'none'
    })
  },
  // 联系商家
  contact(e){
    wx.showModal({
      title: '提示',
      content: '是否联系 15284734518',
      cancelText: '取消',
      confirmText: '确定',
      confirmColor: '#faa680',
      success: (result) => {
        if (result.confirm) {
          wx.makePhoneCall({
            phoneNumber: '15284734518',
          })
        }
      },
    });
  },
  // 报名
  async join(){
    let data = {
      jobId:this.data.job.jobId,
      userId:this.data.userInfo.id,
    }
    let res = await saveJRegistration(data)
    if(res.code === 200){
      this.setData({
        ['job.registered']:this.data.job.registered + 1
      })
      wx.showToast({
        title: '报名成功!',
        icon: 'success',
        duration: 2000,
        success() {
          setTimeout(function () {
            wx.navigateBack()
          }, 3000);
        }
      })
    }
  }

})