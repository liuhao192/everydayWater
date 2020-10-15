package ren.kura.everydaywater.water.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ren.kura.everydaywater.water.entity.WaterUserCount;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: IWaterUserCountService.java
 * <p>描述: 用户统计信息表Service接口
 * <p>HISTORY: 2020/10/12 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 14:25
 */
public interface IWaterUserCountService extends IService<WaterUserCount> {

/**
 *  IWaterUserCountService:: updateUserDrink
 *  <p>更新用户统计信息喝水天数，喝水的次数
 *  <p>HISTORY: 2020/10/12 liuha : Created.
 *  @param    openId  wx用户唯一性的凭证
 *  @return   WaterUserCount  返回用户的统计信息
 */
    WaterUserCount updateUserDrink(String openId);
/**
 *  IWaterUserCountService:: updateUserDrinkNum
 *  <p>更新用户统计信息喝水次数
 *  <p>HISTORY: 2020/10/12 liuha : Created.
 *  @param    openId  wx用户唯一性的凭证
 *  @return   WaterUserCount  返回用户的统计信息
 */
    WaterUserCount updateUserDrinkNum(String openId);
/**
 *  IWaterUserCountService:: updateStandardDay
 *  <p>更新用户统计信息喝水天数
 *  <p>HISTORY: 2020/10/12 liuha : Created.
 *  @param    openId  wx用户唯一性的凭证
 *  @return   WaterUserCount  返回用户的统计信息
 */
    WaterUserCount updateStandardDay(String openId);
/**
 *  IWaterUserCountService:: getUserCountsByOpenId
 *  <p>根据用户的openId得到用户的统计信息
 *  <p>HISTORY: 2020/10/12 liuha : Created.
 *  @param    openId  wx用户唯一性的凭证
 *  @return   WaterUserCount  返回用户的统计信息
 */
    WaterUserCount getUserCountByOpenId(String openId);
/**
 *  IWaterUserCountService:: initWaterUserCount
 *  <p>初始化用户的统计数据表
 *  <p>HISTORY: 2020/10/13 liuha : Created.
 *  @param    openId  用户唯一性的id
 *  @return   返回用户统计西悉尼
 */
    WaterUserCount initWaterUserCount(String openId);
}
