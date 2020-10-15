package ren.kura.everydaywater.water.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ren.kura.everydaywater.common.aspect.annotation.AutoLog;
import ren.kura.everydaywater.common.utils.Result;
import ren.kura.everydaywater.water.dto.WaterDaily;
import ren.kura.everydaywater.water.entity.WaterNumConfig;
import ren.kura.everydaywater.water.entity.WaterUserCount;
import ren.kura.everydaywater.water.entity.WaterUserDay;
import ren.kura.everydaywater.water.entity.WaterUserLog;
import ren.kura.everydaywater.water.service.IWaterNumConfigService;
import ren.kura.everydaywater.water.service.IWaterUserCountService;
import ren.kura.everydaywater.water.service.IWaterUserDayService;
import ren.kura.everydaywater.water.service.IWaterUserLogService;

import java.util.List;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: WaterController.java
 * <p>描述: 喝水的controller类
 * <p>HISTORY: 2020/10/12 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 10:57
 */
@RestController
@RequestMapping("/api/water")
@Slf4j
public class WaterController {

    @Autowired
    private IWaterNumConfigService waterNumConfigService;
    @Autowired
    private IWaterUserDayService waterUserDayService;
    @Autowired
    private IWaterUserCountService waterUserCountService;
    @Autowired
    private IWaterUserLogService waterUserLogService;

    @AutoLog(value = "查询每日的喝水信息")
    @GetMapping(value = "/daily/openid/{openid}")
    public Result getWaterDaily(@PathVariable("openid") String openId ){
        log.info("查询每日喝水信息 用户凭证 openId:{} 开始", openId);
        WaterDaily waterDailyInfo = new WaterDaily();
        //今日目标
        WaterNumConfig waterNumConfig= waterNumConfigService.getNumConfigByOpenId(openId);
        waterDailyInfo.setGoalNum(waterNumConfig.getNum());
        //今日完成
        WaterUserDay waterUserDay=waterUserDayService.getUserTodayByOpenId(openId);
        waterDailyInfo.setCompletedNum(waterUserDay.getNum());
        //TODO 下次提醒日期
        log.info("查询每日喝水信息  waterDailyInfo:{}结束", new Gson().toJson(waterDailyInfo));
        return Result.putDataOk("daily",waterDailyInfo);
    }


    @AutoLog(value = "新增一次喝水信息")
    @PostMapping(value = "/drink/openid/{openid}")
    public Result addOneWater(@PathVariable("openid") String openId){
        log.info("新增一次喝水信息,用户凭证 openId:{} 开始", openId);
        WaterUserDay waterUserDay= waterUserDayService.addOneWater(openId);
        if(StringUtils.isEmpty(waterUserDay)){
            return Result.error("新增失败");
        }
        log.info("新增一次喝水信息  waterUserDay:{}结束", new Gson().toJson(waterUserDay));
        return Result.putDataOk("data",waterUserDay);
    }


    @AutoLog(value = "查询历史统计信息")
    @GetMapping(value = "/count/openid/{openid}")
    public Result getWaterDrinks(@PathVariable("openid") String openId){
        log.info("查询历史统计信息,用户凭证 openId:{} 开始", openId);
        WaterUserCount waterUserCount= waterUserCountService.getUserCountByOpenId(openId);
        if(StringUtils.isEmpty(waterUserCount)){
            return Result.error("获取失败");
        }
        log.info("查询历史统计信息 waterUserCount:{}结束", new Gson().toJson(waterUserCount));
        return Result.putDataOk("data",waterUserCount);
    }

    @AutoLog(value = "查询我的喝水的配置信息")
    @GetMapping(value = "/config/openid/{openid}")
    public Result getNumConfig(@PathVariable("openid") String openId){
        log.info("查询我的喝水的配置信息,用户凭证 openId:{} 开始", openId);
        WaterNumConfig waterNumConfig= waterNumConfigService.getNumConfigByOpenId(openId);
        if(StringUtils.isEmpty(waterNumConfig)){
            return Result.error("获取失败");
        }
        log.info("查询我的喝水的配置信息 waterNumConfig:{}结束", new Gson().toJson(waterNumConfig));
        return Result.putDataOk("data",waterNumConfig);
    }

    @AutoLog(value = "更新我的喝水配置信息")
    @PutMapping(value = "/config")
    public Result updateNumConfig(@RequestBody WaterNumConfig waterNumConfig){
        log.info("更新我的喝水配置信息,更新信息 WaterNumConfig:{} 开始", new Gson().toJson(waterNumConfig));
        waterNumConfigService.updateById(waterNumConfig);
        log.info("更新我的喝水配置信息 waterNumConfig:{}结束", new Gson().toJson(waterNumConfig));
        return Result.ok("更新成功");
    }

    @AutoLog(value = "查询我的喝水日志信息")
    @GetMapping(value = "/log/openid/{openid}")
    public Result getUserLog(@PathVariable("openid") String openId){
        log.info("查询我的喝水日志信息,用户凭证 openId:{} ", openId);
        List<WaterUserLog> userLogs= waterUserLogService.getUserLogsByOpenId(openId);
        log.info("查询我的喝水日志信息 userLogs:{}结束", new Gson().toJson(userLogs));
        return Result.putDataOk("data",userLogs);
    }

}
