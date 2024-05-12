import { count as countExperienceGood } from '../../api/experienceGood';
import { count as countExperienceLove } from '../../api/experienceLove';
import { countFans, countLove } from '../../api/fans';
import { getById } from '../../api/user'

// 手指起始的坐标
let startY = 0;
// 手指移动的坐标
let moveY = 0;
// 手指移动的距离
let moveDistance = 0;
Page({
  data: {
    coverTransform: 'translateY(0)',
    coveTransition: '',
    userInfo:{
      username:'您还未登录',
      portrait: '/static/images/default.png',
      businesss: false,
    },
    privateUserInfo:null,
  },
  async onLoad (){
    var user = wx.getStorageSync('userInfo');
    wx.showLoading({
      title: '请等待',
    });
    // 获取当前用户信息
    let res = await getById(user.id)
    if(res.code === 200){
      this.setData({
        userInfo: res.data,
      })
      wx.setStorageSync('userInfo', res.data)
    }
    this.getPrivateInfo(user.id)
  },

  // 商家认证
  business(){
    if(this.data.userInfo.business){
      wx.showToast({
        title: '您已经认证过了',
        duration: 2000,
      })
      return
    }
    wx.navigateTo({
      url: '/pages/authentication/authentication',
    })
  },
  // 获取用户收藏、粉丝、关注、点赞数量
  async getPrivateInfo(userId){
    let privateUserInfo = {
      experienceLove:0,
      experienceGood:0,
      userFans:0,
      userLove:0,
    }
    // 收藏数量
    let resLove = await countExperienceLove('',userId)
    if(resLove.code === 200){
      privateUserInfo.experienceLove = resLove.data
    }
    // 点赞数量
    let resGood = await countExperienceGood('',userId)
    if(resGood.code === 200){
      privateUserInfo.experienceGood = resGood.data
    }
    // 粉丝数量
    let resFans  = await countFans(userId)
    if(resFans.code === 200){
      privateUserInfo.userFans = resFans.data
    }
    // 关注数量
    let resUserLove = await countLove(userId)
    if(resUserLove.code === 200){
      privateUserInfo.userLove = resUserLove.data
    }

    this.setData({
      privateUserInfo:privateUserInfo
    })
  },

  // 收藏或点赞
  experienceLove(e){
    let key = e.currentTarget.dataset.key
    let condition = {
      current:1,
      size:9999,
      userId:this.data.userInfo.id,
      key:key,
      title:'',
    }
    condition.title = key == "love" ? "我的收藏" : "我的点赞"
    wx.navigateTo({
      url: '/pages/my-love/my-love?data=' + JSON.stringify(condition),
    })
  },
  // 粉丝或关注
  userLove(e){
    let key = e.currentTarget.dataset.key
    let condition = {
      current:1,
      size:9999,
      fromId:'',
      toId:'',
      title:''
    }
    condition[key] = this.data.userInfo.id
    condition.title = condition.fromId != '' ? "我的关注" : "我的粉丝"
    console.log(condition)
    wx.navigateTo({
      url: '/pages/my-fans/my-fans?data=' + JSON.stringify(condition),
    })
  },

  // 发布兼职
  myJob(){
    wx.navigateTo({
      url: '/pages/my-job/my-job',
    })
  },
  // 我参与的
  myJoin(){
    wx.navigateTo({
      url: '/pages/my-join/my-join',
    })
  },
  // 我的经验
  myExperience(){
    wx.navigateTo({
      url: '/pages/my-experience/my-experience',
    })
  },
  // 我的认证
  myAuthentication(){
    wx.navigateTo({
      url: '/pages/my-authentication/my-authentication',
    })
  },
  

  handleTouchStart(event){
    this.setData({
      coveTransition: ''
    })
    // 获取手指起始坐标
    startY = event.touches[0].clientY;
  },
  handleTouchMove(event){
    moveY = event.touches[0].clientY;
    moveDistance = moveY - startY;
    
    if(moveDistance <= 0){
      return;
    }
    if(moveDistance >= 160){
      moveDistance = 160;
    }
    // 动态更新coverTransform的状态值
    this.setData({
      coverTransform: `translateY(${moveDistance}rpx)`
    })
  },
  handleTouchEnd(){
    // 动态更新coverTransform的状态值
    this.setData({
      coverTransform: `translateY(0rpx)`,
      coveTransition: 'transform 1s linear'
    })
  },
  
  // 联系我们
  call() {
    wx.showModal({
      title: '提示',
      content: '手机联系/微信联系',
      confirmText: "微信联系",
      cancelText: "手机联系",
      confirmColor: "#f6a57f",
      cancelColor: "#f6a57f",
      success: function(e) {
        if (e.confirm) {
          wx.showModal({
            title: '提示',
            content: '微信号：ytt',
            confirmText: "复制",
            success: function(e) {
              if (e.confirm) {
                wx.setClipboardData({
                  data: 'ytt',
                })
              }
            }
          })
        } else {
          wx.showModal({
            title: '提示',
            content: '是否联系(12345678911)',
            success: function(e) {
              if (e.confirm) {
                wx.makePhoneCall({
                  phoneNumber: '12345678911',
                })
              }
            }
          })
        }
      }
    })
  },
  // tab切换时的钩子函数
  onTabItemTap(){
    this.onLoad()
  }
})