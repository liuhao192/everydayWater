package ren.kura.everydaywater.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: WeChatConfig.java
 * <p>描述: 微信服务的api配置
 * <p>HISTORY: 2020/10/11 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/11 22:52
 */
@Configuration
@ConfigurationProperties(prefix = "wechatapi")
@PropertySource("classpath:config/wechat.properties")
@Data
@Component

public class WeChatConfig {
    /**
     * 临时登录凭证code转换获取openid的url地址
     */
    private String code2Session;
}
