package com.quartz.sample;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.quartz.job.MisfireJob;

public class MisfireQuartz {

	public static void main(String[] args) {
		
		try {
			
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			
			JobDetail job1 = JobBuilder.newJob(MisfireJob.class)
					.withIdentity("job1", "group1")
					.usingJobData(MisfireJob.EXECUTION_DELAY, 5000L)
					.build();
			
			Trigger trigger1 = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.startAt(new Date())
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(3)
							.withRepeatCount(2))
					.build();
			
			scheduler.scheduleJob(job1, trigger1);
			
			JobDetail job2 = JobBuilder.newJob(MisfireJob.class)
					.withIdentity("job2", "group1")
					.usingJobData(MisfireJob.EXECUTION_DELAY, 5000L)
					.build();
			
			Trigger trigger2 = TriggerBuilder.newTrigger()
					.withIdentity("trigger2", "group1")
					.startAt(new Date())
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(3)
							.withRepeatCount(2)
							.withMisfireHandlingInstructionIgnoreMisfires())
					.build();
			
			scheduler.scheduleJob(job2, trigger2);
			
			scheduler.start();
			
			Thread.sleep(30L * 1000L);
			
			scheduler.shutdown();
			Thread.sleep(5L * 1000L);
		} catch (SchedulerException se) {
			se.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
