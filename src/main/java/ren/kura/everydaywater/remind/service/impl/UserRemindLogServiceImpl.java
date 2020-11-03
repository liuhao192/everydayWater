package ren.kura.everydaywater.remind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ren.kura.everydaywater.remind.entity.UserRemindConfig;
import ren.kura.everydaywater.remind.entity.UserRemindData;
import ren.kura.everydaywater.remind.entity.UserRemindLog;
import ren.kura.everydaywater.remind.mapper.UserRemindConfigMapper;
import ren.kura.everydaywater.remind.mapper.UserRemindLogMapper;
import ren.kura.everydaywater.remind.service.IUserRemindConfigService;
import ren.kura.everydaywater.remind.service.IUserRemindLogService;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: UserRemindLogServiceImpl.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/20 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/20 12:21
 */
@Service
public class UserRemindLogServiceImpl extends ServiceImpl<UserRemindLogMapper, UserRemindLog> implements IUserRemindLogService {
    /**
     * {@inheritDoc}
     */
    @Override
    public UserRemindLog getUserRemindLogByInfoId(String infoId) {
        LambdaQueryWrapper<UserRemindLog> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(UserRemindLog::getInfoId, infoId);
        return this.getOne(cQuery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRemindLog updateReadByInfoId(String infoId) {
        UserRemindLog userRemindLog = getUserRemindLogByInfoId(infoId);
        userRemindLog.setIsRead(1);
        this.updateById(userRemindLog);
        ;
        return userRemindLog;
    }
}
