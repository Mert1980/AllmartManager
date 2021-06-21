package be.switchfully.services;

import be.switchfully.dao.CustomerDao;
import be.switchfully.dao.CustomerDaoImpl;
import be.switchfully.dao.ReceiptDao;
import be.switchfully.dao.ReceiptDaoImpl;

public class AllmartServiceFactory {
    private static ReceiptDao receiptDao;
    private static CustomerDao customerDao;
    private static CashierService cashierService;
    private static CustomerService customerService;
    private static ReportingServiceDaily reportingServiceDaily;
    private static ReportingServiceMonthly reportingServiceMonthly;

    public static ReceiptDao getReceiptDao(){
        if(receiptDao == null){
            receiptDao = new ReceiptDaoImpl();
        }
        return receiptDao;
    }

    public static CustomerDao getCustomerDao(){
        if(customerDao == null){
            customerDao = new CustomerDaoImpl();
        }
        return customerDao;
    }

    public static CustomerService getCustomerService(){
        if(customerService == null) {
            customerService = new CustomerServiceImpl();
        }
        return customerService;
    }

    public static CashierService getCashierService(){
        if(cashierService == null) {
            cashierService = new CashierServiceImpl();
        }
        return cashierService;
    }

    public static ReportingService getReportingServiceDaily(){
        if(reportingServiceDaily == null) {
            reportingServiceDaily = new ReportingServiceDaily();
        }
        return reportingServiceDaily;
    }

    public static ReportingService getReportingServiceMonthly(){
        if(reportingServiceMonthly == null) {
            reportingServiceMonthly = new ReportingServiceMonthly();
        }
        return reportingServiceMonthly;
    }
}
