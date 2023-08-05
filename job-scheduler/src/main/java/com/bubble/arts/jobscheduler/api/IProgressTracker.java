package com.bubble.arts.jobscheduler.api;

/**
 * Tracker of current progress of a task.
 */
public interface IProgressTracker {
	/**
	 * Set the maximum value of the tracker.
	 * 
	 * @param maxProgress Set the max. possible progress of the tracker, default value is 100.
	 */
	public void setMax(final int maxProgress);
	
	/**
	 * Set the current progress. Max is 100 by default.
	 * @param currentProgress
	 */
	public void setCurrentProgress(final int currentProgress);
	
	/**
	 * Increment the current progress by 1.
	 */
	public void incrementByOne();
	
	/**
	 * @return the current progress
	 */
	public int getProgress();
}
