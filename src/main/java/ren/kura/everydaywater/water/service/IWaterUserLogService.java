package ren.kura.everydaywater.water.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ren.kura.everydaywater.water.entity.WaterUserLog;

import java.util.List;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: IWaterUserLogService.java
 * <p>描述:  用户的喝水的日志表的Service的接口
 * <p>HISTORY: 2020/10/12 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 14:46
 */
public interface IWaterUserLogService extends IService<WaterUserLog> {
    /**
     * IWaterUserLogService:: addOneWaterLog
     * <p>新增一次用户一次喝水的日志
     * <p>HISTORY: 2020/10/12 liuha : Created.
     *
     * @param openId 微信用户模块的唯一性id
     * @return WaterUserLog  返回本次用户新增的一次喝水日志的信息
     */
    WaterUserLog addOneWaterLog(String openId);

    /**
     * IWaterUserLogService:: getUserLogsByOpenId
     * <p>Sets the flying object to the speed specified.
     * <p>HISTORY: 2020/10/12 liuha : Created.
     *
     * @param openId 微信用户模块的唯一性id
     * @return List<WaterUserLog> 返回用户全部的喝水的日志
     */
    List<WaterUserLog> getUserLogsByOpenId(String openId);

    //TODO 喝水日志的分页查询
}
