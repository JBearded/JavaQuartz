package com.quartz.sample;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.quartz.job.ParamJob;

public class ParamQuartz {

	public static void main(String[] args) {
		
		
		try {
			
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			
			JobDetail job = JobBuilder.newJob(ParamJob.class)
					.withIdentity("job1", "group1")
					.build();
			
			SimpleTrigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.startAt(new Date())
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(10)
							.withRepeatCount(4))
					.build();
			
			scheduler.scheduleJob(job, trigger);
			
			scheduler.start();
			
			Thread.sleep(50L * 1000L);
			
			scheduler.shutdown(true);
			Thread.sleep(5L * 1000L);
			
		} catch (SchedulerException se) {
			se.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		
	}
}
