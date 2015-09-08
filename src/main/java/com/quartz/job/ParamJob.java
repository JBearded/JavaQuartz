package com.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ParamJob implements Job{

	private final static Logger logger = LoggerFactory.getLogger(ParamJob.class);
	
	public final static String FAVORITE_COLOR = "favorite_color";
	public final static String EXECUTION_COUNT = "execution_count";

	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap data = context.getJobDetail().getJobDataMap();
		String favoriteColor = data.getString(FAVORITE_COLOR);
		int count = data.getInt(EXECUTION_COUNT);
		
		logger.info("favoriteColor: {}, count: {}", favoriteColor, count);
		
		data.put(EXECUTION_COUNT, ++count);
	}

}
