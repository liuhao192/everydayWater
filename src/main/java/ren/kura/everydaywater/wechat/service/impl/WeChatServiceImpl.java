package ren.kura.everydaywater.wechat.service.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import ren.kura.everydaywater.config.WeChatConfig;
import ren.kura.everydaywater.wechat.dto.AccessToken;
import ren.kura.everydaywater.wechat.dto.ParamesAPI;
import ren.kura.everydaywater.wechat.dto.WeChatUniformDTO;
import ren.kura.everydaywater.wechat.dto.WeChatUserSession;
import ren.kura.everydaywater.wechat.service.IWeChatService;

import java.util.Date;
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


    public static String ACCESS_TOKEN;

    public static int TOKEN_CACHE_TIME = 7000000;

    public static Date EXPIRES_TIME;


    /**
     * {@inheritDoc}
     */
    @Override
    public String getOpenIdByCode(String code) {
        Map<String, String> map = new HashMap(3);
        //前端传入的code
        map.put("jsCode", code);
        String code2Session = weChatConfig.getCode2Session();
        String userSessionBody = getMethodForEntity(putAppIdAndSecretToMap(map), code2Session);
        WeChatUserSession userSession = new Gson().fromJson(userSessionBody, WeChatUserSession.class);
        return StringUtils.isEmpty(userSession) ? "" : userSession.getOpenid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized String getAccessToken() {
        //先检查之前获取的开发者凭证是否过期
        if (StringUtils.isEmpty(ACCESS_TOKEN) || ((System.currentTimeMillis() - EXPIRES_TIME.getTime()) >= TOKEN_CACHE_TIME)) {
            log.info("重新获取开发者凭证 oldToken:{} ", ACCESS_TOKEN);
            Map<String, String> map = new HashMap(2);
            String accessTokenUrl = weChatConfig.getAccessToken();
            String accessTokenBody = getMethodForEntity(putAppIdAndSecretToMap(map), accessTokenUrl);
            AccessToken accessToken = new Gson().fromJson(accessTokenBody, AccessToken.class);
            log.info("重新获取开发者凭证 newToken :{}", new Gson().toJson(accessToken));
            ACCESS_TOKEN = accessToken.getAccess_token();
            EXPIRES_TIME = new Date();
        }
        log.info("调用的 token:{}", ACCESS_TOKEN);
        return ACCESS_TOKEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sandUniformMessage(WeChatUniformDTO uniform, String formId, String openId) {
        uniform.setTemplate_id(formId);
        uniform.setMiniprogram_state(paramesAPI.getMiniProgramState());
        uniform.setLang(paramesAPI.getLang());
        String uniformMessage = weChatConfig.getUniformMessage();
        String uniformMessageUrl = uniformMessage + getAccessToken();
        log.info(" uniformMessageUrl :{}", uniformMessageUrl);
        String accessTokenBody = postMethodForEntity(new Gson().toJson(uniform), uniformMessageUrl);
        if (StringUtils.isEmpty(accessTokenBody)) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * WeChatServiceImpl:: putAppIdAndSecretToMap
     * <p>将开发的appId，secret存入到map中
     * <p>HISTORY: 2020/10/15 liuha : Created.
     *
     * @param map 需要进行赋值操作的map
     * @return map  返回结果
     */
    private Map putAppIdAndSecretToMap(Map map) {
        String appId = paramesAPI.getAppId();
        String secret = paramesAPI.getSecret();
        map.put("appId", appId);
        map.put("secret", secret);
        return map;
    }

    /**
     * WeChatServiceImpl:: getMethodForEntity
     * <p>得到用户的凭证信息包括openid,sessionKey(密钥)
     * <p>HISTORY: 2020/10/12 liuha : Created.
     *
     * @param map 请求中的需要替换的连接值得map 如{appId}&secret={secret} map{appId:appId;secret:secret}
     * @return JsonObject  The maximum speed of the object.
     */
    private String getMethodForEntity(Map map, String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, map);
        log.info("getMethodForEntity 请求url:{}", url);
        //成功
        if (entity.getStatusCode().is2xxSuccessful()) {
            String body = entity.getBody();
            log.info("getMethodForEntity 返回的结果为:{}", body);
            return body;
        }
        return null;
    }

    /**
     * WeChatServiceImpl::postMethodForEntity
     * <p>发送post请求
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param info post请求的参数
     * @param url  发送的url
     * @return String  返回的结果
     */
    private String postMethodForEntity(String info, String url) {
        log.info("postMethodForEntity 请求 uniform:{}", info);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<Map> request = new HttpEntity(info, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        log.info("postMethodForEntity 请求url:{}", url);
        //成功
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String body = responseEntity.getBody();
            log.info("postMethodForEntity 返回的结果为:{}", body);
            return body;
        }
        return null;
    }

}
