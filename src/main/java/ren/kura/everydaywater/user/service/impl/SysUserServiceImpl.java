package ren.kura.everydaywater.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ren.kura.everydaywater.user.entity.SysUser;
import ren.kura.everydaywater.user.mapper.SysUserMapper;
import ren.kura.everydaywater.user.service.ISysUserService;
import ren.kura.everydaywater.water.service.IWaterNumConfigService;
import ren.kura.everydaywater.water.service.IWaterUserCountService;

import java.util.UUID;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: SysUserServiceImpl.java
 * <p>描述: 系统用户的实现类
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 0:05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private IWaterNumConfigService waterNumConfigService;
    @Autowired
    private IWaterUserCountService waterUserCountService;
    /**
     * {@inheritDoc}
     */
    @Override
    public SysUser getUserByOpenId(String openId) {
        LambdaQueryWrapper<SysUser> cQuery = new LambdaQueryWrapper<>();
        cQuery.eq(SysUser::getOpenId,openId);
        return this.getOne(cQuery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public SysUser addUser(String openId) {
        SysUser sysUser = new SysUser();
        String id = UUID.randomUUID().toString().replace("-", "");
        sysUser.setId(id);
        sysUser.setGender(0);
        sysUser.setStatus(1);
        sysUser.setOpenId(openId);
        sysUser.setDeleteFlag("0");
        this.save(sysUser);
        //初始化每日喝水得数量
        waterNumConfigService.initWaterNumConfig(openId);
        //初始化历史统计信息表
        waterUserCountService.initWaterUserCount(openId);
        return sysUser;
    }
}
