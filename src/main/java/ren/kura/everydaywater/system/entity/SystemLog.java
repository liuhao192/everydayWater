package ren.kura.everydaywater.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: SystemLog.java
 * <p>描述: 系统日志表
 * <p>HISTORY: 2020/10/10 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/10 23:13
 */
@Data
@TableName("t_system_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SystemLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private long id;

    private int logType;

    private String logContent;

    private long operateType;

    private String userid;

    private String username;

    private String ip;

    private String method;

    private String requestParam;

    private String requestUrl;

    private String requestType;

    private long costTime;

    private String createBy;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @TableField(update="now()")
    private Date updateTime;

    private String updateBy;


}
