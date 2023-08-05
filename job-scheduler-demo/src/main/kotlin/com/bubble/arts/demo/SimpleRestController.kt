package com.bubble.arts.demo

import com.bubble.arts.jobscheduler.api.JobInfo
import com.bubble.arts.jobscheduler.api.JobScheduler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class SimpleRestController {
    /**
     * This will be injected by Spring Boot automatically upon
     * initialization.
     */
    @Autowired
    private lateinit var jobScheduler: JobScheduler

    @PostMapping("/job/{jobName}")
    fun startJob(@PathVariable("jobName") jobName: String): String {
        return jobScheduler.scheduleJob(jobName, "") {pt ->
            // Create a random delay for each step-unit.
            val delay = Random.nextInt(1, 400)
            var counter = 0
            println("Creating new job with delay $delay.")
            while (counter < 100) {
                // Simulate some work and update progress tracker ...
                Thread.sleep(delay.toLong())
                pt.setCurrentProgress(counter++)
            }
            pt.setCurrentProgress(100)
        }
    }

    @GetMapping("/jobStatus")
    fun jobStatus(): Collection<JobInfo> {
        return jobScheduler.jobInfos
    }
}