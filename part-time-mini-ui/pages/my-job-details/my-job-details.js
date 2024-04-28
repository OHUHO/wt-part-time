import { getById } from "../../api/job"
import { getRegistration } from "../../api/registration"

Page({
  data: {
    job:null,
    userInfo:wx.getStorageSync('userInfo'),
    // 已经报名的用户
    registration:[],
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
    // 查询当前兼职报名的用户信息
    this.getRegistrationUser(options.jobId)
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
  // 获取兼职报名用户
  async getRegistrationUser(jobId){
    let data = {
      current:1,
      size:999,
      jobId:jobId
    }
    let res = await getRegistration(data)
    if(res.code === 200){
      let temp = res.data.records
      if(temp.length == 0){
        return
      }
      // 对评价星级进行处理
      for(var i=0;i<temp.length;i++){
        // console.log(temp[i].reviewsToUser.point)
        if(temp[i].reviewsToUser != null){
          let point = this.setStar(temp[i].reviewsToUser.point - 1)
          temp[i].reviewsToUser.point = point
        }
      }
      this.setData({
        registration:temp
      })
    }
  },

  // 设置评分
  setStar(starCount){
    let stars = this.data.stars;
    for(var i = 0; i < stars.length; i ++){
      if(i <= starCount){
        stars[i] = '../../static/images/yellowStar.png'
      }else{
        stars[i] = '../../static/images/grayStar.png'
      }
    }
    return stars;
  },

  // 去评价
  goReviews(e){
    let registration = e.currentTarget.dataset.value
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
    if(registration.reviewsToUser != null){
      wx.showToast({
        title: '您已评价过了~',
        icon:'none',
        duration:3000
      })
      return
    }
    let data = {
      fromId:this.data.userInfo.id,
      toId:registration.userId,
      jobId:this.data.job.jobId,
      name:registration.username
    }
    console.log("business to user reviews data - ",data)
    wx.navigateTo({
      url: '/pages/job-reviews/job-reviews?data=' + JSON.stringify(data),
    })
  }

})