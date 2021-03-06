package ren.kura.everydaywater.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: TimeUtils.java
 * <p>描述: [时间操作的工具类]
 * <p>HISTORY: 2020/10/13 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/13 23:07
 */
public class TimeUtils {

    public static ThreadLocal<SimpleDateFormat> DATE_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static ThreadLocal<SimpleDateFormat> TIME_SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };

    /**
     * 获取当前的时间
     *
     * @return
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }


    /**
     * TimeUtils:: getStringNowDate
     * <p>默认方式表示的系统当前日期，具体格式：年-月-日
     * <p>HISTORY: 2020/10/15 liuha : Created.
     *
     * @return 默认日期按“年-月-日“格式显示
     */
    public static String getFormatDate() {
        return DATE_SDF.get().format(getCalendar().getTime());
    }

    /**
     * TimeUtils:: getFormatTime
     * <p>默认方式表示的系统当前日期，具体格式：24时 小时:分钟
     * <p>HISTORY: 2020/10/15 liuha : Created.
     *
     * @return 默认日期按“小时-分钟“格式显示
     */
    public static String getFormatTime() {
        return TIME_SDF.get().format(getCalendar().getTime());
    }

    /**
     * TimeUtils:: getFormatTime
     * <p> 将传入的时间转换成“小时-分钟“格式显示
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param date 时间
     * @return 将时间格式返回为 “小时-分钟“格式显示
     */
    public static String getFormatTime(Date date) {
        return TIME_SDF.get().format(date);
    }
}
