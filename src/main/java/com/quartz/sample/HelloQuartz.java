package com.quartz.sample;

import java.util.Calendar;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.quartz.job.HelloJob;

public class HelloQuartz {

	public static void main(String[] args) {
		
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			JobDetail job =  JobBuilder.newJob(HelloJob.class)
					.withIdentity("job1", "group1")
					.build();
			

			Calendar calender = Calendar.getInstance();
			calender.setTime(new Date());
			calender.add(Calendar.MINUTE, 1);
			Date date = calender.getTime();
			
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.startAt(date)
					.build();
			
			scheduler.scheduleJob(job, trigger);
			
			scheduler.start();
			
			Thread.sleep(90L * 1000L);
			
			scheduler.shutdown(true);
			Thread.sleep(5L * 1000L);
		}catch(SchedulerException se){
			se.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
