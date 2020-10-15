package ren.kura.everydaywater.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ren.kura.everydaywater.water.entity.WaterUserLog;
import ren.kura.everydaywater.water.mapper.WaterUserLogMapper;
import ren.kura.everydaywater.water.service.IWaterUserLogService;

import java.util.Date;
import java.util.List;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: WaterUserLogServiceImpl.java
 * <p>描述: 喝水日志表的实现类
 * <p>HISTORY: 2020/10/12 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 14:46
 */
@Service
public class WaterUserLogServiceImpl extends ServiceImpl<WaterUserLogMapper, WaterUserLog> implements IWaterUserLogService {
    /**
     * {@inheritDoc}
     */
    @Override
    public WaterUserLog addOneWaterLog(String openId) {
        WaterUserLog waterUserLog =new WaterUserLog();
        waterUserLog.setOpenId(openId);
        waterUserLog.setNum(1);
        waterUserLog.setDrinkTime(new Date());
        waterUserLog.setDeleteFlag("0");
        this.save(waterUserLog);
        return waterUserLog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WaterUserLog> getUserLogsByOpenId(String openId) {
        QueryWrapper<WaterUserLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",openId);
        //按照drink_time字段排序
        queryWrapper.orderByDesc("drink_time");
        return this.list(queryWrapper);
    }
}
