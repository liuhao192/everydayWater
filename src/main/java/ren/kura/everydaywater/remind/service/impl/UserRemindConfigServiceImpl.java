package ren.kura.everydaywater.remind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ren.kura.everydaywater.common.utils.TimeUtils;
import ren.kura.everydaywater.quartz.WaterSchedulerService;
import ren.kura.everydaywater.remind.entity.UserRemindConfig;
import ren.kura.everydaywater.remind.entity.UserRemindData;
import ren.kura.everydaywater.remind.job.UserRemindJob;
import ren.kura.everydaywater.remind.mapper.UserRemindConfigMapper;
import ren.kura.everydaywater.remind.service.IUserRemindConfigService;
import ren.kura.everydaywater.remind.service.IUserRemindDataService;
import ren.kura.everydaywater.water.entity.WaterNumConfig;
import ren.kura.everydaywater.water.service.IWaterNumConfigService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
public class UserRemindConfigServiceImpl extends ServiceImpl<UserRemindConfigMapper, UserRemindConfig> implements IUserRemindConfigService {


    @Autowired
    private IWaterNumConfigService waterNumConfigService;
    @Autowired
    private IUserRemindDataService userRemindDataService;
    @Autowired
    private WaterSchedulerService schedulerUtilService;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRemindConfig getRemindConfigByOpenId(String openId) {
        LambdaQueryWrapper<UserRemindConfig> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(UserRemindConfig::getOpenId, openId);
        return this.getOne(cQuery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserRemindConfig addIntervalRemindConfig(String openId, UserRemindConfig userRemindConfig) {
        String id = UUID.randomUUID().toString().replace("-", "");
        userRemindConfig.setId(id);
        userRemindConfig.setOpenId(openId);
        //默认提醒开启
        userRemindConfig.setIsRemind(1);
        //默认提醒模式 1时间间隔 0 自定义时间
        userRemindConfig.setRemindMode(1);
        userRemindConfig.setDeleteFlag("0");
        //新增配置
        this.save(userRemindConfig);
        //计算出定时配置  得到用户设置的喝水次数 按照开始时间加上时间间隔 依次添加时间
        addScheduler(openId, userRemindConfig);
        return userRemindConfig;
    }

    /**
     * UserRemindConfigServiceImpl:: addScheduler
     * <p>新增用户的定时器配置
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param openId           小程序的唯一性凭证
     * @param userRemindConfig 用户的配置信息
     */
    private void addScheduler(String openId, UserRemindConfig userRemindConfig) {
        List<Date> timeList = getRemindTimeList(openId, userRemindConfig);
        //新增用户提醒数据
        for (Date date : timeList) {
            UserRemindData remindData = new UserRemindData();
            String remindDataId = UUID.randomUUID().toString().replace("-", "");
            remindData.setId(remindDataId);
            remindData.setOpenId(openId);
            remindData.setRemindTime(date);
            remindData.setDeleteFlag("0");
            userRemindDataService.save(remindData);
            //配置定时器 //转换成cron  0 s% s% * * ?
            String[] timeStingArr = TimeUtils.getFormatTime(date).split(":");
            String cronString = "0 %S %S * * ?";
            String format = String.format(cronString, timeStingArr[1], timeStingArr[0]);
            schedulerUtilService.schedulerAdd(remindDataId, UserRemindJob.class, format, openId);
        }
    }

    /**
     * UserRemindConfigServiceImpl:: getRemindTimeList
     * <p>得到用户提醒间隔时间
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param openId           小程序的用户微信的凭证
     * @param userRemindConfig 用户的定时的配置.
     * @return List<Date>  用户提醒时间的集合
     */
    private List<Date> getRemindTimeList(String openId, UserRemindConfig userRemindConfig) {
        WaterNumConfig numConfigByOpenId = waterNumConfigService.getNumConfigByOpenId(openId);
        int configNum = numConfigByOpenId.getNum();
        Date startTime = userRemindConfig.getStartTime();
        Date endTime = userRemindConfig.getEndTime();
        int remindInterval = userRemindConfig.getRemindInterval();
        int isLunch = userRemindConfig.getIsLunch();
        Date lunchStartTimeTime = userRemindConfig.getLunchStartTime();
        Date lunchEndTime = userRemindConfig.getLunchEndTime();
        Calendar calendar = Calendar.getInstance();
        //设置起时间
        calendar.setTime(startTime);
        List<Date> timeList = new ArrayList<>();
        timeList.add(startTime);
        //去掉按照用户开始时间往后按照时间间隔往后推，午休模式继续往后推
        for (int i = 0; i < (configNum - 1); i++) {
            calendar.add(Calendar.MINUTE, remindInterval);
            //判断是否在开始和结束时间的范围内
            if (calendar.getTime().after(endTime)) {
                break;
            }
            //判断是否在午休时间内
            if (isLunch == 1 && calendar.getTime().after(lunchStartTimeTime) && calendar.getTime().before(lunchEndTime)) {
                i--;
                continue;
            }
            timeList.add(calendar.getTime());
        }
        return timeList;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public UserRemindConfig getRemindConfig(String openId) {
        LambdaQueryWrapper<UserRemindConfig> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(UserRemindConfig::getOpenId, openId);
        return this.getOne(cQuery);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public UserRemindConfig updateRemindConfig(String openId, UserRemindConfig userRemindConfig) {
        //获取用户的之前的订阅信息
        LambdaQueryWrapper<UserRemindData> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(UserRemindData::getOpenId, openId);
        List<UserRemindData> beforeRemindDataList = userRemindDataService.list(cQuery);
        //删掉订阅的信息
        List<String> ids = new ArrayList<>();
        for (UserRemindData beforeRemindData : beforeRemindDataList) {
            ids.add(beforeRemindData.getId());
        }
        userRemindDataService.removeByIds(ids);
        //新增订阅
        addScheduler(openId, userRemindConfig);
        this.updateById(userRemindConfig);
        //删掉定时器
        for (UserRemindData beforeRemindData : beforeRemindDataList) {
            schedulerUtilService.schedulerDelete(beforeRemindData.getId());
        }
        return userRemindConfig;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public int openRemindConfig(String openId) {
        UserRemindConfig remindConfig = getRemindConfigByOpenId(openId);
        if (remindConfig == null) {
            return 0;
        }
        remindConfig.setIsRemind(1);
        this.updateById(remindConfig);
        LambdaQueryWrapper<UserRemindData> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(UserRemindData::getOpenId, openId);
        List<UserRemindData> remindDataList = userRemindDataService.list(cQuery);
        for (UserRemindData beforeRemindData : remindDataList) {
            schedulerUtilService.schedulerResume(beforeRemindData.getId());
        }
        return 0;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public int closeRemindConfig(String openId) {
        UserRemindConfig remindConfig = getRemindConfigByOpenId(openId);
        if (remindConfig == null) {
            return 0;
        }
        remindConfig.setIsRemind(0);
        this.updateById(remindConfig);
        LambdaQueryWrapper<UserRemindData> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(UserRemindData::getOpenId, openId);
        List<UserRemindData> remindDataList = userRemindDataService.list(cQuery);
        for (UserRemindData beforeRemindData : remindDataList) {
            schedulerUtilService.schedulerPause(beforeRemindData.getId());
        }
        return 0;
    }
}
