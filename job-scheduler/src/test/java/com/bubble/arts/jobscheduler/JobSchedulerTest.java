package com.bubble.arts.jobscheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.bubble.arts.jobscheduler.api.JobScheduler;
import com.bubble.arts.jobscheduler.impl.DefaultJobScheduler;

/**
 * Simple test for our job trackers.
 */
public class JobSchedulerTest {
	
	@Test
	public void testJobScheduler() throws InterruptedException {
		final JobScheduler jobScheduler = new DefaultJobScheduler();
		jobScheduler.scheduleJob("Job 1", "Some computation for job 1.", r -> {
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
		jobScheduler.scheduleJob("Job 2", "Another job.", r -> {
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
		assertTrue(jobScheduler.busy());
		while (jobScheduler.busy()) {
			Thread.sleep(50);
			jobScheduler.printJobStatus();
			assertEquals(2, jobScheduler.getJobInfos().size());
		}
	}
}
