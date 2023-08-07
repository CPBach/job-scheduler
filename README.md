# Job-scheduler

Job-scheduler is a simple library for easily scheduling background jobs without 
having to deal with progress tracking, Futures, Thread pools, etc. yourself. Currently this library does not support distributed job scheduling. It can be easily integrated into your Spring Boot project.

To read more about how it was created feel free to read the [blog post](https://stefanbabel.de/job-scheduler-spring-boot). 


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
    @Autowired
    private lateinit var jobScheduler: JobScheduler

    @PostMapping("/job/{jobName}")
    fun startJob(@PathVariable("jobName") jobName: String): String {
        return jobScheduler.scheduleJob(jobName, "Some job description") {progressTracker ->
            // Do work here and inform progressTracker on current status.
        }
    }
```

## Links

-   [Article on this library](https://www.stefanbabel.de/a-simple-job-scheduler-library-for-spring-boot/)
