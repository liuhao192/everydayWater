package ren.kura.everydaywater.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ren.kura.everydaywater.user.entity.SysUser;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: null.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/12 liuha : Created
 * <p>公司信息: ************公司 *********部
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 0:04
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * IWaterNumConfigService:: getUserByOpenId
     * <p>根据openId查询用户信息是否存在
     * <p>HISTORY: 2020/10/12 liuha : Created.
     *
     * @param openId 微信用户的唯一凭证
     * @return SysUser  系统的用户信息.
     */
    SysUser getUserByOpenId(String openId);

    /**
     * IWaterNumConfigService:: addUser
     * <p>新增用户信息;同时初始化用户的配置信息
     * <p>HISTORY: 2020/10/12 liuha : Created.
     *
     * @param openId 微信用户的唯一凭证
     * @return SysUser  新增用户信息
     */
    SysUser addUserAndInitConfig(String openId);
}
