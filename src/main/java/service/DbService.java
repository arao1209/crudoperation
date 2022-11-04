package service;

import models.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DbService {


    Boolean insertCustomer(Customer customer) throws SQLException;

    Boolean insertCustomers(List<Customer> customers) throws SQLException;

    List<Customer> getAllCustomerDataFromDB() throws SQLException;

    Boolean checkCustomerIfExistsByID(Customer customer) throws SQLException;

    Boolean checkCustomerIfExistsByName(Customer customer) throws SQLException;

    Boolean updateCustomer(Customer customer) throws  SQLException;

    Boolean removeCustomer(Customer customer) throws SQLException;

}
