package com.bubble.arts.jobtracker.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.bubble.arts.jobtracker.api.IProgressTracker;
import com.bubble.arts.jobtracker.api.JobInfo;
import com.bubble.arts.jobtracker.api.JobScheduler;
import com.bubble.arts.jobtracker.api.TrackableSupplier;

/**
 * A primitive implementation of a job scheduler.
 */
public class DefaultJobScheduler implements JobScheduler {
	final Map<String, CompletableFuture<?>> futures = new HashMap<>();
	final Map<String, IProgressTracker> progressTrackers = new HashMap<>();
	final Map<String, JobInfo> jobInfos = new HashMap<>();

	@Override
	public <T> String scheduleJob(String name, String description, TrackableSupplier<T> job, Consumer<T> completion) {
		final String jobId = UUID.randomUUID().toString();
		final IProgressTracker pt = new DefaultProgressTracker();
		progressTrackers.put(jobId, pt);
		jobInfos.put(jobId, new JobInfo(jobId, name, description, 0));
		final CompletableFuture<T> f = CompletableFuture.supplyAsync(() -> {
			return job.run(pt);
		});
		futures.put(jobId, f);
		f.thenAccept(completion);
		return jobId;
	}

	@Override
	public Collection<JobInfo> getJobInfos() {
		updateJobProgress();
		return jobInfos.values();
	}

	@Override
	public <T> String scheduleJob(String name, String description, TrackableSupplier<T> job) {
		return scheduleJob(name, description, job, t -> {});
	}
	

	@Override
	public boolean busy() {
		return futures.values().stream().anyMatch(f -> f.isDone() == false);
	}
	
	@Override
	public void printJobStatus() {
		updateJobProgress();
		jobInfos.values().forEach(v -> {
			System.out.println(v);
		});
	}
	
	private void updateJobProgress() {
		this.jobInfos.forEach((jobId, info) -> {
			info.setProgress(progressTrackers.get(jobId).getProgress());
		});
	}
}
