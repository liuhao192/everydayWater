package ren.kura.everydaywater.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ren.kura.everydaywater.common.utils.TimeUtils;
import ren.kura.everydaywater.water.entity.WaterNumConfig;
import ren.kura.everydaywater.water.entity.WaterUserDay;
import ren.kura.everydaywater.water.mapper.WaterUserDayMapper;
import ren.kura.everydaywater.water.service.IWaterNumConfigService;
import ren.kura.everydaywater.water.service.IWaterUserCountService;
import ren.kura.everydaywater.water.service.IWaterUserDayService;
import ren.kura.everydaywater.water.service.IWaterUserLogService;

import java.util.UUID;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: WaterUserDayServiceImpl.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/12 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 11:23
 */
@Service
public class WaterUserDayServiceImpl extends ServiceImpl<WaterUserDayMapper, WaterUserDay> implements IWaterUserDayService {

    @Autowired
    private IWaterNumConfigService waterNumConfigService;
    @Autowired
    private IWaterUserCountService waterUserCountService;
    @Autowired
    private IWaterUserLogService waterUserLogService;
    /**
     * {@inheritDoc}
     */
    @Override
    public WaterUserDay getUserTodayByOpenId(String openId) {
        LambdaQueryWrapper<WaterUserDay> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(WaterUserDay::getOpenId, openId);
        cQuery.eq(WaterUserDay::getDayDate, TimeUtils.getFormatDate());
        WaterUserDay waterUserDay = this.getOne(cQuery);
        if (StringUtils.isEmpty(waterUserDay)) {
            return new WaterUserDay().setNum(0).setOpenId(openId);
        }
        return waterUserDay;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaterUserDay addOneWater(String openId) {
        //查询今天的是否存在数据
        WaterUserDay waterUserDay = getUserTodayByOpenId(openId);
        waterUserLogService.addOneWaterLog(openId);
        if (StringUtils.isEmpty(waterUserDay.getId())) {
            WaterUserDay newWaterUserDay = new WaterUserDay();
            String id = UUID.randomUUID().toString().replace("-", "");
            newWaterUserDay.setId(id);
            newWaterUserDay.setOpenId(openId);
            newWaterUserDay.setNum(1);
            newWaterUserDay.setDayDate(TimeUtils.getFormatDate());
            newWaterUserDay.setDeleteFlag("0");
            newWaterUserDay.setStandard(isStandard(openId, 1));
            this.save(newWaterUserDay);
            return newWaterUserDay;
        } else {
            int num = waterUserDay.getNum();
            waterUserDay.setNum(++num);
            int standard = waterUserDay.getStandard();
            //未达标,判断是否达标
            if (standard == 0) {
                waterUserDay.setStandard(isStandard(openId, num));
            }
            this.updateById(waterUserDay);
            return waterUserDay;
        }
    }

/**
 *  WaterUserDayServiceImpl:: isStandard
 *  <p>本次是否达标
 *  <p>HISTORY: 2020/10/23 liuha : Created.
 *  @param    openId  微信的用户凭证
 *  @param    num  本次喝水次数
 *  @return   int  The maximum speed of the object.

 */
    private int isStandard(String openId, int num) {
        WaterNumConfig waterNumConfig = waterNumConfigService.getNumConfigByOpenId(openId);
        //达标
        if (num >= waterNumConfig.getNum()) {
            //新增达标天数
            waterUserCountService.updateStandardDay(openId);
            return 1;
        }
        return 0;
    }
    


}
