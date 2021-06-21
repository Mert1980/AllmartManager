package be.switchfully.dao;

import be.switchfully.model.Receipt;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDaoImpl implements ReceiptDao<Receipt> {
    private static final List<Receipt> receipts = new ArrayList<>();

    @Override
    public List<Receipt> getAll() {
        return receipts;
    }

    @Override
    public void save(Receipt receipt) {
        receipts.add(receipt);
    }

    @Override
    public void delete(Receipt receipt) {
    }
}
