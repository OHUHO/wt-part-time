import { saveExperience } from '../../api/experience'


Page({
  data: {
    experience:{
      // 经验标题
      name:'',
      // 经验内容
      content:'',
      createUserId: wx.getStorageSync('userInfo').id
    }
  },

  onLoad(options) {

  },

  //设置用户输入的数据
  setInput(e) {
    const { key } = e.target.dataset
    this.data[key] = e.detail.value
    this.setData(this.data)
  },

  // 发布经验
  async submit(){
    let data = this.data.experience
    if(data.name == '' || data.content == ''){
      wx.showToast({
        title: '内容不能为空～',
        icon:'none'
      })
      return
    }
    let res = await saveExperience(data)
    if(res.code === 200){
      wx.showToast({
        title: '经验发布成功!',
        icon: 'success',
        duration: 2000,
        success() {
          setTimeout(function () {
            wx.navigateBack()
          }, 3000);
        }
      })
    }
  }
})