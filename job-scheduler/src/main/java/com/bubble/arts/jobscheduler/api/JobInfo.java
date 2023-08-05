package com.bubble.arts.jobscheduler.api;

/**
 * Holds info about a job.
 */
public class JobInfo {
	private String jobId;
	private String jobName;
	private String jobDescription;
	private int progress = -1;
	
	public JobInfo(final String jobId, final String jobName, final String jobDescription, final int progress) {
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobDescription = jobDescription;
		this.progress = progress;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	@Override
	public String toString() {
		return "JobInfo: " + jobName + ": " + jobDescription + ": "  + getProgress() + "% done.";
	}
}
