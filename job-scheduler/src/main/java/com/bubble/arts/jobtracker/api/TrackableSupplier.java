package com.bubble.arts.jobtracker.api;

/**
 * Simple interface where we can track the progress of a job.
 */
public interface TrackableSupplier<T> {
	
	/**
	 * 
	 * @param tracker
	 * @return
	 */
	public T run(IProgressTracker tracker);
}
