import {request} from './http'

// 保存评价信息
export function saveReviews(data){
  return request({
    url:'/reviews/save',
    method: 'post',
    data
  })
}
// 查询评价详情
export function getByMultiId(jobId,fromId,toId){
  return request({
    url:'/reviews/getByMultiId?jobId=' + jobId + '&fromId=' + fromId + "&toId=" + toId,
    method:'get',
  })
}

