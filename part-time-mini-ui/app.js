// app.js
import { getBusiness, getCarousel, getJobType } from "./api/app"

App({
  globalData: {
    statusBarHeight: 0,
    // 轮播图
    carousels:[],
    // 兼职类型
    jobTypes:[],
    // 商家排行榜
    businesses:[],
  },
  async onLaunch() {
    // 获取 status bar的高度
    wx.getSystemInfo({
      success: (res) => {
        this.globalData.statusBarHeight = res.statusBarHeight
      }
    })
    // 获取小程序首页的数据：轮播图、兼职类型、商家排行榜
    await this.getCarousel() 
    await this.getJobType() 
    this.getBusiness()
  },

  // 获取轮播图
  async getCarousel(){
    let condition = {
      current:1,
      size:6,
      keywords:'',
    }
    let res = await getCarousel(condition)
    if(res.code === 200){
      this.globalData.carousels = res.data.records
    }
  },
  // 获取兼职分类
  async getJobType(){
    let condition = {
      current:1,
      size:4,
      keywords:'',
      isOrder:true,
    }
    let res = await getJobType(condition)
    if(res.code === 200){
      this.globalData.jobTypes = res.data.records
    }
  },
  // 获取商家排行榜
  async getBusiness(){
    let condition = {
      current:1,
      size:6,
      keywords:'',
      status:[2]
    }
    let res = await getBusiness(condition)
    if(res.code === 200){
      this.globalData.businesses = res.data.records
    }
  },

  // onPullDownRefresh: function () {
  //   //启用标题栏显示加载状态
  //   wx.showNavigationBarLoading()
  //   console.log('下拉刷新 触发')
  //   setTimeout(() => {
  //     wx.hideNavigationBarLoading()
  //     wx.stopPullDownRefresh() 
  //   }, 2000);
  // },

})
