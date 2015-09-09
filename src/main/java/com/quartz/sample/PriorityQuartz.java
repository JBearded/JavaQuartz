package com.quartz.sample;

import java.net.URL;
import java.util.Calendar;
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

import com.quartz.job.PriorityJob;

public class PriorityQuartz {

	public static void main(String[] args) {
		
		try {
			
			URL url = ClassLoader.getSystemResource("quartz_priority.properties");
			SchedulerFactory sf = new StdSchedulerFactory(url.getPath());
			Scheduler scheduler = sf.getScheduler();
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.SECOND, 5);
			Date startTime = calendar.getTime();
			
			JobDetail job = JobBuilder.newJob(PriorityJob.class)
					.withIdentity("TriggerPriorityJob")
					.build();
			
			Trigger trigger1 = TriggerBuilder.newTrigger()
					.withIdentity("Priority1Trigger5SecondRepeat")
					.startAt(startTime)
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withRepeatCount(1)
							.withIntervalInSeconds(5))
					.withPriority(1)
					.forJob(job)
					.build();
			
			//默认优先级为5
			Trigger trigger2 = TriggerBuilder.newTrigger()
					.withIdentity("Priority5Trigger10SecondRepeat")
					.startAt(startTime)
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withRepeatCount(1)
							.withIntervalInSeconds(10))
					.forJob(job)
					.build();
			
			Trigger trigger3 = TriggerBuilder.newTrigger()
					.withIdentity("Priority10Trigger15SecondRepeat")
					.startAt(startTime)
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withRepeatCount(1)
							.withIntervalInSeconds(15))
					.withPriority(10)
					.forJob(job)
					.build();
			
			scheduler.scheduleJob(job, trigger1);
			scheduler.scheduleJob(trigger2);
			scheduler.scheduleJob(trigger3);
			
			scheduler.start();
			
			Thread.sleep(30L * 1000L);
			
			scheduler.shutdown(true);
			Thread.sleep(5L * 1000L);
			
		} catch (SchedulerException se) {
			se.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
