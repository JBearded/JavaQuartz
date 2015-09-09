package com.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcepJob implements Job{

	private static final Logger logger = LoggerFactory.getLogger(ExcepJob.class);
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		try{
			int zero = 0;
			int calculation = 4815 / zero;
		}catch(Exception e){
			logger.info("---error in excepjob");
			JobExecutionException je = new JobExecutionException(e);
			//je.refireImmediately();
			je.setUnscheduleAllTriggers(true);	//停止所有与此任务相关的触发器
			throw je;
		}
	}

}
