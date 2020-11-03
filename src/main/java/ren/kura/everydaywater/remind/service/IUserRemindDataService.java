package ren.kura.everydaywater.remind.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ren.kura.everydaywater.remind.entity.UserRemindData;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: IUserRemindDataService.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/20 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/20 12:20
 */
public interface IUserRemindDataService extends IService<UserRemindData> {
    /**
     * IUserRemindDataService:: getRemindNextTimeOpenId
     * <p>查询用户的下次提醒的时间，以 时-分 的格式返回给前端
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param openId 小程序的用户唯一性凭证
     * @return String   时-分 的时间格式
     */
    String getRemindNextTimeOpenId(String openId);
}
