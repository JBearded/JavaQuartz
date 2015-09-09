package com.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriorityJob implements Job{

	private static final Logger logger = LoggerFactory.getLogger(PriorityJob.class);
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		logger.info("trigger: {}", context.getTrigger().getKey());
	}

}
