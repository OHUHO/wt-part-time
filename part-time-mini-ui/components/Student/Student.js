// components/Student.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    title:{
      type:String,
      value:'这是一个标题',
    },
    labelList:{
      type:Array,
      value:['重金酬谢','在线答疑','有问必答']
      // value:['在线答疑','重金酬谢','有问必答']
    },
    schooMajor:{
      type:String,
      value:'电子科技大学成都学院/软件工程'
    }

  },

  /**
   * 组件的初始数据
   */
  data: {
    labelList:[
      '景超','答疑'
    ],
    // 所有的颜色
    colorArr: ["#EE2C2C", "#ff7070", "#EEC900", "#4876FF", "#ff6100",
      "#7DC67D", "#E17572", "#7898AA", "#C35CFF", "#33BCBA", "#C28F5C",
      "#FF8533", "#6E6E6E", "#428BCA", "#5cb85c", "#FF674F", "#E9967A",
      "#66CDAA", "#00CED1", "#9F79EE", "#CD3333", "#FFC125", "#32CD32",
      "#00BFFF", "#68A2D5", "#FF69B4", "#DB7093", "#CD3278", "#607B8B"],
    // 随机颜色
    randomColorArr: []
  },

  lifetimes: {
    // 在组件实例进入页面节点树时执行
    attached: function(options) {
      
      var that = this,
      labLen = that.data.labelList.length,
      // labLen = 1,
      colorArr = that.data.colorArr,
      colorLen = colorArr.length,
      randomColorArr = [];
      //判断执行
      do{
        let random = colorArr[Math.floor(Math.random() * colorLen)];
        randomColorArr.push(random);
        labLen--;
      } while (labLen > 0)
      
      that.setData({ 
        randomColorArr: randomColorArr
      });
    }
  },

  /**
   * 组件的方法列表
   */
  methods: {

  }
})
