// 本机开发时使用
var WxApiRoot = 'http://localhost:8082/wx/';

module.exports = {
  IndexUrl: WxApiRoot + 'home/index', //首页数据接口
  AboutUrl: WxApiRoot + 'home/about', //介绍信息

  AuthLoginByAccount: WxApiRoot + 'auth/login', //账号登录
  AuthLogout: WxApiRoot + 'auth/logout', //账号登出
  AuthReset: WxApiRoot + 'auth/reset', //账号密码重置
  AuthRegisterCaptcha: WxApiRoot + 'auth/regCaptcha', //验证码
  AuthBindPhone: WxApiRoot + 'auth/bindPhone', //绑定微信手机号

  StorageUpload: WxApiRoot + 'storage/upload', //图片上传,

  FeedbackAdd: WxApiRoot + 'feedback/submit', //添加反馈

  UserIndex: WxApiRoot + 'user/index', //个人页面用户相关信息
  MyDetail: WxApiRoot + 'user/mydetail', //个人页面用户相关信息
  IssueList: WxApiRoot + 'issue/list', //帮助信息

  UploadInfoAdd: WxApiRoot + 'information/add', // 义工信息上传 UploadInfoAdd
  ListInfoByStatus: WxApiRoot + 'information/listByStatus', // 查看通过审核的义工信息
};