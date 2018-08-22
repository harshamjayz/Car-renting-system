/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.clickme.rac.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.clickme.rac.dao.CrudDAO;
import lk.clickme.rac.dao.CrudUtil;
import lk.clickme.rac.dao.custom.CustomerDAO;
import lk.clickme.rac.entity.Customer;

/**
 *
 * @author Harsha madushan
 */
public class CustomerDAOImpl implements CustomerDAO{

    @Override
    public boolean save(Customer entity) throws Exception {
        return CrudUtil.executeUpdate("Insert into customer(nIC,name,telNO,address) Values(?,?,?,?)", entity.getNIC(),entity.getName(),entity.getTelNO(),entity.getAddress());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.executeUpdate("delete from customer where cID = ?", id);
    }

    @Override
    public boolean update(Customer entity) throws Exception {
        return CrudUtil.executeUpdate("Update customer set nIC = ?, name = ?, telNO = ?, address = ? where cID = ?", entity.getNIC(),entity.getName(),entity.getTelNO(),entity.getAddress(),entity.getCID());
    }

    @Override
    public Customer findByID(Integer id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from customer where cID = ?", id);
        rs.next();
        Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
        return customer;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from customer");
        ArrayList<Customer> Customers = new ArrayList<>();
        while(rs.next()){
            Customer customer = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5));
            Customers.add(customer);
        }
        return Customers;
    }
    
}
