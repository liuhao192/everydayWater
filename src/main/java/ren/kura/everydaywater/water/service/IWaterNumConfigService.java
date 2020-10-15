package ren.kura.everydaywater.water.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ren.kura.everydaywater.water.entity.WaterNumConfig;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: IWaterNumConfigService.java
 * <p>描述: 喝水的数量的接口
 * <p>HISTORY: 2020/10/12 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 0:04
 */
public interface IWaterNumConfigService extends IService<WaterNumConfig> {
/**
 *  IWaterNumConfigService:: initWaterNumConfig
 *  <p>初始化用户的喝水数量配置的信息
 *  <p>HISTORY: 2020/10/12 liuha : Created.
 *  @param    openId  微信用户唯一性凭证
 *  @return   WaterNumConfig  数量配置的信息
 */
    WaterNumConfig initWaterNumConfig(String openId);
/**
 *  IWaterNumConfigService:: getNumConfigByOpenId
 *  <p>Sets the flying object to the speed specified.
 *  <p>HISTORY: 2020/10/12 liuha : Created.
 *  @param    openId  微信用户唯一性凭证
 *  @return   WaterNumConfig  数量配置的信息
 */
    WaterNumConfig getNumConfigByOpenId(String openId);
}
