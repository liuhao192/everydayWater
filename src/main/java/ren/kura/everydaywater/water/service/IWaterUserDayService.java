package ren.kura.everydaywater.water.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ren.kura.everydaywater.water.entity.WaterUserDay;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: IWaterUserDayService.java
 * <p>描述: 喝水的数量的接口
 * <p>HISTORY: 2020/10/12 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 0:04
 */
public interface IWaterUserDayService extends IService<WaterUserDay> {

/**
 *  IWaterUserDayService:: getUserTodayByOpenId
 *  <p>根据openId得到用户今天喝水的信息
 *  <p>HISTORY: 2020/10/12 liuha : Created.
 *  @param    openId  微信用户模块的唯一性id
 *  @return   WaterUserDay   返回用户当天的喝水的情况
 */
    WaterUserDay getUserTodayByOpenId(String openId);
/**
 *  IWaterUserDayService:: addOneWater
 *  <p>添加一次今天的喝水次数
 *  <p>HISTORY: 2020/10/12 liuha : Created.
 *  @param    openId  微信用户模块的唯一性id
 *  @return   WaterUserDay  返回用户当天的喝水的情况
 */
    WaterUserDay addOneWater(String openId);

}
