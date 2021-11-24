import request from '@/utils/request'

/*
* 学生信息统计接口
* */
/*
* 每日学生增长量
* */
export function statUser(query) {
  return request({
    url: '/stat/user',
    method: 'get',
    params: query
  })
}

/*
* 不同学院的学生  Students from different colleges
* */
export function statDiffCollegeUser(query) {
  return request({
    url: '/stat/collegeUser',
    method: 'get',
    params: query
  })
}

/*
* 不同学院的学生  Students from different colleges by Echars
* */
export function statDiffCollegeUserByEcharts(query) {
  return request({
    url: '/stat/collegeUserEcharts',
    method: 'get',
    params: query
  })
}

/*
* 每日义工信息统计 - Daily Volunteer Information
* */
export function statDayInfo(query) {
  return request({
    url: '/stat/day',
    method: 'get',
    params: query
  })
}

/*
* 每日义工信息统计 - Daily Volunteer Information by Echars
* */
export function statDayInfoByEChart(query) {
  return request({
    url: '/stat/dayEcharts',
    method: 'get',
    params: query
  })
}
