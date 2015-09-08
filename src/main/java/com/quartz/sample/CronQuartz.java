package com.quartz.sample;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.quartz.job.CronJob;

public class CronQuartz {

	public static void main(String[] args) {
		
		
		try {
			
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			
			JobDetail job = JobBuilder.newJob(CronJob.class)
					.withIdentity("job1", "group1")
					.build();
			
			CronTrigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 7 16 * * ?"))
					.build();
			
			scheduler.scheduleJob(job, trigger);
			
			scheduler.start();
			
			Thread.sleep(90L * 1000L);
			
			scheduler.shutdown(true);
			Thread.sleep(5L * 1000L);
			
		} catch (SchedulerException se) {
			se.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		
	}
}
