package com.bubble.arts.jobtracker.api;

import java.util.Collection;
import java.util.function.Consumer;

public interface JobScheduler {
	/**
	 * @return all the job infos
	 */
	public Collection<JobInfo> getJobInfos();
		
	/**
	 * @return true, if there are still running jobs.
	 */
	boolean busy();
	
	/**
	 * Print infos regarding all running jobs.
	 */
	public void printJobStatus();
	
	/**
	 * Schedule a job and get a corresponding job id
	 * 
	 * @param name the name of the job 
	 * @param description a description of the job
	 * @param job the job to execute
	 * @return a job id
	 */
	<T> String scheduleJob(String name, String description, TrackableSupplier<T> job);
	
	/**
	 * Schedule a job and get a corresponding job id.
	 * 
	 * @param name the name of the job 
	 * @param description a description of the job
	 * @param job the job to execute
	 * @param completion what should be run after a job has completed
	 * @return a job id
	 */
	<T> String scheduleJob(String name, String description, TrackableSupplier<T> job, Consumer<T> completion);
}
