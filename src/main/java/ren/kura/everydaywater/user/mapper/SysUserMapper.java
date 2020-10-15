package ren.kura.everydaywater.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ren.kura.everydaywater.user.entity.SysUser;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: SysUserMapper.java
 * <p>描述:系统用户表的Mapper接口
 * <p>HISTORY: 2020/10/11 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/11 16:48
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
