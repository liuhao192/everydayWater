package ren.kura.everydaywater.wechat.service;

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
     *  IWeChatService:: getOpenIdByCode
     *  <p>通过签到传入的code,返回用户唯一标识openId
     *  <p>HISTORY: 2020/10/11 liuha : Created.
     *  @param    code  登录时获取的 code
     *  @return   String  用户唯一标识openId
     */
    String getOpenIdByCode(String code);
}
