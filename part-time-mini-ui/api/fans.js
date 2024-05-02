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

// 分页查询 粉丝列表、关注列表
export function getFans(data){
  return request({
    url:'/fans/list',
    method: 'post',
    data
  })
}

// 统计用户的粉丝数量
export function countFans(userId){
  return request({
    url:'/fans/countFans?userId=' + userId,
    method:'get',
  })
}

// 统计用户的关注数量
export function countLove(userId){
  return request({
    url:'/fans/countLove?userId=' + userId,
    method:'get',
  })
}
