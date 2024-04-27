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
  },
  async onLoad (){
    var user = wx.getStorageSync('userInfo');
    wx.showLoading({
      title: '请等待',
    });
    // 获取当前用户信息
    let res = await getById(user.id)
    // console.log(res)
    if(res.code === 200){
      this.setData({
        userInfo: res.data,
      })
    }
  },
  // 商家认证
  business(){
    if(this.data.userInfo.business){
      wx.showToast({
        title: '您已经认证过了',
        duration: 2000,
      })
    }else{
      wx.navigateTo({
        url: '/pages/authentication/authentication',
      })
    }
  },
  // 兼职发布
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

  //钱包
  wallet(){
    wx.showToast({
      title: '此功能未开通~',
      icon:'none'
    })
  },
  //粉丝和关注
  like:function(e){
    var information = e.currentTarget.dataset.information;
    wx.navigateTo({
      url: '/pages/like/like?information=' + information,
    })
  },
  //认证
  authentication: function(){
    wx.navigateTo({
      url: '/pages/authentication/authentication',
    })
  },
  //我的兼职发布
  myPartTime:function(){
    wx.navigateTo({
      url: '/pages/myPartTime/myPartTime',
    })
  },
  //我的需求发布
  myDemand:function(){
    wx.navigateTo({
      url: '/pages/myDemand/myDemand',
    })
  },
  //我的咨询
  myConsult:function(){
    wx.showToast({
      title: '功能开发中~',
      icon:'none'
    })
  },
  
  //我报名的兼职
  mypartimeJobs:function(){
      wx.navigateTo({
        url: '/pages/myPartTimeJobs/myPartTimeJobs',
        
      })
  },

  //联系客服
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
})