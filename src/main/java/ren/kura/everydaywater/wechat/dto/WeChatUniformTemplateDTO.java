package ren.kura.everydaywater.wechat.dto;

import lombok.Data;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: null.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/25 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/25 23:57
 */
@Data
public class WeChatUniformTemplateDTO {
    private String value;

    public WeChatUniformTemplateDTO(String value) {
        this.value = value;
    }
}
