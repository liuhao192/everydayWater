package ren.kura.everydaywater.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ren.kura.everydaywater.water.entity.WaterUserCount;
import ren.kura.everydaywater.water.mapper.WaterUserCountMapper;
import ren.kura.everydaywater.water.service.IWaterUserCountService;

import java.util.UUID;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: null.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/12 liuha : Created
 * <p>公司信息: ************公司 *********部
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 14:26
 */
@Service
public class WaterUserCountServiceImpl extends ServiceImpl<WaterUserCountMapper, WaterUserCount> implements IWaterUserCountService {
    /**
     * {@inheritDoc}
     */
    @Override
    public WaterUserCount updateUserDrink(String openId) {
        WaterUserCount waterUserCount = getUserCountByOpenId(openId);
        int drinkDay = waterUserCount.getDrinkDay();
        int drinkNum = waterUserCount.getDrinkNum();
        waterUserCount.setDrinkDay(++drinkDay);
        waterUserCount.setDrinkNum(++drinkNum);
        this.updateById(waterUserCount);
        return waterUserCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaterUserCount updateUserDrinkNum(String openId) {
        WaterUserCount waterUserCount = getUserCountByOpenId(openId);
        int drinkNum = waterUserCount.getDrinkNum();
        waterUserCount.setDrinkNum(++drinkNum);
        this.updateById(waterUserCount);
        return waterUserCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaterUserCount updateStandardDay(String openId) {
        WaterUserCount waterUserCount = getUserCountByOpenId(openId);
        int standardDay = waterUserCount.getStandardDay();
        waterUserCount.setStandardDay(++standardDay);
        this.updateById(waterUserCount);
        return waterUserCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaterUserCount getUserCountByOpenId(String openId) {
        LambdaQueryWrapper<WaterUserCount> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(WaterUserCount::getOpenId, openId);
        return this.getOne(cQuery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaterUserCount initWaterUserCount(String openId) {
        WaterUserCount waterUserCount = new WaterUserCount();
        String id = UUID.randomUUID().toString().replace("-", "");
        waterUserCount.setId(id);
        waterUserCount.setOpenId(openId);
        waterUserCount.setDrinkDay(0);
        waterUserCount.setDrinkNum(0);
        waterUserCount.setStandardDay(0);
        waterUserCount.setDeleteFlag("0");
        this.save(waterUserCount);
        return waterUserCount;

    }
}
