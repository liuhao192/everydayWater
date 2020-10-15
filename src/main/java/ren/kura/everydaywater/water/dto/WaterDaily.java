package ren.kura.everydaywater.water.dto;

import lombok.Data;
import ren.kura.everydaywater.common.utils.TimeUtils;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: WaterDaily.java
 * <p>描述: 每日喝水的信息
 * <p>HISTORY: 2020/10/12 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/12 11:06
 */
@Data
public class WaterDaily {
    /**
     * 目标数
     */
    private int goalNum;
    /**
     * 已完成数量
     */
    private int completedNum;
    /**
     * 下次提醒时间
     */
    private String remindNextTime;
    /**
     * 请求的天数
     */
    private String requestDate= TimeUtils.getFormatDate();


}
