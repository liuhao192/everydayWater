package ren.kura.everydaywater.wechat.service.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import ren.kura.everydaywater.config.WeChatConfig;
import ren.kura.everydaywater.wechat.dto.ParamesAPI;
import ren.kura.everydaywater.wechat.dto.WeChatUserSession;
import ren.kura.everydaywater.wechat.service.IWeChatService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: WeChatServiceImpl.java
 * <p>描述: 微信的业务接口实现类
 * <p>HISTORY: 2020/10/11 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/11 22:37
 */
@Service
@Slf4j
public class WeChatServiceImpl implements IWeChatService {
    /**
     * wx的api
     */
    @Autowired
    WeChatConfig weChatConfig;

    @Autowired
    private ParamesAPI paramesAPI;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOpenIdByCode(String code) {
        Map<String,String> map=new HashMap(3);
        //前端传入的code
        map.put("jsCode",code);
        WeChatUserSession userSession  = getCode2SessionJson(putAppIdAndSecretToMap(map));
        return StringUtils.isEmpty(userSession) ? "" : userSession.getOpenid();
    }

    /**
     *  WeChatServiceImpl:: putAppIdAndSecretToMap
     *  <p>将开发的appId，secret存入到map中
     *  <p>HISTORY: 2020/10/15 liuha : Created.
     *  @param    map  需要进行赋值操作的map
     *  @return   map  返回结果
     */
    private Map putAppIdAndSecretToMap(Map map) {
        String appId = paramesAPI.getAppId();
        String secret = paramesAPI.getSecret();
        map.put("appId", appId);
        map.put("secret", secret);
        return map;
    }

    /**
     * WeChatServiceImpl:: getCode2SessionJson
     * <p>得到用户的凭证信息包括openid,sessionKey(密钥)
     * <p>HISTORY: 2020/10/12 liuha : Created.
     *
     * @param map 请求中的需要替换的连接值得map 如{appId}&secret={secret} map{appId:appId;secret:secret}
     * @return JsonObject  The maximum speed of the object.
     */
    private WeChatUserSession getCode2SessionJson(Map map) {
        RestTemplate restTemplate = new RestTemplate();
        //url
        String code2Session = weChatConfig.getCode2Session();
        ResponseEntity<String> entity = restTemplate.getForEntity(code2Session, String.class, map);
        //成功
        if (entity.getStatusCode().is2xxSuccessful()) {
            String body = entity.getBody();
            WeChatUserSession userSession = new Gson().fromJson(body,WeChatUserSession.class);
            return userSession;
        }
        return null;
    }
}
