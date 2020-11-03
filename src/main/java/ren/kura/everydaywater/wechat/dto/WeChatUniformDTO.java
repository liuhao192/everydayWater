package ren.kura.everydaywater.wechat.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: null.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/25 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/25 21:56
 */
@Data
public class WeChatUniformDTO {
    //用户openid
    private String touser;
    //订阅消息模版id

    private String template_id;

    private String miniprogram_state;

    private String page;

    private String lang;

    //推送文字
    private Map<String, WeChatUniformTemplateDTO> data;
}
