import {request} from './http'

// 保存经验
export function saveExperience(data){
  return request({
    url:'/experience/save',
    method: 'post',
    data
  })
}
// 分页查询经验
export function getExperience(data){
  return request({
    url:'/experience/list',
    method: 'post',
    data
  })
}
// 通过ID查询
export function getById(experienceId){
  return request({
    url:'/experience/getById?experienceId=' + experienceId,
    method: 'get',
  })
}


