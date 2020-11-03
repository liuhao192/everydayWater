package ren.kura.everydaywater.wechat.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

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
@Data
public class ParamesAPI implements Serializable{

    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.reminderTemplateId}")
    private String reminderTemplateId;
    @Value("${wechat.miniProgramState}")
    private String miniProgramState;
    @Value("${wechat.page}")
    private String page;
    @Value("${wechat.lang}")
    private String lang;

}
