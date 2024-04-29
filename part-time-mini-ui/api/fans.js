import {request} from './http'


// 查询用户是否关注了当前查看的用户
export function getByFromToId(fromId,toId){
  return request({
    url:'/fans/getByFromToId?fromId=' + fromId + '&toId=' + toId,
    method:'get',
  })
}
// 关注
export function saveFans(data){
  return request({
    url:'/fans/save',
    method: 'post',
    data
  })
}
// 取消关注
export function deleteFans(fansId){
  return request({
    url:'/fans/delete?fansId=' + fansId,
    method: 'delete',
  })
}

