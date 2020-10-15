package ren.kura.everydaywater.water.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: WaterNumConfig.java
 * <p>描述: 喝水数目的配置实体类
 * <p>HISTORY: 2020/10/10 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/10 23:13
 */
@Data
@TableName("water_num_config")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class WaterNumConfig {

    @TableId(type = IdType.UUID)
    private String id;

    private String openId;

    private int num;

    private int mlNum;

    private int status;

    private String creator;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String updatePerson;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(update="now()")
    private Date updateDate;

    private String deleteFlag;


}
