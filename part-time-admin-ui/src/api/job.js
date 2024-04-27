import request from '@/utils/request'

// 保存或更新
export function saveJob(data) {
  return request({
    url: '/job/save',
    method: 'post',
    data
  })
}

// 分页查询
export function getJob(data) {
    return request({
      url: '/job/list',
      method: 'post',
      data
    })
}

// 删除
export function deleteJob(jobId){
    return request({
        url:'/job/delete?jobId=' + jobId,
        method:'delete',
    })
}

// 通过ID查询
export function getById(jobId){
    return request({
        url:'/job/getById?jobId=' + jobId,
        method:'get',
    })
}
