package ren.kura.everydaywater.remind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ren.kura.everydaywater.remind.entity.UserRemindData;
import ren.kura.everydaywater.remind.entity.UserRemindSubscribe;
import ren.kura.everydaywater.remind.mapper.UserRemindSubscribeMapper;
import ren.kura.everydaywater.remind.service.IUserRemindSubscribeService;

import java.util.UUID;

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
@Service
public class UserRemindSubscribeServiceImpl extends ServiceImpl<UserRemindSubscribeMapper, UserRemindSubscribe> implements IUserRemindSubscribeService {
    /**
     * {@inheritDoc}
     */
    @Override
    public UserRemindSubscribe addOrUpdateSubscribe(String openId, String templateId) {
        UserRemindSubscribe userRemindSubscribe = getSubscribeByOpenIdAndTemplateId(openId, templateId);
        //查找订阅是否存在
        if (StringUtils.isEmpty(userRemindSubscribe)) {
            //不存在新增订阅
            userRemindSubscribe = new UserRemindSubscribe();
            String id = UUID.randomUUID().toString().replace("-", "");
            userRemindSubscribe.setId(id);
            userRemindSubscribe.setOpenId(openId);
            userRemindSubscribe.setTemplateId(templateId);
            userRemindSubscribe.setIsSubscribe(1);
            userRemindSubscribe.setDeleteFlag("0");
            this.save(userRemindSubscribe);
        } else {

            userRemindSubscribe.setIsSubscribe(1);
            this.updateById(userRemindSubscribe);
        }
        return userRemindSubscribe;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRemindSubscribe getSubscribeByOpenIdAndTemplateId(String openId, String templateId) {
        LambdaQueryWrapper<UserRemindSubscribe> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(UserRemindSubscribe::getOpenId, openId);
        cQuery.eq(UserRemindSubscribe::getTemplateId, templateId);
        UserRemindSubscribe userRemindSubscribe = this.getOne(cQuery);
        return userRemindSubscribe;
    }
}
