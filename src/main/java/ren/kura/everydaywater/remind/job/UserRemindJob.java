package ren.kura.everydaywater.remind.job;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ren.kura.everydaywater.common.utils.TimeUtils;
import ren.kura.everydaywater.remind.entity.UserRemindLog;
import ren.kura.everydaywater.remind.entity.UserRemindSubscribe;
import ren.kura.everydaywater.remind.service.IUserRemindLogService;
import ren.kura.everydaywater.remind.service.IUserRemindSubscribeService;
import ren.kura.everydaywater.water.entity.WaterNumConfig;
import ren.kura.everydaywater.water.entity.WaterUserDay;
import ren.kura.everydaywater.water.service.IWaterNumConfigService;
import ren.kura.everydaywater.water.service.IWaterUserDayService;
import ren.kura.everydaywater.wechat.dto.ParamesAPI;
import ren.kura.everydaywater.wechat.dto.WeChatUniformDTO;
import ren.kura.everydaywater.wechat.dto.WeChatUniformTemplateDTO;
import ren.kura.everydaywater.wechat.service.IWeChatService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: UserRemindJob.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/24 liuha : Created
 * <p>公司信息: ************公司 *********部
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/24 23:23
 */
@Slf4j
public class UserRemindJob implements Job {

    @Autowired
    private IWaterUserDayService waterUserDayService;
    @Autowired
    private IWaterNumConfigService waterNumConfigService;
    @Autowired
    private IWeChatService weChatService;
    @Autowired
    private ParamesAPI paramesAPI;
    @Autowired
    private IUserRemindLogService userRemindLogService;
    @Autowired
    private IUserRemindSubscribeService userRemindSubscribeService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String openId = (String) dataMap.get("parameter");
        log.info("定时器的配置的用户的openId:{}", openId);
        //生成用户的提醒信息发送
        WeChatUniformDTO wxMssVo = new WeChatUniformDTO();
        wxMssVo.setTouser(openId);
        WaterUserDay waterUserDay = waterUserDayService.getUserTodayByOpenId(openId);

        //已经达标之间返回
        if (waterUserDay.getStandard() == 1) {
            log.info("已经达标 waterUserDay{}", waterUserDay);
            return;
        }

        //查询用户是否定订阅了模板信息
        UserRemindSubscribe subscribe = userRemindSubscribeService.getSubscribeByOpenIdAndTemplateId(openId, paramesAPI.getReminderTemplateId());
        if (StringUtils.isEmpty(subscribe) || subscribe.getIsSubscribe() == 0) {
            log.info("未订阅通知模板");
            return;
        }
        //剩余的喝水的次数
        int num = waterUserDay.getNum();
        String thingNum = ++num + "杯";

        //根据喝水的次数显示不同的提示语
        WaterNumConfig waterNumConfig = waterNumConfigService.getNumConfigByOpenId(openId);
        int configNum = waterNumConfig.getNum();
        int target = configNum - num;
        String remark = "";
        if (target == 0) {
            String remarks = "距离目标还有%S杯,时刻保存自己水分充足";
            remark = String.format(remarks, target);
        } else {
            remark = "喝完这杯了,今天就达标了O_O";
        }

        //组装通知模板的信息
        Map<String, WeChatUniformTemplateDTO> mapTemplate = new HashMap<>(3);
        mapTemplate.put("thing5", new WeChatUniformTemplateDTO("每日喝水提醒"));
        mapTemplate.put("time2", new WeChatUniformTemplateDTO(TimeUtils.getFormatTime()));
        mapTemplate.put("thing6", new WeChatUniformTemplateDTO(thingNum));
        mapTemplate.put("thing3", new WeChatUniformTemplateDTO(remark));
        wxMssVo.setData(mapTemplate);
        String infoId = UUID.randomUUID().toString().replace("-", "");
        wxMssVo.setPage(paramesAPI.getPage() + "?infoId=" + infoId);

        //发送信息
        int isSuccess = weChatService.sandUniformMessage(wxMssVo, paramesAPI.getReminderTemplateId(), openId);

        //新增提醒日志
        saveUserRemindLog(openId, wxMssVo, infoId, isSuccess);

        //将一次性的订阅标记为取消订阅
        subscribe.setIsSubscribe(0);
        userRemindSubscribeService.updateById(subscribe);
    }

    /**
     *  UserRemindJob:: saveUserRemindLog
     *  <p>保存用户提醒的日志信息
     *  <p>HISTORY: 2020/11/3 liuha : Created.
     *  @param    openId  小程序的用户唯一性凭证
     *  @param    wxMssVo   发送的信息内容
     *  @param    infoId  信息的id,用于前端是否显示打卡的按钮
     *  @param    isSuccess  信息是否发送成功
     */
    private void saveUserRemindLog(String openId, WeChatUniformDTO wxMssVo, String infoId, int isSuccess) {
        UserRemindLog userRemindLog = new UserRemindLog();
        userRemindLog.setInfoId(infoId);
        userRemindLog.setIsRead(0);
        userRemindLog.setOpenId(openId);
        userRemindLog.setRemindTime(new Date());
        userRemindLog.setRemindContent(new Gson().toJson(wxMssVo));
        userRemindLog.setIsSuccess(isSuccess);
        userRemindLog.setDeleteFlag("0");
        userRemindLogService.save(userRemindLog);
    }
}
