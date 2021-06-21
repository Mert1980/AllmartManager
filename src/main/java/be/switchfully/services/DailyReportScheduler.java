package be.switchfully.services;

import java.util.TimerTask;

public class DailyReportScheduler extends TimerTask {
    private static ReportingService reportingServiceDaily = AllmartServiceFactory.getReportingServiceDaily();

    @Override
    public void run() {
        reportingServiceDaily.generateReport();
    }
}
