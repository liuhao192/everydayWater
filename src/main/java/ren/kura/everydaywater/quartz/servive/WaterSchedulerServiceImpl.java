package ren.kura.everydaywater.quartz.servive;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.kura.everydaywater.quartz.WaterSchedulerService;
import ren.kura.everydaywater.quartz.exceptions.EveryWaterException;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: SchedulerUtil.java
 * <p>描述: [类型描述]
 * <p>HISTORY: 2020/10/23 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/23 9:44
 */
@Slf4j
@Service
public class WaterSchedulerServiceImpl implements WaterSchedulerService {

    @Autowired
    private Scheduler scheduler;


    /**
     *@{inheritDoc}
     */
    @Override
    public  void schedulerAdd(String jobName, Class jobClass, String cronExpression, String parameter) {
        try {
            // 启动调度器
            scheduler.start();

            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName).usingJobData("parameter", parameter).build();

            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new EveryWaterException("创建定时任务失败", e);
        } catch (RuntimeException e) {
            throw new EveryWaterException(e.getMessage(), e);
        } catch (Exception e) {
            throw new EveryWaterException("后台找不到该类：" + jobClass.getName(), e);
        }
    }




    /**
     *@{inheritDoc}
     */
    @Override
    public void schedulerDelete(String jobName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobName));
            scheduler.deleteJob(JobKey.jobKey(jobName));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new EveryWaterException("删除定时任务失败");
        }
    }
/**
*@{inheritDoc}
*/
    @Override
    public void schedulerPause(String jobName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new EveryWaterException("暂停定时任务失败");
        }
    }

    /**
     *@{inheritDoc}
     */
    @Override
    public void schedulerResume(String jobName) {
        try {
            scheduler.resumeTrigger(TriggerKey.triggerKey(jobName));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new EveryWaterException("暂停定时任务失败");
        }
    }
}
