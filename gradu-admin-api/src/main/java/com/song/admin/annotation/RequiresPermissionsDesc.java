package com.song.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* 自定义的`RequiresPermissionsDesc`注解
*
* 当后端设计好权限以后，需要向前端发送系统的权限情况，管理员可以查看系统当前所有权限以及允许为一个角色配置权限。
* 这里就带来了实现方面的问题:
* 一些开源项目在数据库设计一个permission表，记录系统所有权限，这种方式有一定局限性。
* 因此本项目采用权限注解方式，要求开发者在使用权限注解（RequiresPermissions）的地方应该同时使用权限文档注解（RequiresPermissionsDesc），
* 这样系统能够通过权限注解自动生成权限列表，向前端返回可读的信息。
*
* */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissionsDesc {
    String[] menu();

    String button();
}
