package be.switchfully.services;

import be.switchfully.dao.ReceiptDao;
import be.switchfully.model.Customer;
import be.switchfully.model.Product;
import be.switchfully.model.Receipt;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.HashMap;

@Getter
@Setter
@RequiredArgsConstructor
public class CashierServiceImpl implements CashierService {
    private ReceiptDao receiptDao = AllmartServiceFactory.getReceiptDao();

    @Override
    public void generateReceipt(Customer customer, HashMap<Product, Integer> productAmountMap) {
        Receipt receipt = new Receipt(customer, productAmountMap);
        receiptDao.save(receipt);
    }
}
