const app = getApp()
Page({
  
  data: {
    statusBarHeight: app.globalData.statusBarHeight * 2 + 20,
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

  carouselChange: function (e) {  
    this.setData({
      currentIndex:e.detail.current
    })
  },
  // 搜索
  search: function() {
    wx.navigateTo({
      url: '/pages/search/search',
    })
  },
  jobType(e){
    var typeId = e.currentTarget.dataset.value;
    console.log("type id", typeId)
    wx.navigateTo({
      url: '/pages/fourBlock/fourBlock?typeId=' + typeId,
    })
  },
  
  onLoad: function (options) {
    this.setData(({
      carousels:app.globalData.carousels,
      jobTypes:app.globalData.jobTypes,
      businesses:app.globalData.businesses
    }))
  },
})