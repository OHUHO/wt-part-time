import { getById } from "../../api/experience"
import { getByUserId as getGoodByUserId,saveGood,deleteGood } from "../../api/experienceGood"
import { getByUserId as getLoveByUserId,saveLove,deleteLove } from "../../api/experienceLove"
import { getByFromToId,saveFans,deleteFans } from "../../api/fans"
import { saveComment,getList } from "../../api/comment"


Page({
  data: {
    userInfo:wx.getStorageSync('userInfo'),
    // 经验
    experience:null,
    loveUser:null,
    goodExperience:null,
    loveExperience:null,
    comments:[],
    // 评论框状态：true隐藏，false显示
    hideModal:true,
    animationData:[],
    // 评论信息
    comment:{
      parentId:'',
      experienceId:'',
      fromUserId:'',
      fromUserName:'',
      toUserId:'',
      toUserName:'',
      content:'',
    }
  },

  onLoad(options) {
    // console.log("experienceId",options.experienceId)
    this.getExperience(options.experienceId)
    this.getExperienceGood(options.experienceId)
    this.getExperienceLove(options.experienceId)
    this.getComments(options.experienceId)
    this.setData({
      ['comment.experienceId']:options.experienceId,
      ['comment.fromUserId']:this.data.userInfo.id,
      ['comment.fromUserName']:this.data.userInfo.username
    })
  },

  // 获取经验
  async getExperience(experienceId){
    let res = await getById(experienceId)
    if(res.code === 200){
      this.setData({
        experience:res.data
      })
      this.getLove(res.data.userId)
    }
  },
  // 查询经验点赞
  async getExperienceGood(experienceId){
    let res = await getGoodByUserId(experienceId,this.data.userInfo.id)
    if(res.code === 200){
      this.setData({
        goodExperience:res.data
      })
    }
  },
  // 查询经验收藏
  async getExperienceLove(experienceId){
    let res = await getLoveByUserId(experienceId,this.data.userInfo.id)
    if(res.code === 200){
      this.setData({
        loveExperience:res.data
      })
    }
  },
  // 查询用户关注信息
  async getLove(toUserId){
    let res = await getByFromToId(this.data.userInfo.id,toUserId)
    if(res.code === 200){
      // console.log("res.data",res)
      this.setData({
        loveUser:res.data
      })
    }
  },

  // 关注或取消关注
  async love(){
    let data = {
      fromId:this.data.userInfo.id,
      toId:this.data.experience.userId,
    }
    let res = await saveFans(data)
    if(res.code === 200){
      this.setData({
        loveUser:res.data
      })
      wx.showToast({
        title: '关注成功！',
        icon:'success',
        duration:3000
      })
    }
  },
  async hate(){
    let res = await deleteFans(this.data.loveUser.fansId)
    if(res.code === 200){
      this.setData({
        loveUser:null
      })
      wx.showToast({
        title: '取关成功！',
        icon:'success',
        duration:3000
      })
    }
  },

  // 经验点赞
  async goodExperience(){
    if(this.data.goodExperience == null){
      // 点赞
      let data = {
        experienceId:this.data.experience.experienceId,
        userId:this.data.userInfo.id
      }
      let res = await saveGood(data)
      if(res.code === 200){
        this.setData({
          goodExperience:res.data
        })
      }
    }else{
      // 取消点赞
      let res = await deleteGood(this.data.goodExperience.goodId)
      if(res.code === 200){
        this.setData({
          goodExperience:null
        })
      }
    }
  },
  // 经验收藏
  async loveExperience(){
    if(this.data.loveExperience == null){
      // 收藏
      let data = {
        experienceId:this.data.experience.experienceId,
        userId:this.data.userInfo.id
      }
      let res = await saveLove(data)
      if(res.code === 200){
        this.setData({
          loveExperience:res.data
        })
      }
    }else{
      // 取消收藏
      let res = await deleteLove(this.data.loveExperience.loveId)
      if(res.code === 200){
        this.setData({
          loveExperience:null
        })
      }
    }
  },

  // 显示评论弹窗
  showModal(){
    this.setData({
      hideModal:false,
    })
    var animation = wx.createAnimation({
      //动画的持续时间默认400ms;数值越大,动画越慢
      duration: 10,
      //动画的效果 默认值是linear
      timingFunction: 'ease',
    })
    this.animation = animation
    var that = this
    setTimeout(function(){
      //调用显示动画
      that.fadeIn();
    },10)   
  },
  // 隐藏评论弹窗
  hideModal(){
    var animation = wx.createAnimation({
      //动画的持续时间默认400ms;数值越大,动画越慢
      duration: 10,
      //动画的效果 默认值是linear
      timingFunction: 'ease',
    })
    this.animation = animation
    //调用隐藏动画
    this.fadeDown();
    var that = this
    setTimeout(function(){
      that.setData({
        hideModal:true,
        ['comment.content']:''
      })      
    },10)
  },
  // 动画集
  fadeIn(){
    this.animation.translateY(0).step()
    this.setData({
      //动画实例的export方法导出动画数据传递给组件的animation属性
      animationData: this.animation.export()
    })    
  },
  fadeDown(){
    this.animation.translateY(300).step()
    this.setData({
      animationData: this.animation.export(),  
    })
  }, 

  // 绑定数据
  setInput(e) {
    const { key } = e.target.dataset
    this.setData({
      [key]:e.detail.value
    })
  },

  // 评论
  async comment(){
    // console.log("comment param - ",this.data.comment)
    if(this.data.comment.content == ''){
      wx.showToast({
        title: '评论不能为空！',
        icon:'none'
      })
      return
    }
    let resp = await saveComment(this.data.comment)
    if(resp.code === 200){
      this.setData({
        hideModal:true,
        ['comment.content']:''
      })
      wx.showToast({
        title: '评论成功!',
        icon:'success'
      })
      this.getComments(this.data.experience.experienceId)
    }
  },

  // 获取评论
  async getComments(experienceId){
    let res = await getList({experienceId:experienceId})
    if(res.code === 200){
      this.setData({
        comments:res.data
      })
    }
  } 

})
