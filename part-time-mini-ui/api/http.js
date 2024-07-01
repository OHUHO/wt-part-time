const baseUrl = 'http://localhost:8417/api'

function request({url='',method='GET',header={'content-type': 'application/json'},data={}}){
    return new Promise((resolve,reject) => {
        wx.showLoading({
          title: '正在加载...',
        })
        wx.request({
            url: baseUrl + url,
            method:method,
            data:data,
            header:header,
            timeout:5000,
            // 请求成功的回调
            success:res=>{
                wx.hideLoading()
                resolve(res.data)
            },
            // 请求失败回调
            fail:err => {
                wx.hideLoading()
                wx.showToast({
                  title: '请求是发生错误！',
                  icon:'error',
                  duration:2000
                })
                reject(err)
            },
            // 请求完成回调
            complete:()=>{
                console.log("request complete!")
            }
        })
    })
}

function  upload({url,fileUrl,fileName=''}) {
  return new Promise((resolve,reject) => {
    wx.showLoading({
      title: '正在加载...',
    })
    wx.downloadFile({
      url: fileUrl,
      success:res => {
        let filePath = res.tempFilePath
        if(res.statusCode === 200){
          wx.uploadFile({
            url: baseUrl + url, 
            filePath: filePath,
            name: 'file',
            formData: {
              fileName: fileName
            },
            success: res => {
              wx.hideLoading()
              resolve(res.data)
            },
            fail:err => {
              wx.hideLoading()
              wx.showToast({
                title: '上传时发生错误！',
                icon:'error',
                duration:2000
              })
              reject(err)
            },
            complete:() => {
              console.log("upload complete!")
            }
          })
        }
      }
    })
  })
}

export { request, upload };

