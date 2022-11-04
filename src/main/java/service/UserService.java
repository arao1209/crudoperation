package service;

import models.Customer;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    Customer getCustomerDataFromUser();

    List<Customer> getCustomersDataFromUser();

    Customer getCustomerByIdFromUser();

    Customer getCustomerByNameFromUser();

    Customer getCustomerUpdatedDataFromUser() throws SQLException;

    Customer deleteCustomerByFirstName();


}
