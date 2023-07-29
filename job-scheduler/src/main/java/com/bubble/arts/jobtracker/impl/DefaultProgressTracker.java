package com.bubble.arts.jobtracker.impl;

import com.bubble.arts.jobtracker.api.IProgressTracker;

public class DefaultProgressTracker implements IProgressTracker{
	private int currentProgress = 0;
	private int max = 100;
	
	@Override
	public void setMax(int maxProgress) {
		this.max = maxProgress;
	}

	@Override
	public void setCurrentProgress(int currentProgress) {
		this.currentProgress = Math.min(max, currentProgress);
	}

	@Override
	public void incrementByOne() {
		this.currentProgress++;
	}

	@Override
	public int getProgress() {
		return (int)((double)this.currentProgress * 100 / max);
	}
}
