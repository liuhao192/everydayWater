package ren.kura.everydaywater.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ren.kura.everydaywater.user.entity.SysUser;
import ren.kura.everydaywater.water.entity.WaterNumConfig;
import ren.kura.everydaywater.water.mapper.WaterNumConfigMapper;
import ren.kura.everydaywater.water.service.IWaterNumConfigService;

import java.util.UUID;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: WaterNumConfigServiceImpl.java
 * <p>描述: 喝水的数量的实现类
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 0:05
 */
@Service
public class WaterNumConfigServiceImpl extends ServiceImpl<WaterNumConfigMapper, WaterNumConfig> implements IWaterNumConfigService {
    private final static int DEFAULT_WATER_NUM=8;

    private final static int DEFAULT_WATER_Ml_NUM=250*8;
    /**
     * {@inheritDoc}
     */
    @Override
    public WaterNumConfig initWaterNumConfig(String openId) {
        WaterNumConfig waterNumConfig = new WaterNumConfig();
        String id = UUID.randomUUID().toString().replace("-", "");
        waterNumConfig.setId(id);
        waterNumConfig.setOpenId(openId);
        waterNumConfig.setNum(DEFAULT_WATER_NUM);
        waterNumConfig.setMlNum(DEFAULT_WATER_Ml_NUM);
        waterNumConfig.setStatus(1);
        waterNumConfig.setDeleteFlag("0");
        this.save(waterNumConfig);
        return waterNumConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaterNumConfig getNumConfigByOpenId(String openId) {
        LambdaQueryWrapper<WaterNumConfig> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(WaterNumConfig::getOpenId,openId);
        return this.getOne(cQuery);
    }
}
