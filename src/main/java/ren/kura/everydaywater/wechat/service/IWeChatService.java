package ren.kura.everydaywater.wechat.service;

import org.springframework.util.MultiValueMap;
import ren.kura.everydaywater.wechat.dto.WeChatUniformDTO;

import java.util.Map;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: IWeChatService.java
 * <p>描述: 微信的业务接口
 * <p>HISTORY: 2020/10/11 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/11 22:37
 */
public interface IWeChatService {
    /**
     * IWeChatService:: getOpenIdByCode
     * <p>通过签到传入的code,返回用户唯一标识openId
     * <p>HISTORY: 2020/10/11 liuha : Created.
     *
     * @param code 登录时获取的 code
     * @return String  用户唯一标识openId
     */
    String getOpenIdByCode(String code);

    /**
     * IWeChatService:: getAccessToken
     * <p>得到开发者accessToke
     * <p>HISTORY: 2020/10/25 liuha : Created.
     *
     * @return String
     */
    String getAccessToken();

    /**
     *  IWeChatService:: sandUniformMessage
     *  <p>发送信息模板信息
     *  <p>HISTORY: 2020/10/25 liuha : Created.
     * @param uniform 提醒信息内容
     * @param formId 模板的id
     * @param openId 小程序的唯一性用户的凭证；发送的对象
     *  @return   是否成功
     *
     */
    int sandUniformMessage(WeChatUniformDTO uniform, String formId, String openId);
}
