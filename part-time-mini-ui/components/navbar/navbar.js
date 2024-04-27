const app = getApp()
Component({
  properties: {
    // 由父页面传递的数据，变量名字自命名
    navbarData: { 
      type: Object,
      value: {
        showCapsule:true,
        title:'默认标题'
      },
      observer: function(newVal, oldVal) {}
    }
  },
  data: {
    statusBarHeight: '',
  },
  attached: function() {
    // 获取是否是通过分享进入的小程序
    this.setData({
      share: app.globalData.share
    })
    // 定义导航栏的高度
    this.setData({
      statusBarHeight: app.globalData.statusBarHeight
    })
  },
  methods: {
    // 返回上一页面
    _navback() {
      wx.navigateBack({
        delta: 1
      })
    },
    //返回到首页
    _backhome() {
      wx.switchTab({
        url: '/pages/index/index',
      })
    }
  }
})