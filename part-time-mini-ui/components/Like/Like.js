// components/Like/Like.js
Component({
    /**
     * 组件的属性列表
     */
    properties: {
        portrait:{
            type: String,
            value: 'https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo0JnOqicrn63GG2AQAOTsuFRjV1ibnIlhBjnsYrHcHwfqoicm5PiaON6nEDUwL4R7xT3uY2c9TnhZDSA/132',
        },
        username:{
            type: String,
            value: "京茶吉鹿🦌" 
        },
        introduction:{
            type: String,
            value: "请好好介绍自己",
        },
        isFollow:{
            type: Boolean,
            value: true,
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

    }
})
