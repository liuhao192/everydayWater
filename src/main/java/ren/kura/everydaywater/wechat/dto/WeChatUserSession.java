package ren.kura.everydaywater.wechat.dto;

import lombok.Data;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: WeChatUserSession.java
 * <p>描述: 微信的返回得用户信息
 * <p>HISTORY: 2020/10/11 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/11 23:03
 */
@Data
public class WeChatUserSession {

    private  String session_key;

    private  String openid;

}
