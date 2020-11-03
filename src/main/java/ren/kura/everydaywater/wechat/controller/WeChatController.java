package ren.kura.everydaywater.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ren.kura.everydaywater.common.aspect.annotation.AutoLog;
import ren.kura.everydaywater.common.utils.Result;
import ren.kura.everydaywater.user.entity.SysUser;
import ren.kura.everydaywater.user.service.ISysUserService;
import ren.kura.everydaywater.wechat.service.IWeChatService;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: WeChatController.java
 * <p>描述: [小程序与微信交互的controller层]
 * <p>HISTORY: 2020/10/10 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/10 23:15
 */
@RestController
@RequestMapping("/api/wx")
@Slf4j
public class WeChatController {

    @Autowired
    private IWeChatService weChatService;
    @Autowired
    private ISysUserService sysUserService;

    @AutoLog(value = "用户登陆系统")
    @GetMapping(value = "/login/code/{code}")
    public Result loginByCode(@PathVariable("code") String code ){
        log.info("通过code获取当前用户的openid等信息 code:{}开始", code);
        //需要用户的openId
        String openId =weChatService.getOpenIdByCode(code);
        log.info("当前用户的openid openId:{}", openId);
        if(StringUtils.isEmpty(openId)) {
            return  Result.error("登陆失败");
        }
        //需要查询本次登陆的用户是否为第一次
        SysUser user = sysUserService.getUserByOpenId(openId);
        if(StringUtils.isEmpty(user)){
            //注册用户
            log.info("注册用户  openId:{} 结束", openId);
            sysUserService.addUserAndInitConfig(openId);
        }
        log.info("通过code用户登陆系统  openId:{} 结束", openId);
        return  Result.putDataOk("openId",openId);
    }
}
