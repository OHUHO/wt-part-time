// components/NavHeader/VavHeader.js
Component({
  /**
   * 组件的属性列表,由组件外部传入的数据
   */
  properties: {
    title:{
      type:String,
      value:"我是title的默认值"
    },
    nav:{
      type:String,
      value:"我是nav的默认值"
    }
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    lookMore(e){
      console.log(e)
      wx.showToast({
        title: '跳转',
      })
    }

  }
  
})
