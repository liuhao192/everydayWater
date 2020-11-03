package ren.kura.everydaywater.remind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.kura.everydaywater.common.utils.TimeUtils;
import ren.kura.everydaywater.remind.entity.UserRemindConfig;
import ren.kura.everydaywater.remind.entity.UserRemindData;
import ren.kura.everydaywater.remind.mapper.UserRemindDataMapper;
import ren.kura.everydaywater.remind.service.IUserRemindConfigService;
import ren.kura.everydaywater.remind.service.IUserRemindDataService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: null.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/20 liuha : Created
 * <p>公司信息: ************公司 *********部
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/20 12:21
 */
@Service
public class UserRemindDataServiceImpl extends ServiceImpl<UserRemindDataMapper, UserRemindData> implements IUserRemindDataService {
    @Autowired
    private IUserRemindConfigService userRemindConfigService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRemindNextTimeOpenId(String openId) {

        UserRemindConfig remindConfig = userRemindConfigService.getRemindConfig(openId);
        if (remindConfig != null && remindConfig.getIsRemind() == 0) {
            return "-";
        }

        QueryWrapper<UserRemindData> cQuery = new QueryWrapper<>();
        cQuery.eq("open_id", openId);
        cQuery.orderByAsc("remind_time");
        List<UserRemindData> remindDataList = this.list(cQuery);

        Calendar calendar = Calendar.getInstance();
        String formatTime = TimeUtils.getFormatTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = sdf.parse(formatTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);

        ///查询当前时间的后一次时间
        for (UserRemindData userRemindData : remindDataList) {
            if (calendar.getTime().before(userRemindData.getRemindTime())) {
                return TimeUtils.getFormatTime(userRemindData.getRemindTime());
            }
        }

        return "-";
    }
}
