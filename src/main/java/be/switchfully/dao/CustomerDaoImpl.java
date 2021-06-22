package be.switchfully.dao;

import be.switchfully.services.AllmartServiceFactory;
import be.switchfully.model.Customer;
import be.switchfully.model.Receipt;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerDaoImpl implements CustomerDao<Customer> {
    private static ReceiptDao receiptDao = AllmartServiceFactory.getReceiptDao();

    private ArrayList<Customer> getCustomers(){
        ArrayList<Receipt> customers = (ArrayList) receiptDao.getAll();
        return customers.stream()
                .map(receipt -> receipt.getCustomer())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Optional<Customer> findByName(String firstName, String lastName) {
        return getCustomers().stream()
                .filter(customer -> customer.getFirstName().equalsIgnoreCase(firstName)
                        && customer.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
    }

    @Override
    public Optional<Customer> findById(String id) {
        return getCustomers().stream()
                .filter(customer -> customer.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public void save(Customer customer) {
        getCustomers().add(customer);
    }

    @Override
    public void delete(Customer customer) {
        getCustomers().remove(customer);
    }

}
