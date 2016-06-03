package com.why.ismartweb.web.controller;

import java.util.List;

import com.why.ismart.framework.ioc.Inject;
import com.why.ismart.framework.mvc.Action;
import com.why.ismart.framework.mvc.Controller;
import com.why.ismart.framework.mvc.Param;
import com.why.ismart.framework.mvc.View;
import com.why.ismart.framework.util.DateUtil;
import com.why.ismartweb.domain.Customer;
import com.why.ismartweb.service.CustomerService;

@Controller
public class CustomerController {
    
    @Inject
    private CustomerService customerService;
    
    @Action("get:/")
    public View index(Param param){
        String currentTime = DateUtil.format(DateUtil.newDate(), DateUtil.DAY_SECONDS);
        return new View("hello.jsp").addModel("currentTime", currentTime);
    }
    
    @Action("get:/customer")
    public View customer(Param param){
        List<Customer> customerList = customerService.findCustomerList();
        return new View("customer/customer.jsp").addModel("customerList", customerList);
    }
    
    @Action("get:/show")
    public View show(Param param){
        long id = param.getLong("id");
        Customer customer = customerService.findCustomer(id);
        return new View("customer/show.jsp").addModel("customer", customer);
    }

}
