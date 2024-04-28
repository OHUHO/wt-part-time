import { uploadFile } from "../../api/common"
import { saveReviews,getByMultiId } from "../../api/reviews";

Page({

  data: {
    reviews:{
      // 评价人
      fromId:'',
      // 被评价人
      toId:'',
      // 兼职Id
      jobId:'',
      // 积分
      point:5,
      // 内容
      content:'',
      // 附加证明材料
      additional:''
    },
    name:'阿超的小卖铺',
    stars:[
      '../../static/images/yellowStar.png',
      '../../static/images/yellowStar.png',
      '../../static/images/yellowStar.png',
      '../../static/images/yellowStar.png',
      '../../static/images/yellowStar.png',
    ]
  },

  async onLoad(options) {
    let data = JSON.parse(options.data)
    // 查询是否已经评价过了
    // let res = await getByMultiId(data.jobId,data.fromId,data.toId)
    // if(res.code == 200 && res.data != null){
    //   this.setData({
    //     reviews:res.data
    //   })
    //   this.setStar(res.data.point - 1)
    //   return
    // }
    this.setData({
      ['reviews.fromId']:data.fromId,
      ['reviews.toId']:data.toId,
      ['reviews.jobId']:data.jobId,
      name:data.name
    })
  },

  // 设置数据输入
  setInput(e){
    const key = e.target.dataset.key;
    this.setData({
      [key]: e.detail.value
    });
  },

  // 评分
  selectPoint(e){
    let index = e.currentTarget.dataset.index;
    // console.log(index)
    this.setStar(index)
  },
  // 设置评分
  setStar(index){
    let stars = this.data.stars;
    for(var i = 0; i < stars.length; i ++){
      if(i <= index){
        stars[i] = '../../static/images/yellowStar.png'
      }else{
        stars[i] = '../../static/images/grayStar.png'
      }
    }
    this.setData({
      stars:stars,
      ['reviews.point']:index+1
    })
  },


  // 上传材料
  async uploadFiles(e) {
    wx.chooseMessageFile({
      count: 1,
      type:'file',
      success:res=>{
        console.log("call back success")
        let tempFilePath = res.tempFiles[0].path;
        console.log(tempFilePath)
        var that = this
        uploadFile(tempFilePath).then(resp => {
          resp = JSON.parse(resp)
          if(resp.code === 200){
            that.setData({
              papersFilesName:res.tempFiles[0].name,
              ['reviews.additional']:resp.data
            })
            wx.showToast({
              title: '材料上传成功',
              icon:'success',
              duration:3000
            })
          }
        })
      },
      fail:err=>{
        console.log("call back fail")
      }
    })
  },

  // 提交评价
  async submit(){
    let reviews = this.data.reviews
    if(reviews.point <= 1 && (reviews.additional =='' || reviews.content == '')){
      wx.showToast({
        title: '一星评价必须填写评价内容,并提交材料～',
        icon:'none',
        duration:3000
      })
      return;
    }
    let res = await saveReviews(this.data.reviews)
    if(res.code === 200){
      wx.showToast({
        title: '评价成功!',
        icon: 'success',
        duration: 2000,
        success() {
          setTimeout(function () {
            wx.navigateBack()
          }, 2000);
        }
      })
    }
  }
  
})