package quickkanban



class SchedulerJob {

    static long SECONDS=1000;
    static long MINUTES=60*SECONDS;
    CronService cron=new CronService()
    static triggers = {
        simple repeatInterval:2*MINUTES //2*SECONDS // execute job once in 5 seconds
    }

    def execute() {
        println "Scheduler calling CronService at : "+new Date()
        cron.serviceMethod()
    }
}
