import { getById } from "../../api/business"
import { getJobTypes } from "../../api/jobType"
import { saveJob } from "../../api/job"

// pages/release/release.js
const app = getApp()
Page({
  data: {
    job:{
      // 商家ID
      businessId:'',
      // 兼职名称
      name:'',
      // 兼职内容
      content:'',
      // 兼职封面
      // cover:'',
      // 兼职类型ID
      typeId:'',
      // 薪水
      salary:'',
      // 薪水类型：
      salaryType:'2',
      // 结算方式
      payType:'1',
      // 性别要求
      gender:'',
      // 招聘人数
      count:'',
      // 工作开始时间
      beginTime:new Date().getFullYear() + '-'+ (new Date().getMonth() + 1).toString().padStart(2, '0') + '-' + new Date().getDate().toString().padStart(2, '0'),
      // 工作结束时间
      endTime:new Date().getFullYear() + '-'+ (new Date().getMonth() + 1).toString().padStart(2, '0') + '-' + new Date().getDate().toString().padStart(2, '0'),
      // 工作地点
      address:'',
      // 创建人ID
      createUserId:'',
      // 创建人姓名
      createUserName:''
    },
    // 薪资类型
    salaryTypes: [
      {name:'小时',value:'1'},
      {name:'天',value:'2'},
      {name:'周',value:'3'},
      {name:'月',value:'4'},
      {name:'次',value:'5'}
    ],
    // 结算方式
    payTypes:[
      {name:'日结',value:'1'},
      {name:'周结',value:'2'},
      {name:'月结',value:'3'},
      {name:'完工结',value:'4'},
    ],
    // 兼职种类
    selectedJobTypeIndex:0,
    jobTypes:[],
    // 性别
    selectedGenderIndex:2,
    gender:[
      {name:'男',value:'1'},
      {name:'女',value:'2'},
      {name:'不限',value:'3'},
    ],
    address: ["四川省", "巴中市", "通江县"],
    addressDetails:'',
  },
  async onLoad(options) {
    let user = wx.getStorageSync('userInfo')
    let res = await getById(user.id,2)
    if(res.code === 200){
      this.setData({
        ['job.businessId']: res.data.businessId,
        ['job.gender']: this.data.gender[this.data.selectedGenderIndex].value,
        ['job.createUserId']: user.id,
        ['job.createUserName']: user.username
      })
    }
    this.getJobTypes()
  },
  // 绑定数据
  setInput(e) {
    const { key } = e.target.dataset
    // this.data[key] = e.detail.value
    // this.setData(this.data)
    this.setData({
      [key]:e.detail.value
    })
  },
  // 设置薪资类型
  setSalaryType(e){
    this.setData({
      ['job.salaryType']:e.currentTarget.dataset.value
    })
  },
  // 设置结算类型
  setPayType(e){
    this.setData({
      ['job.payType']:e.currentTarget.dataset.value
    })
  },
  // 获取兼职种类
  async getJobTypes(){
    let condition = {
      current:1,
      size:999,
      keywords:'',
    }
    let res = await getJobTypes(condition)
    if(res.code === 200){
      this.setData({
        jobTypes:res.data.records,
        ['job.typeId']:res.data.records[0].typeId
      })
    }
  },
  // 选择兼职种类
  selectJobType(e){
    let selectedIndex = e.detail.value
    this.setData({
      selectedJobTypeIndex: selectedIndex,
      ['job.typeId']:this.data.jobTypes[selectedIndex].typeId
    })
  },
  // 工作时间::开始
  selectBeginTime(e){
    this.setData({
      ['job.beginTime']:e.detail.value
    })
  },
  // 工作时间::结束
  selectEndTime(e){
    this.setData({
      ['job.endTime']:e.detail.value
    })
  },
  // 性别选择
  selectGender(e) {
    let selectedIndex = e.detail.value
    this.setData({
      selectedGenderIndex: selectedIndex,
      ['job.gender']:this.data.gender[selectedIndex].value
    })
  },
  // 选择省市区
  selectAddress(e){
    this.setData({ 
      address: e.detail.value,
    });
  },
  // 设置地址
  setInputAddress(e){
    let address = this.data.address
    this.setData({
      addressDetails:e.detail.value,
      ['job.address']:address[0]+","+address[1]+","+address[2]+","+e.detail.value
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

  async submit(){
    console.log("submit job data",this.data.job)
    if(this.notEmpty(this.data.job)){
      let data = this.data.job
      data.beginTime = data.beginTime + " 00:00:00"
      data.endTime = data.endTime + " 00:00:00"
      let res = await saveJob(data)
      if(res.code === 200){
        wx.showToast({
          title: '发布成功!',
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
  }
})