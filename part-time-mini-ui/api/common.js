import { upload } from './http'

// 图片上传
export function uploadFile(fileUrl){
  return upload({
    url:'/files/upload',
    fileUrl: fileUrl,
  })
}

