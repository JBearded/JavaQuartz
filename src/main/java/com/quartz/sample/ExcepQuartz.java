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

import com.quartz.job.ExcepJob;

public class ExcepQuartz {

	public static void main(String[] args) {
		
		try {
			
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			
			
			JobDetail job = JobBuilder.newJob(ExcepJob.class)
					.withIdentity("job1", "group1")
					.build();
			
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.startAt(new Date())
					.withSchedule(SimpleScheduleBuilder
							.simpleSchedule()
							.withIntervalInSeconds(3)
							.repeatForever())
					.build();
			
			scheduler.scheduleJob(job, trigger);
			
			
			scheduler.start();
			
			Thread.sleep(20L * 1000L);

			scheduler.shutdown(true);
			Thread.sleep(5L * 1000L);
			
		} catch (SchedulerException se) {
			se.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
	}
}
