package deb.samples.quartzscheduler.schedules;

import deb.samples.quartzscheduler.jobs.PrintSquareRootJob;
import deb.samples.quartzscheduler.util.SchedulerHolder;
import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobSchedules {

    @Value("${print.square.root.job.cron}")
    private String printSquareRootJobCron;

    @PostConstruct
    public void scheduleJobs() throws SchedulerException {
        schedulePrintSquareRootJob();
    }

    @Scheduled(cron = "${date.time.job.cron}")
    public void printDateTimeJob() {
        System.out.println("Date Time: " + new Date());
    }

    private void schedulePrintSquareRootJob() throws SchedulerException {
        JobKey jobKey = new JobKey("printSqRoot", "group1");
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("number", "6644.336");
        JobDetail jobDetail = JobBuilder.newJob(PrintSquareRootJob.class).withIdentity(jobKey).usingJobData(jobDataMap).build();
        TriggerKey triggerKey = new TriggerKey("printSqRootTrigger");
        Trigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(printSquareRootJobCron)).build();
        SchedulerHolder.scheduleJob(jobDetail, cronTrigger);
    }






}
