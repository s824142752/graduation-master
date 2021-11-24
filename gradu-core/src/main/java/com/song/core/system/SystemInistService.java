package com.song.core.system;

import com.song.core.util.SystemInfoPrinter;
import com.song.db.service.GraduSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 系统启动服务，用于设置系统配置信息、检查系统状态及打印系统信息
 */
@Component
class SystemInistService {
    private SystemInistService systemInistService;

    @Autowired
    private Environment environment;

    @PostConstruct
    private void inist() {
        systemInistService = this;
        initConfigs();
        SystemInfoPrinter.printInfo("gradu 初始化信息", getSystemInfo());
    }

    private final static Map<String, String> DEFAULT_CONFIGS = new HashMap<>();

    static {
        // 配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.GRADU_WEB_NAME, "graduation");
        DEFAULT_CONFIGS.put(SystemConfig.GRADU_WEB_ADDRESS, "河北");
        DEFAULT_CONFIGS.put(SystemConfig.GRADU_WEB_Latitude, "31.201900");
        DEFAULT_CONFIGS.put(SystemConfig.GRADU_WEB_LONGITUDE, "121.587839");
        DEFAULT_CONFIGS.put(SystemConfig.GRADU_WEB_PHONE, "0310-666-8888");
        DEFAULT_CONFIGS.put(SystemConfig.GRADU_WEB_QQ, "824142752");
    }

    @Autowired
    private GraduSystemConfigService graduSystemConfigService;

    private void initConfigs() {
        // 1. 读取数据库全部配置信息
        Map<String, String> configs = graduSystemConfigService.queryAll();

        // 2. 分析DEFAULT_CONFIGS
        for (Map.Entry<String, String> entry : DEFAULT_CONFIGS.entrySet()) {
            if (configs.containsKey(entry.getKey())) {
                continue;
            }

            configs.put(entry.getKey(), entry.getValue());
            graduSystemConfigService.addConfig(entry.getKey(), entry.getValue());
        }

        SystemConfig.setConfigs(configs);
    }

    private Map<String, String> getSystemInfo() {

        Map<String, String> infos = new LinkedHashMap<>();

        infos.put(SystemInfoPrinter.CREATE_PART_COPPER + 0, "系统信息");
        // 测试获取application-db.yml配置信息
        infos.put("服务器端口", environment.getProperty("server.port"));
        infos.put("数据库USER", environment.getProperty("spring.datasource.druid.username"));
        infos.put("数据库地址", environment.getProperty("spring.datasource.druid.url"));
        infos.put("调试级别", environment.getProperty("logging.level.com.song.wx"));

        // 测试获取application-core.yml配置信息
        infos.put(SystemInfoPrinter.CREATE_PART_COPPER + 1, "模块状态");
        infos.put("邮件", environment.getProperty("GRADU.notify.mail.enable"));
        infos.put("短信", environment.getProperty("GRADU.notify.sms.enable"));
        infos.put("模版消息", environment.getProperty("GRADU.notify.wx.enable"));
        infos.put("对象存储", environment.getProperty("GRADU.storage.active"));
        infos.put("本地对象存储路径", environment.getProperty("GRADU.storage.local.storagePath"));
        infos.put("本地对象访问地址", environment.getProperty("GRADU.storage.local.address"));
        infos.put("本地对象访问端口", environment.getProperty("GRADU.storage.local.port"));

        return infos;
    }
}
