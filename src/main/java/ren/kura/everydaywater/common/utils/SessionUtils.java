package ren.kura.everydaywater.common.utils;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: SessionUtils.java
 * <p>描述: 用户信息缓存存储
 * <p>HISTORY: 2020/10/11 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/11 23:41
 */
public class SessionUtils {
    private static final long TIME_OUT = 7000 * 1000;
    /**
     * 系统登录用户信息【存在放HashMap内存中】
     */
    public static final Map<String, UserInfo> mapUser = new ConcurrentHashMap<String, UserInfo>();

    /**
     * SessionUtils:: getCacheSessionKey
     * <p>得到缓存中用户的SessionKey
     * <p>HISTORY: 2020/10/11 liuha : Created.
     *
     * @param openId 用户的唯一性凭证.
     * @return String  用户的SessionKey
     */
    public static String getCacheSessionKey(String openId) {
        UserInfo userInfo = mapUser.get(openId);
        long timestamp = userInfo.getTimestamp();
        //过期
        if (System.currentTimeMillis() - timestamp >= TIME_OUT) {
            return "";
        } else {
            return userInfo.getSessionKey();
        }
    }

    /**
     * SessionUtils:: putCacheSessionKey
     * <p>将用户的SessionKey存入缓存中
     * <p>HISTORY: 2020/10/11 liuha : Created.
     *
     * @param openId     用户唯一性凭证
     * @param sessionKey sessionKey
     */
    public static void putCacheSessionKey(String openId, String sessionKey) {
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenId(openId);
        userInfo.setSessionKey(sessionKey);
        userInfo.setTimestamp(System.currentTimeMillis());
        mapUser.put(openId, userInfo);
    }

    /**
     * 微信用户的数据信息
     */
    @Data
    static class UserInfo {
        String openId;
        String sessionKey;
        long timestamp;
    }
}
