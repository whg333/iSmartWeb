package com.why.ismart.web.controller;

import java.util.List;

import com.mysql.fabric.xmlrpc.base.Param;
import com.why.domain.Customer;
import com.why.ismart.framework.ioc.Inject;
import com.why.ismart.framework.mvc.Action;
import com.why.ismart.framework.mvc.Controller;
import com.why.ismart.framework.mvc.View;
import com.why.service.CustomerService;

@Controller
public class CustomerController {
    
    @Inject
    private CustomerService customerService;
    
    @Action("get:/customer")
    public View index(Param param){
        List<Customer> customerList = customerService.findCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

}
