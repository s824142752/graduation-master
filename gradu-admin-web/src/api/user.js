import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/user/list',
    method: 'get',
    params: query
  })
}

export function userDetail(nickname) {
  return request({
    url: '/user/detail',
    method: 'get',
    params: nickname
  })
}

export function updateUser(data) {
  return request({
    url: '/user/update',
    method: 'post',
    data
  })
}

export function addUser(data) {
  return request({
    url: '/user/addByExcel',
    method: 'post',
    data
  })
}

export function deleteUser(data) {
  return request({
    url: '/user/delete',
    method: 'post',
    data
  })
}

export function listFeedback(query) {
  return request({
    url: '/feedback/list',
    method: 'get',
    params: query
  })
}

export function listFootprint(query) {
  return request({
    url: '/footprint/list',
    method: 'get',
    params: query
  })
}

export function listHistory(query) {
  return request({
    url: '/history/list',
    method: 'get',
    params: query
  })
}

/*
* 在学生用户信息界面查看学生义工信息的详细信息，
* */
export function listInfoDetail(username) {
  return request({
    url: '/user/getInfoByUsername',
    method: 'get',
    params: { username }
  })
}
