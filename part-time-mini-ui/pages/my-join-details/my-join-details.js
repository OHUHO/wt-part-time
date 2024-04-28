import { getById } from "../../api/job"
import { getByMultiId } from "../../api/reviews"

Page({
  data: {
    job:null,
    // 用户评价信息
    reviews:null,
    userInfo:wx.getStorageSync('userInfo'),
    stars:[
      '../../static/images/yellowStar.png',
      '../../static/images/yellowStar.png',
      '../../static/images/yellowStar.png',
      '../../static/images/yellowStar.png',
      '../../static/images/yellowStar.png',
    ]
  },
  onLoad(options) {
    this.getJob(options.jobId)
    // this.getReviews(options.jobId)
  },
  // 获取兼职详情
  async getJob(jobId){
    let res = await getById(jobId)
    if(res.code === 200){
      this.setData({
        job:res.data
      })
    }
    // 获取评价信息
    this.getReviews()
  },

  // 查询是否已评价
  async getReviews(){
    // 查询用户是否已经进行了评价
    let res = await getByMultiId(this.data.job.jobId,this.data.userInfo.id,this.data.job.businessId)
    if(res.code == 200 && res.data != null){
      this.setData({
        reviews:res.data
      })
      // 设置等级
      this.setStar(res.data.point - 1)
    }
  },

  // 设置评分
  setStar(index){
    let stars = this.data.stars;
    for(var i = 0; i < stars.length; i ++){
      if(i <= index){
        stars[i] = '../../static/images/yellowStar.png'
      }else{
        stars[i] = '../../static/images/grayStar.png'
      }
    }
    this.setData({
      stars:stars,
    })
  },
  
  // 去评价
  goReviews(){
    let endTime = this.data.job.endTime
    endTime = endTime.substring(0,4) + '/' + endTime.substring(5,7) + '/' + endTime.substring(8,10)
    let currentTime = new Date().getFullYear() + '/'+ (new Date().getMonth() + 1).toString().padStart(2, '0') + '/' + new Date().getDate().toString().padStart(2, '0')
    if(endTime >= currentTime){
      wx.showToast({
        title: '活动未结束,暂不能进行评价~',
        icon:'none',
        duration:3000
      })
      return
    }
    if(this.data.reviews != null){
      wx.showToast({
        title: '您已评价过了~',
        icon:'none',
        duration:3000
      })
      return
    }
    let data = {
      fromId:this.data.userInfo.id,
      toId:this.data.job.businessId,
      jobId:this.data.job.jobId,
      name:this.data.job.storeName
    }
    console.log("user to business reviews data - ", data)
    wx.navigateTo({
      url: '/pages/job-reviews/job-reviews?data=' + JSON.stringify(data),
    })
  }

})