package com.song.admin.util;

public class AdminResponseCode {
    public static final Integer ADMIN_INVALID_NAME = 601;
    public static final Integer ADMIN_INVALID_PASSWORD = 602;
    public static final Integer ADMIN_NAME_EXIST = 602;  // 添加管理员前，要比对，是否存在
    public static final Integer ADMIN_DELETE_NOT_ALLOWED = 604;
    public static final Integer ADMIN_INVALID_ACCOUNT = 605;
    public static final Integer ADMIN_INVALID_KAPTCHA = 606;
    public static final Integer ADMIN_INVALID_KAPTCHA_REQUIRED = 607;

    public static final Integer ROLE_NAME_EXIST = 640;
    public static final Integer ROLE_SUPER_SUPERMISSION = 641;
    public static final Integer ROLE_USER_EXIST = 642;
    public static final Integer NOTICE_UPDATE_NOT_ALLOWED = 660;

    public static final Integer USER_NAME_EXIST = 101;
}
