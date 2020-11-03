package ren.kura.everydaywater.remind.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ren.kura.everydaywater.remind.entity.UserRemindConfig;
import ren.kura.everydaywater.remind.entity.UserRemindLog;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: IUserRemindLogService.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/20 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/20 12:20
 */
public interface IUserRemindLogService extends IService<UserRemindLog> {

    /**
     * IUserRemindLogService:: getUserRemindLogByInfoId
     * <p>通过信息的id查询提醒用户的日志信息
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param infoId 信息的id
     * @return UserRemindLog   提醒用户的日志信息
     */
    UserRemindLog getUserRemindLogByInfoId(String infoId);

    /**
     * IUserRemindLogService:: updateReadByInfoId
     * <p>通过信息的id更新提醒醒用户的日志信息，前端标记是否显示打开的界面
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param infoId 信息的id
     * @return UserRemindLog  提醒用户的日志信息
     */
    UserRemindLog updateReadByInfoId(String infoId);
}
