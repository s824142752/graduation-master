package com.song.admin.controller;

import com.google.code.kaptcha.Producer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import com.song.admin.service.LogHelper;
import com.song.admin.util.Permission;
import com.song.admin.util.PermissionUtil;
import com.song.core.util.IpUtil;
import com.song.core.util.JacksonUtil;
import com.song.core.util.ResponseUtil;
import com.song.db.domain.GraduAdmin;
import com.song.db.service.GraduAdminService;
import com.song.db.service.GraduPermissionService;
import com.song.db.service.GraduRoleService;
import org.bouncycastle.tsp.TSPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static com.song.admin.util.AdminResponseCode.*;

@RestController
@RequestMapping("/admin/auth")
@Validated
public class AdminAuthController {
    private final Log logger = LogFactory.getLog(AdminAuthController.class);

    @Autowired
    private GraduAdminService adminService;
    @Autowired
    private GraduRoleService roleService;
    @Autowired
    private GraduPermissionService permissionService;
    @Autowired
    private LogHelper logHelper;

    @Autowired
    private Producer kaptchaProducer;

    @GetMapping("/kaptcha")
    public Object kaptcha(HttpServletRequest request) {
        String kaptcha = doKaptcha(request);
        if (kaptcha != null) {
            return ResponseUtil.ok(kaptcha);
        }
        return ResponseUtil.fail();
    }

    private String doKaptcha(HttpServletRequest request) {
        // ???????????????
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);
        HttpSession session = request.getSession();
        session.setAttribute("kaptcha", text);

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", outputStream);

            Base64.Encoder encoder = Base64.getEncoder();
            String encode = encoder.encodeToString(outputStream.toByteArray());

            String captchaBase64 = "data:image/jpeg;base64," + encode.replaceAll("\r\n", "");
            return captchaBase64;
        } catch (IOException e) {
            return null;
        }
    }

    /*
     *  { username : value, password : value }
     */
    @PostMapping("/login")
    public Object login(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        String code = JacksonUtil.parseString(body, "code");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(code)) {
            return ResponseUtil.fail(ADMIN_INVALID_KAPTCHA_REQUIRED, "??????????????????");
        }

        HttpSession session = request.getSession();
        String kaptcha = (String)session.getAttribute("kaptcha");
        if (Objects.requireNonNull(code).compareToIgnoreCase(kaptcha) != 0) {
            return ResponseUtil.fail(ADMIN_INVALID_KAPTCHA, "??????????????????", doKaptcha(request));
        }

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException uae) {
            logHelper.logAuthFail("??????", "??????????????????????????????");
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "??????????????????????????????", doKaptcha(request));
        } catch (LockedAccountException lae) {
            logHelper.logAuthFail("??????", "??????????????????????????????");
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "??????????????????????????????");

        } catch (AuthenticationException ae) {
            logHelper.logAuthFail("??????", "????????????");
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "????????????");
        }

        currentUser = SecurityUtils.getSubject();
        GraduAdmin admin = (GraduAdmin) currentUser.getPrincipal();
        admin.setLastLoginIp(IpUtil.getIpAddr(request));
        admin.setLastLoginTime(LocalDateTime.now());
        adminService.updateById(admin);

        logHelper.logAuthSucceed("??????");

        // userInfo
        Map<String, Object> adminInfo = new HashMap<String, Object>();
        adminInfo.put("nickName", admin.getUsername());
        adminInfo.put("avatar", admin.getAvatar());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", currentUser.getSession().getId());
        result.put("adminInfo", adminInfo);
        return ResponseUtil.ok(result);
    }

    /*
     *
     */
    @RequiresAuthentication
    @PostMapping("/logout")
    public Object logout() {
        Subject currentUser = SecurityUtils.getSubject();

        logHelper.logAuthSucceed("??????");
        currentUser.logout();
        return ResponseUtil.ok();
    }


    @RequiresAuthentication
    @GetMapping("/info")
    public Object info() {
        Subject currentUser = SecurityUtils.getSubject();
        GraduAdmin admin = (GraduAdmin) currentUser.getPrincipal();

        Map<String, Object> data = new HashMap<>();
        data.put("name", admin.getUsername());
        data.put("avatar", admin.getAvatar());

        Integer[] roleIds = admin.getRoleIds();
        Set<String> roles = roleService.queryByIds(roleIds);
        Set<String> permissions = permissionService.queryByRoleIds(roleIds);
        data.put("roles", roles);
        // NOTE
        // ??????????????????perms?????????????????????????????????API??????????????????????????????
        data.put("perms", toApi(permissions));
//        System.out.println("??????????????????" + data);
        return ResponseUtil.ok(data);
    }

    @Autowired
    private ApplicationContext context;
    private HashMap<String, String> systemPermissionsMap = null;

    private Collection<String> toApi(Set<String> permissions) {
        if (systemPermissionsMap == null) {
            systemPermissionsMap = new HashMap<>();
            final String basicPackage = "com.song.admin";
            List<Permission> systemPermissions = PermissionUtil.listPermission(context, basicPackage);
            for (Permission permission : systemPermissions) {
                String perm = permission.getRequiresPermissions().value()[0];
                String api = permission.getApi();
                systemPermissionsMap.put(perm, api);
            }
        }

        Collection<String> apis = new HashSet<>();
        for (String perm : permissions) {
            String api = systemPermissionsMap.get(perm);
            apis.add(api);

            if (perm.equals("*")) {
                apis.clear();
                apis.add("*");
                return apis;
                //                return systemPermissionsMap.values();

            }
        }
        return apis;
    }

    @GetMapping("/401")
    public Object page401() {
        return ResponseUtil.unlogin();
    }

    @GetMapping("/index")
    public Object pageIndex() {
        return ResponseUtil.ok();
    }

    @GetMapping("/403")
    public Object page403() {
        return ResponseUtil.unauthz();
    }
}
