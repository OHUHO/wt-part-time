// 本组件为搜索组件
// 需要传入addflag   值为true / false （搜索框右侧部分）
// 若显示搜索框右侧部分   需传入右侧图标url以及addhandle函数
const app = getApp();
Component({

  properties: {

    addflag: { //显示搜索框右侧部分
      type: Boolean,
      value: false,

      observer(newVal, oldVal, changedPath) {

      }
    },
    addimg: { //显示搜索框右侧部分icon
      type: String,
      value: ''
    },
    searchstr: { //input  值
      type: String,
      value: '值'
    },
    searchflag: {
      type: Boolean,
      value: false,
    }

  },

  /**
   * 组件的初始数据
   */
  data: {
    // searchflag: false,
    statusBarHeight: 0
  },
  attached: function() {
    // 定义导航栏的高度   方便对齐
    this.setData({
      statusBarHeight: app.globalData.statusBarHeight
    })
  },


})