import request from '@/utils/request'

// 分页查询
export function getRegistration(data) {
    return request({
      url: '/registration/list',
      method: 'post',
      data
    })
}

// 保存或更新兼职报名信息
export function saveRegistration(data){
    return request({
      url:'/registration/save',
      method: 'post',
      data
    })
}
