# Job-scheduler: A simple job-scheduler library for Java / Spring Boot

Job-scheduler is a simple library for easily scheduling background jobs without 
having to deal with progress tracking, Futures, Thread pools, etc. yourself.

Currently this library does not support distributed job scheduling.

It can be easily integrated into your Spring Boot project.

To read more about how it was create feel free to read the article on 

[article]: https://stefanbabel.de/job-scheduler-spring-boot

## Adding job-scheduler to your build and using it

The group ID is `com.bubble.arts`, and its artifact ID is `job-scheduler`.
To add a dependency on the job-scheduler using Maven, use the following:

```xml
<dependency>
  <groupId>com.bubble.arts</groupId>
  <artifactId>job-scheduler</artifactId>
  <version>1.0.5-SNAPSHOT</version>
</dependency>
```

To add a dependency using Gradle:

```gradle
dependencies {
  implementation("com.bubble.arts:job-scheduler:1.0.5-SNAPSHOT")
}
```

To use it in a REST controller e.g. in Spring boot :

```kotlin
    /**
     * This will be injected by Spring Boot automatically upon
     * initialization.
     */
    @Autowired
    private lateinit var jobScheduler: JobScheduler

	@PostMapping("/job/{jobName}")
    fun startJob(@PathVariable("jobName") jobName: String): String {
        return jobScheduler.scheduleJob(jobName, "Some job description") {progressTracker ->
            // Create a random delay for each step-unit.
            val delay = Random.nextInt(1, 400)
            var counter = 0
            while (counter < 100) {
                // Simulate some work and update progress tracker ...
                Thread.sleep(delay.toLong())
                progressTracker.setCurrentProgress(counter++)
            }
            progressTracker.setCurrentProgress(100)
        }
    }
```

## Links

-   [Article on this library](https://stefanbabel.de/job-scheduler-spring-boot)