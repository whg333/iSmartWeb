package com.why.ismartweb.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.why.ismart.framework.Loader;
import com.why.ismart.framework.ioc.BeanContext;
import com.why.ismart.framework.util.JdbcUtil;
import com.why.ismartweb.domain.Customer;
import com.why.ismartweb.service.CustomerService;

public class CustomerServiceTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceTest.class);

    private static final AtomicInteger count = new AtomicInteger();
    
    private CustomerService customerService;
    
    @Before
    public void init(){
        JdbcUtil.executeSqlFile("sql/insert_test_customer.sql");
        LOGGER.info("init count="+count.incrementAndGet());
        Loader.init();
        customerService = BeanContext.getBean(CustomerService.class);
    }
    
    @Test
    public void testFindCustomers(){
        List<Customer> customers = customerService.findCustomerList();
        Assert.assertTrue(customers.size() == 2);
        LOGGER.info("testFindCustomers");
    }
    
    @Test
    public void testFindCustomer() {
        long id = 1;
        Customer customer = customerService.findCustomer(id);
        Assert.assertNotNull(customer);
        LOGGER.info("testFindCustomer");
    }
    
    @Test
    public void testCreateCustomer(){
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", "why");
        fieldMap.put("contact", "为什么");
        fieldMap.put("telephone", "12345678");
        Assert.assertTrue(customerService.createCustomer(fieldMap));
        Customer customer = customerService.findCustomer(3);
        Assert.assertTrue(customer.getContact().equals("为什么"));
        LOGGER.info("testCreateCustomer "+customer.getContact());
    }
    
    @Test
    public void testUpdateCustomer() {
        long id = 1;
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("contact", "Eric");
        boolean result = customerService.updateCustomer(id, fieldMap);
        Assert.assertTrue(result);
        LOGGER.info("testUpdateCustomer");
    }
    
    @Test
    public void testDeleteCustomer() {
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
        LOGGER.info("testDeleteCustomer");
    }
    
}
