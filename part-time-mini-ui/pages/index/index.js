
const app = getApp()
Page({
  
  data: {
    statusBarHeight: app.globalData.statusBarHeight * 2,
    //swiper的高度
    swiperHeight:1000,
    // 轮播图
    carousels:[],
    // 当前轮播图
    currentIndex: 0, 
    // 兼职类型
    jobTypes:[],
    // 商家排行榜
    businesses:[],
  },

  onLoad: function (options) {
    this.setData(({
      carousels:app.globalData.carousels,
      jobTypes:app.globalData.jobTypes,
      businesses:app.globalData.businesses
    }))
  },

  // 搜索
  search: function() {
    wx.navigateTo({
      url: '/pages/search/search',
    })
  },

  // 轮播图手动切换
  carouselChange: function (e) {  
    this.setData({
      currentIndex:e.detail.current
    })
  },

  // 兼职类型
  jobType(e){
    var typeId = e.currentTarget.dataset.value;
    console.log("type id", typeId)
    wx.navigateTo({
      url: '/pages/job-type/job-type?typeId=' + typeId,
    })
  },
  
  // 查看更多商家
  lookMore(e){
    wx.navigateTo({
      url: '/pages/business-list/business-list',
    })
  },

  // 商家详情
  businessDetails(e){
    var businessId = e.currentTarget.dataset.value;
    wx.navigateTo({
      url: '/pages/business-details/business-details?businessId='+businessId,
    })
  }
  
})