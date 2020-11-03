package ren.kura.everydaywater.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

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

@Data
@Component
public class WeChatConfig implements Serializable {
    /**
     * 临时登录凭证code转换获取openid的url地址
     */

    @Value("${wechatapi.codeSession}")
    private String code2Session;
    @Value("${wechatapi.accessToken}")
    private String accessToken;
    @Value("${wechatapi.uniformMessage}")
    private String uniformMessage;
}
