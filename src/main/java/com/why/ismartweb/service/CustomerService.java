package com.why.ismartweb.service;

import java.util.List;
import java.util.Map;

import com.why.ismart.framework.aop.Transaction;
import com.why.ismart.framework.ioc.Service;
import com.why.ismart.framework.util.JdbcUtil;
import com.why.ismartweb.domain.Customer;

@Service
public class CustomerService {
    
    public List<Customer> findCustomerList(){
        return JdbcUtil.queryEntityList("SELECT * FROM customer", Customer.class);
    }
    
    public Customer findCustomer(long id){
        return JdbcUtil.queryEntity("SELECT * FROM customer WHERE id=?", Customer.class, id);
    }
    
    @Transaction
    public boolean createCustomer(Map<String, Object> fieldMap){
        return JdbcUtil.insertEntity(fieldMap, Customer.class);
    }
    
    @Transaction
    public boolean updateCustomer(long id, Map<String, Object> fieldMap){
        return JdbcUtil.updateEntity(id, fieldMap, Customer.class);
    }
    
    @Transaction
    public boolean deleteCustomer(long id){
        return JdbcUtil.deleteEntity(id, Customer.class);
    }
    
}
