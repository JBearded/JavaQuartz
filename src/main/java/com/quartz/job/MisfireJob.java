package com.quartz.job;

import java.util.Date;

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
public class MisfireJob implements Job{

	private final static Logger logger = LoggerFactory.getLogger(MisfireJob.class);
	
	public final static String NUM_EXECUTIONS = "NumExecutions";
	public final static String EXECUTION_DELAY = "ExecutionDelay";
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		logger.info("currentthread:{}---{} executing.[{}]",Thread.currentThread().getId(), context.getJobDetail().getKey(), new Date());
		
		JobDataMap map = context.getJobDetail().getJobDataMap();
		int executeCount = 0;
		if(map.containsKey(NUM_EXECUTIONS)){
			executeCount = map.getInt(NUM_EXECUTIONS);
		}
		executeCount++;
		map.put(NUM_EXECUTIONS, executeCount);
		
		long delay = 5000L;
		if(map.containsKey(EXECUTION_DELAY)){
			delay = map.getLong(EXECUTION_DELAY);
		}
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logger.info("currentthread:{} -{} complete({}).",Thread.currentThread().getId(), context.getJobDetail().getKey(), executeCount);
	}

}
