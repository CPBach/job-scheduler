package com.bubble.arts.jobscheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bubble.arts.jobscheduler.api.JobScheduler;
import com.bubble.arts.jobscheduler.impl.DefaultJobScheduler;

/**
 * Make our beans visible to spring boot.
 */
@Configuration
public class JobSchedulerAutoConfiguration {
	
	@Bean 
	public JobScheduler jobScheduler() {
		return new DefaultJobScheduler();
	}
}