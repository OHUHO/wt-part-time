// components/AnswerQuestion/AnswerQuestion.js

const app = getApp()
Component({
    /**
     * 组件的属性列表
     */
    properties: {
        portrait:{
            type: String,
            value:'https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo0JnOqicrn63GG2AQAOTsuFRjV1ibnIlhBjnsYrHcHwfqoicm5PiaON6nEDUwL4R7xT3uY2c9TnhZDSA/132',
        },
        username:{
            type: String,
            value: '京茶吉鹿'
        },
        publishTime:{
            type: String ,
            value: '2-25 21:29'
        },
        comment:{
            type: String,
            value: "这！"
        },
        replyList:{
            type: Array,
            value:[
                {'username':'京茶吉鹿','replyTime':'2-26 14:29','commentReply':'哈'},
                {'username':'景超','replyTime':'2-26 19:09','commentReply':'哈哈'},
                {'username':'Aubuary','replyTime':'2-25 22:30','commentReply':'哈哈哈'},
            ],
        },
        // 页面（students）传回的评论的id值
        transCommentId:{
            type: Number,
            value: null,
        },
        // 页面（students）传回的信息的id值
        tranQuestionId:{
            type: Number,
            value: null,
        }, 

    },

    /**
     * 组件的初始数据
     */
    data: {
        isShowReplyBox: false,
        isAutoFocus: false,
        replyContents:'',
    },

    /**
     * 组件的方法列表
     */
    methods: {
        // 打赏功能
        reward(){
            wx.showToast({
              title: '未开放！',
              icon: 'error'
            })
        },
        //评论功能
        reply(){
            this.setData({
                isShowReplyBox: !this.data.isShowReplyBox,
                isAutoFocus: !this.data.isAutoFocus,
            })
        },
        setInput:function(e){
            //设置用户输入的数据
            const {
              name
            } = e.target.dataset
            this.data[name] = e.detail.value
            this.setData(this.data)
        },

        confirmReply(){
            if(this.data.replyContents == ''){
                wx.showToast({
                  title: '内容不能为空',
                  icon:'none'
                })
            }
            else{

                // console.log(this.data.replyContents)
                // console.log(this.properties.transCommentId)

                var that = this
                var user = wx.getStorageSync('userInfo');//获取缓存在本地的用户数据
                const data = {}

                data.commentId = this.properties.transCommentId;
                data.userId = user.id;
                data.username = user.username;
                data.portrait = user.portrait;
                data.replyDetail = this.data.replyContents;
                data.creatTime = new Date().getFullYear() + '-'+ (new Date().getMonth()+1) + '-' + new Date().getDate() + ' ' + new Date().getHours() + ':' + new Date().getMinutes() + ':' + new Date().getMinutes()
                // console.log(user)
                console.log(JSON.stringify(data)) //打印提交的用户信息

                var serverUrl = app.serverUrl;
                wx.request({
                    url: serverUrl + '/questionsComment/reply',
                    method:'POST',
                    data:data,
                    header: {
                        'content-type': 'application/json',
                    },
                    success:function(res){
                        var me = that;
                        console.log(res);
                        wx.showToast({
                            title: '回复成功',
                            icon:'success',
                            
                        })
                        me.setData({
                            isShowReplyBox: false
                        })

                        //回复成功后，重新从数据库中去获取数据（刷新操作）
                        // 调用页面（students）中的函数
                        // var questionId = me.properties.tranQuestionId;
                        
                        const myEventDetail = {} // detail对象，提供给事件监听函数
                        me.triggerEvent("getQuestionsAbout",myEventDetail)

                    }
                })
      
            }
            
            //获取页面的评论信息的id（ 页面===>组件 ），并将信息返回给后端进行保存
            

        }
    }
})
