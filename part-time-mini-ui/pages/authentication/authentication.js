import { saveBusiness } from "../../api/business"
import { uploadFile } from "../../api/common"

const app = getApp()
Page({
  data: {
    nvabarData: {
      showCapsule: true, 
      title: '商家认证',
    },
    // 商家信息
    business:{
      // 真实姓名
      username:'',
      // 性别
      gender:'',
      // 手机号
      phone:'',
      // 身份证号
      cardNo:'',
      // 店铺类型
      storeType:'',
      // 店铺名称
      storeName:'',
      // 证件类型
      papersType:'',
      // 证件号码
      papersNumber:'',
      // 证件资料地址
      papersImg:'',
      // 商家ID,
      businessId:'',
      // 当前用户的信息
      createTime:'',
      createUserId:'',
      createUserName:'',
    },
    selectedGenderIndex:2,
    gender:[
      {name:'男',value:'1'},
      {name:'女',value:'2'},
      {name:'未知',value:'3'},
    ],
    selectedStoreTypeIndex:4,
    storeType:[
      "店铺类型-A",
      "店铺类型-B",
      "店铺类型-C",
      "店铺类型-D",
      "未知类型-Z",
    ],
    selectedPapersTypeIndex:3,
    papersType:[
      "证件类型-A",
      "证件类型-B",
      "证件类型-C",
      "未知类型-Z"
    ],
    papersFilesName:'',
    imageList: [],
  },
  onLoad(options) {
    let user = wx.getStorageSync('userInfo')
    this.setData({
      ['business.gender']:this.data.gender[this.data.selectedGenderIndex].value,
      ['business.storeType']:this.data.storeType[this.data.selectedStoreTypeIndex],
      ['business.papersType']:this.data.papersType[this.data.selectedPapersTypeIndex],
      ['business.businessId']:user.id,
      ['business.createUserId']:user.id,
      ['business.createUserName']:user.username,
    })
  },
  //设置用户输入的数据
  setInput(e) {
    const { key } = e.target.dataset
    this.data[key] = e.detail.value
    this.setData(this.data)
  },
  // 输入框数据自动更新
  update(e) {
    const key = e.target.dataset.key;
    this.setData({
      [key]: e.detail.value
    });
  },
  //性别选择
  selectGender(e) {
    let selectedIndex = e.detail.value
    this.setData({
      selectedGenderIndex: selectedIndex,
      ['business.gender']:this.data.gender[selectedIndex].value
    })
  },
  // 店铺类型选择
  selectStoreType(e){
    let selectedIndex = e.detail.value
    this.setData({
      selectedStoreTypeIndex: selectedIndex,
      ['business.storeType']:this.data.storeType[selectedIndex]
    })
  },
  // 证件类型选择
  selectPapersType(e){
    let selectedIndex = e.detail.value
    console.log(selectedIndex,selectedIndex)
    this.setData({
      selectedPapersTypeIndex: selectedIndex,
      ['business.papersType']:this.data.papersType[selectedIndex]
    })
  },
  // 上传认证材料
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
              ['business.papersImg']:resp.data
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
  // 判空
  notEmpty(obj){
    for (let key in obj) {
      if(obj[key] === '' || obj[key] === undefined){
        return false;
      }
    }
    return true;
  },
  // 获取当前时间
  currentTime(){
    let currentDate = new Date();
    let year = currentDate.getFullYear();
    let month = String(currentDate.getMonth() + 1).padStart(2, '0');
    let day = String(currentDate.getDate()).padStart(2, '0');
    let hours = String(currentDate.getHours()).padStart(2, '0');
    let minutes = String(currentDate.getMinutes()).padStart(2, '0');
    let seconds = String(currentDate.getSeconds()).padStart(2, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  },
  // 提交
  async submit(){
    console.log("submit business data",this.data.business)
    this.setData({
      ['business.createTime']:this.currentTime()
    })
    if(this.notEmpty(this.data.business)){
      let res = await saveBusiness(this.data.business);
      if(res.code === 200){
        wx.showToast({
          title: '提交成功!',
          icon: 'success',
          duration: 2000,
          success() {
            setTimeout(function () {
              wx.navigateBack()
            }, 2000);
          }
        })
      }else{
        wx.showToast({
          title: '提交失败！',
          icon:'error',
          duration:4000
        })
      }
      return
    }
    wx.showToast({
      title: '参数不完整~',
      icon:'error',
      duration:2000
    })
  },

})