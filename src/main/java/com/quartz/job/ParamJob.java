package com.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution //持久化数据,确保任意一个线程都成取到同步的数据
@DisallowConcurrentExecution	//保证工作在同一时间只在一个线程工作
public class ParamJob implements Job{

	private final static Logger logger = LoggerFactory.getLogger(ParamJob.class);
	
	public final static String FAVORITE_COLOR = "favorite_color";
	public final static String EXECUTION_COUNT = "execution_count";

	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try {
			Thread.sleep(10L * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JobDataMap data = context.getJobDetail().getJobDataMap();
		String favoriteColor = data.getString(FAVORITE_COLOR);
		int count = data.getInt(EXECUTION_COUNT);
		
		logger.info("current thread: {}, favoriteColor: {}, count: {}", Thread.currentThread().getId(), favoriteColor, count);
		
		data.put(EXECUTION_COUNT, ++count);
	}

}
