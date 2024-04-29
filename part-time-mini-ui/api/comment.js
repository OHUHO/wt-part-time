import {request} from './http'

// 保存评论
export function saveComment(data){
  return request({
    url:'/comment/save',
    method: 'post',
    data
  })
}
// 不分页查询经验
export function getList(data){
  return request({
    url:'/comment/list',
    method: 'post',
    data
  })
}
// 通过ID查询
export function getById(commentId){
  return request({
    url:'/comment/getById?commentId=' + commentId,
    method: 'get',
  })
}


