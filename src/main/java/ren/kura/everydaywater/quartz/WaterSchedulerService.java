package ren.kura.everydaywater.quartz;

/**
 * <p>项目名称: WaterSchedulerService
 * <p>文件名称: null.java
 * <p>描述: 新增定时器的调度的接口，由于静态类或者新建类无法自动注册(Autowired)bean,使用接口的方式交给spring完成自动自动注入
 * <p>HISTORY: 2020/10/25 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/25 20:17
 */
public interface WaterSchedulerService {
    /**
     * WaterSchedulerService:: schedulerAdd
     * <p>新建一个定时任务
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param jobName        执行的工作名称
     * @param jobClass       实现job接口的类.
     * @param cronExpression cron表达式
     * @param parameter      传递的参数
     */
    void schedulerAdd(String jobName, Class jobClass, String cronExpression, String parameter);

    /**
     * WaterSchedulerService:: schedulerDelete
     * <p>删除一个定时任务
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param jobName 执行的工作名称
     */
    void schedulerDelete(String jobName);

    /**
     * WaterSchedulerService:: schedulerPause
     * <p>暂停定时任务
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param jobName 执行的工作名称
     */
    void schedulerPause(String jobName);

    /**
     * WaterSchedulerService:: schedulerResume
     * <p>重启一个定时任务
     * <p>HISTORY: 2020/11/3 liuha : Created.
     *
     * @param jobName 执行的工作名称
     */
    void schedulerResume(String jobName);

}
