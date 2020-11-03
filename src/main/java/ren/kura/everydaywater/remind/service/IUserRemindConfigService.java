package ren.kura.everydaywater.remind.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ren.kura.everydaywater.remind.entity.UserRemindConfig;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: null.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/20 liuha : Created
 *
 * @Author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @Version 1.0
 * @Date: 2020/10/20 12:20
 */
public interface IUserRemindConfigService extends IService<UserRemindConfig> {
    /**
     *  <p>IUserRemindConfigService:: getRemindConfigByOpenId
     *  <p>通过openId得到用户的配置信息
     *  <p>HISTORY: 2020/10/20 liuha : Created.
     *  @param    openId 微信的用户凭证id
     *  @return   UserRemindConfig 返回用户的配置信息
     */
    UserRemindConfig getRemindConfigByOpenId(String openId);
/**
 *  IUserRemindConfigService:: addRemindConfig
 *  <p>新增用户的定时配置信息
 *  <p>HISTORY: 2020/10/20 liuha : Created.
 *  @param    openId  微信的用户凭证id.
 *  @param    userRemindConfig  用户的配置信息
 *  @return   UserRemindConfig  返回用户的配置信息
 */
    UserRemindConfig addIntervalRemindConfig(String openId, UserRemindConfig userRemindConfig);

    UserRemindConfig getRemindConfig(String openId);

    UserRemindConfig updateRemindConfig(String openId, UserRemindConfig userRemindConfig);

    int openRemindConfig(String openId);

    int closeRemindConfig(String openId);
}
