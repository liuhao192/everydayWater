package ren.kura.everydaywater.remind.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ren.kura.everydaywater.common.aspect.annotation.AutoLog;
import ren.kura.everydaywater.common.utils.Result;
import ren.kura.everydaywater.remind.entity.UserRemindConfig;
import ren.kura.everydaywater.remind.entity.UserRemindLog;
import ren.kura.everydaywater.remind.entity.UserRemindSubscribe;
import ren.kura.everydaywater.remind.service.IUserRemindConfigService;
import ren.kura.everydaywater.remind.service.IUserRemindLogService;
import ren.kura.everydaywater.remind.service.IUserRemindSubscribeService;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: UserRemindController.java
 * <p>描述: 用户提醒的配置表
 * <p>HISTORY: 2020/10/19 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/19 12:05
 */
@RestController
@RequestMapping("/api/remind")
@Slf4j
public class UserRemindController {

    @Autowired
    private IUserRemindConfigService userRemindConfigService;

    @Autowired
    private IUserRemindSubscribeService userRemindSubscribeService;

    @Autowired
    private IUserRemindLogService userRemindLogService;

    @AutoLog(value = "新增用户的定时任务")
    @PostMapping(value = "/config/interval/openid/{openid}")
    public Result addRemindConfig(@PathVariable("openid") String openId, @RequestBody UserRemindConfig userRemindConfig) {
        //新增时间间隔的体现
        log.info("新增用户的定时任务  userRemindConfig:{}开始", new Gson().toJson(userRemindConfig));
        userRemindConfigService.addIntervalRemindConfig(openId, userRemindConfig);
        log.info("新增用户的定时任务  userRemindConfig:{}结束", new Gson().toJson(userRemindConfig));
        return Result.putDataOk("data", userRemindConfig);
    }

    @AutoLog(value = "打开用户的是否提醒的配置")
    @PutMapping(value = "/config/open/openid/{openid}")
    public Result openRemindConfig(@PathVariable("openid") String openId) {
        log.info("打开用户的是否提醒的配置  openid:{}开始", openId);
        userRemindConfigService.openRemindConfig(openId);
        log.info("打开用户的是否提醒的配置  结束");
        return Result.ok("开启成功");
    }

    @AutoLog(value = "关闭用户的是否提醒的配置")
    @PutMapping(value = "/config/close/openid/{openid}")
    public Result closeRemindConfig(@PathVariable("openid") String openId) {
        log.info("关闭用户的是否提醒的配置  openid:{}开始", openId);
        userRemindConfigService.closeRemindConfig(openId);
        log.info("关闭用户的是否提醒的配置  openid:{}开始", openId);
        return Result.ok("关闭成功");
    }

    @AutoLog(value = "更新用户的是否允许通知配置")
    @PutMapping(value = "/config/interval/openid/{openid}")
    public Result updateRemindConfig(@PathVariable("openid") String openId, @RequestBody UserRemindConfig userRemindConfig) {
        log.info("更新用户的定时任务  userRemindConfig:{}开始", new Gson().toJson(userRemindConfig));
        userRemindConfigService.updateRemindConfig(openId, userRemindConfig);
        log.info("更新用户的定时任务  userRemindConfig:{}结束", new Gson().toJson(userRemindConfig));
        return Result.putDataOk("data", userRemindConfig);
    }

    @AutoLog(value = "获取用户的定时任务")
    @GetMapping(value = "/config/interval/openid/{openid}")
    public Result getRemindConfig(@PathVariable("openid") String openId) {
        log.info("获取用户的定时任务  userRemindConfig:{}开始", openId);
        UserRemindConfig userRemindConfig = userRemindConfigService.getRemindConfig(openId);
        log.info("获取用户的定时任务  userRemindConfig:{}结束", new Gson().toJson(userRemindConfig));
        return Result.putDataOk("data", userRemindConfig);
    }


    @AutoLog(value = "用户订阅模板信息")
    @PostMapping(value = "/subscribe/openid/{openid}/templateid/{templateId}")
    public Result getRemindConfig(@PathVariable("openid") String openId, @PathVariable("templateId") String templateId) {
        log.info("用户订阅模板信息  openId:{} templateId:{} 开始", openId, templateId);
        UserRemindSubscribe userRemindSubscribe = userRemindSubscribeService.addOrUpdateSubscribe(openId, templateId);
        log.info("用户订阅模板信息  userRemindSubscribe:{}结束", new Gson().toJson(userRemindSubscribe));
        return Result.putDataOk("data", userRemindSubscribe);
    }

    @AutoLog(value = "查询当前通知的消息是否内容")
    @GetMapping(value = "/log/infoid/{infoId}")
    public Result getUserRemindLogByInfoId(@PathVariable("infoId") String infoId) {
        log.info("查询当前通知的消息是否内容 infoId:{} 开始", infoId);
        UserRemindLog userRemindLog = userRemindLogService.getUserRemindLogByInfoId(infoId);
        log.info("查询当前通知的消息是否内容  userRemindLog:{}结束", new Gson().toJson(userRemindLog));
        return Result.putDataOk("data", userRemindLog);
    }

    @AutoLog(value = "更新当前消息为已读")
    @PutMapping(value = "/log/read/infoid/{infoId}")
    public Result updateReadByInfoId(@PathVariable("infoId") String infoId) {
        log.info("更新当前消息为已读  infoId:{} 开始", infoId);
        UserRemindLog userRemindLog = userRemindLogService.updateReadByInfoId(infoId);
        log.info("更新当前消息为已读  userRemindLog:{}结束", new Gson().toJson(userRemindLog));
        return Result.putDataOk("data", userRemindLog);
    }

}
