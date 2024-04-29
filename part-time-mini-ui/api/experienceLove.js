import {request} from './http'


// 查询用户是否点赞了该经验
export function getByUserId(experienceId,userId){
  return request({
    url:'/love/getByUserId?experienceId=' + experienceId + '&userId=' + userId,
    method:'get',
  })
}
// 点赞经验
export function saveLove(data){
  return request({
    url:'/love/save',
    method: 'post',
    data
  })
}
// 取消点赞
export function deleteLove(loveId){
  return request({
    url:'/love/delete?loveId=' + loveId,
    method: 'delete',
  })
}

