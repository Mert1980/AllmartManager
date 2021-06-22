package be.switchfully.services;

import be.switchfully.dao.CustomerDao;
import be.switchfully.model.Customer;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = AllmartServiceFactory.getCustomerDao();

    @Override
    public Customer findByCustomerName(String firstName, String lastName) {
        if(customerDao.findByName(firstName, lastName).isPresent()){
            return (Customer) customerDao.findByName(firstName, lastName).get();
        }
        return null;
    }

    @Override
    public Customer findCustomerById(String id) {
        if(customerDao.findById(id).isPresent()){
            return (Customer) customerDao.findById(id).get();
        }
        return null;
    }
}
