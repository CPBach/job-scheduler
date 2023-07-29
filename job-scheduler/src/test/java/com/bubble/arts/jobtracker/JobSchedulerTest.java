package com.bubble.arts.jobtracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.bubble.arts.jobtracker.api.JobScheduler;
import com.bubble.arts.jobtracker.impl.DefaultJobScheduler;

/**
 * Simple test for our job trackers.
 */
public class JobSchedulerTest {
	
	@Test
	public void testJobScheduler() throws InterruptedException {
		final JobScheduler jobTracker = new DefaultJobScheduler();
		jobTracker.scheduleJob("Job 1", "Some computation for job 1.", r -> {
			for (int i = 0; i <= 10; i++) {
				r.setCurrentProgress(i * 10);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					fail();
				}
			}
			return "DONE";
		});
		jobTracker.scheduleJob("Job 2", "Another job.", r -> {
			for (int i = 0; i < 100; i++) {
				r.incrementByOne();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					fail();
				}

			}
			return "DONE";
		}, (t) -> {
			assertEquals("DONE", t);
		});
		assertTrue(jobTracker.busy());
		while (jobTracker.busy()) {
			Thread.sleep(50);
			jobTracker.printJobStatus();
			assertEquals(2, jobTracker.getJobInfos().size());
		}
	}
}
