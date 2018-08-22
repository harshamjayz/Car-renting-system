/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.clickme.rac.business.custom.impl;

import java.util.ArrayList;
import lk.clickme.rac.business.custom.CustomerBO;
import lk.clickme.rac.dao.CrudDAO;
import lk.clickme.rac.dao.DAOFactory;
import lk.clickme.rac.dao.custom.CustomerDAO;
import lk.clickme.rac.dao.custom.impl.CustomerDAOImpl;
import lk.clickme.rac.dto.CustomerDTO;
import lk.clickme.rac.entity.Customer;

/**
 *
 * @author Harsha madushan
 */
public class CustomerBOImpl implements CustomerBO{
    
    
    CustomerDAO customerDAO;

    public CustomerBOImpl() {
        this.customerDAO = (CustomerDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Customer);
    }
    
    
    @Override
    public boolean saveCustomer(CustomerDTO customer) throws Exception {
        Customer Ecustomer = new Customer(customer.getcID() , customer.getnIC() , customer.getName() , customer.getTelNO() , customer.getAddress());
        return customerDAO.save(Ecustomer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        Customer Ecustomer = new Customer(customer.getcID() , customer.getnIC() , customer.getName() , customer.getTelNO() , customer.getAddress());
        return customerDAO.update(Ecustomer);
    }

    @Override
    public boolean deleteCustomer(int id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO findByID(int id) throws Exception {
        Customer ecustomer = customerDAO.findByID(id);
        CustomerDTO customerDTO = new CustomerDTO(ecustomer.getCID(), ecustomer.getNIC(), ecustomer.getName(), ecustomer.getTelNO(),ecustomer.getAddress());
        return customerDTO;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws Exception {
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        ArrayList<Customer> ecustomerArray = customerDAO.getAll();
        for (Customer customer : ecustomerArray) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getCID(), customer.getNIC(), customer.getName(), customer.getTelNO(), customer.getAddress());
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }
    
}
