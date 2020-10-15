package ren.kura.everydaywater.user.entity;


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
 * <p>文件名称: SysUser.java
 * <p>描述: 系统用户表
 * <p>HISTORY: 2020/10/10 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/10 23:13
 */
@Data
@TableName("t_sys_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysUser {

    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 用户账号
     */
    private String userName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 微信头像
     */
    private String avatarUrl;
    /**
     * 性别
     */
    private int gender;
    /**
     * 手机
     */
    private String phone;
    /**
     * 状态
     */
    private int status;
    /**
     * 微信用户凭证openId
     */
    private String openId;
    /**
     * 微信用户凭证openId
     */
    private String unionId;

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
