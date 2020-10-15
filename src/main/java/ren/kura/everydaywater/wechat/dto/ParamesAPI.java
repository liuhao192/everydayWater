package ren.kura.everydaywater.wechat.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: ParamesAPI.java
 * <p>描述: 微信的配置信息
 * <p>HISTORY: 2020/10/11 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/11 23:03
 */
@Component
@ConfigurationProperties(prefix = "wechat")
@PropertySource("classpath:config/wechat.properties")
@Data
public class ParamesAPI {


    private String appId;

    private String secret;
}
