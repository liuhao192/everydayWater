package ren.kura.everydaywater.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ren.kura.everydaywater.system.entity.SystemLog;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: SysLogMapper.java
 * <p>描述: 系统日志的Mapper接口
 * <p>HISTORY: 2020/10/11 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/11 16:48
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SystemLog> {
}
