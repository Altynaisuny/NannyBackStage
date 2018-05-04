package main.java.service.Impl;

import main.java.dao.ICustomerDao;
import main.java.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerDao customerDao;

    @Override
    public int insertCustomer(Map map) {
        return customerDao.insertCustomer(map);
    }
}
