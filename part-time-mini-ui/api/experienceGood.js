import {request} from './http'


// 查询用户是否点赞了该经验
export function getByUserId(experienceId,userId){
  return request({
    url:'/good/getByUserId?experienceId=' + experienceId + '&userId=' + userId,
    method:'get',
  })
}
// 点赞经验
export function saveGood(data){
  return request({
    url:'/good/save',
    method: 'post',
    data
  })
}
// 取消点赞
export function deleteGood(goodId){
  return request({
    url:'/good/delete?goodId=' + goodId,
    method: 'delete',
  })
}

// 分页查询
export function getGoods(data){
  return request({
    url:'/good/list',
    method: 'post',
    data
  })
}

// 统计用户的点赞数量
export function count(experienceId,userId){
  return request({
    url:'/good/count?experienceId=' + experienceId + '&userId=' + userId,
    method:'get',
  })
}

