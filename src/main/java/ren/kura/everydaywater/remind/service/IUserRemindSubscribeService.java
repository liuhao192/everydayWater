package ren.kura.everydaywater.remind.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ren.kura.everydaywater.remind.entity.UserRemindSubscribe;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: null.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/26 liuha : Created
 * <p>公司信息: ************公司 *********部
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/26 23:16
 */
public interface IUserRemindSubscribeService extends IService<UserRemindSubscribe> {
    /**
     * IUserRemindSubscribeService:: addOrUpdateSubscribe
     * <p>新增或者更新用户的订阅模板的信息
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param openId     小程序的唯一性id
     * @param templateId 模板的id
     * @return UserRemindSubscribe 用户的订阅信息
     */
    UserRemindSubscribe addOrUpdateSubscribe(String openId, String templateId);

    /**
     * IUserRemindSubscribeService:: getSubscribeByOpenIdAndTemplateId
     * <p>通过openId和reminderTemplateId获取当前用户的订阅信息
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param openId             用户在小程序的唯一性id
     * @param reminderTemplateId 模板的id
     * @return UserRemindSubscribe 用户的订阅信息
     */
    UserRemindSubscribe getSubscribeByOpenIdAndTemplateId(String openId, String reminderTemplateId);
}
