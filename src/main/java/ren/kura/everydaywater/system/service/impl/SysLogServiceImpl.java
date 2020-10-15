package ren.kura.everydaywater.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ren.kura.everydaywater.system.entity.SystemLog;
import ren.kura.everydaywater.system.mapper.SysLogMapper;
import ren.kura.everydaywater.system.service.ISysLogService;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: SysLogServiceImpl.java
 * <p>描述: 系统日志的实现类
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 0:05
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SystemLog> implements ISysLogService {
}
