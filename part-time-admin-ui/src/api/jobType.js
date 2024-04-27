import request from '@/utils/request'

// 保存或更新
export function saveJobType(data) {
  return request({
    url: '/jobType/save',
    method: 'post',
    data
  })
}

// 分页查询
export function getJobType(data) {
    return request({
      url: '/jobType/list',
      method: 'post',
      data
    })
}

// 删除
export function deleteJobType(typeId){
    return request({
        url:'/jobType/delete?typeId=' + typeId,
        method:'delete',
    })
}

// 通过ID查询
export function getById(typeId){
    return request({
        url:'/jobType/getById?typeId=' + typeId,
        method:'get',
    })
}