package service;

import exceptions.UserNotFoundException;
import models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbServiceImpl implements DbService {

    private static final String URL = "jdbc:mysql://localhost:3306/sql_store";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection conn= null;
    private static final Logger logger = LogManager.getLogger(DbService.class);

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(Exception e){
            logger.error(e);
            e.printStackTrace();
            System.exit(0);
        }

    }

      @Override
    public Boolean insertCustomer(Customer customer) throws SQLException {

        boolean flag = false;
        String insertQuery = "INSERT INTO customers (first_name, last_name) VALUES (?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
        preparedStatement.setString(1,customer.getFirst_name());
        preparedStatement.setString(2,customer.getLast_name());

        int rawInserted = preparedStatement.executeUpdate();
        if(rawInserted>0){
            logger.info("A new customer was inserted successfully!");
            flag = true;
        }

        return flag;
    }

    @Override
    public Boolean insertCustomers(List<Customer> customers) throws SQLException {

        boolean flag = false;
        String insertQuery = "INSERT INTO customers (first_name, last_name) VALUES (?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

        for(int i=0;i<customers.size();i++){
            preparedStatement.setString(1, customers.get(i).getFirst_name());
            preparedStatement.setString(2, customers.get(i).getLast_name());
            preparedStatement.addBatch();
        }
        int[] inserted = preparedStatement.executeBatch();
        if(inserted.length>0){
            logger.info("A new customers were inserted successfully!");
            flag = true;
        }

        return flag;
    }

    @Override
    public List<Customer> getAllCustomerDataFromDB() throws SQLException {

        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            Customer customer = new Customer();
            customer.setCustomer_id(resultSet.getInt(1));
            customer.setFirst_name(resultSet.getString(2));
            customer.setLast_name(resultSet.getString(3));
            customers.add(customer);
        }
        logger.debug("size of all customer: {} ",customers.size());
        return customers;
    }

    @Override
    public Boolean checkCustomerIfExistsByID(Customer customer) throws SQLException {


        String custID_sql = "SELECT * FROM customers WHERE customer_id="+customer.getCustomer_id();

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(custID_sql);

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String fname = resultSet.getString(2);
            String lname = resultSet.getString(3);
            logger.info("ID {} First Name {} Last Name {} ", id, fname, lname);
            return Boolean.TRUE;
        }
        else{
            logger.warn("No ID: {} available ", customer.getCustomer_id());
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean checkCustomerIfExistsByName(Customer customer) throws SQLException, UserNotFoundException {

        String customerName_sql = "SELECT * FROM customers WHERE first_name = '"+customer.getFirst_name()+"'";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(customerName_sql);

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String fname = resultSet.getString(2);
            String lname = resultSet.getString(3);
            logger.info("ID {} First Name {} Last Name {} ", id, fname, lname);
            return Boolean.TRUE;
        }
        else{
            logger.warn("No Name: {} available ", customer.getFirst_name());
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updateCustomer(Customer customer) throws SQLException {


        String sql = "UPDATE customers SET first_name = ? WHERE customer_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, customer.getFirst_name());
        preparedStatement.setInt(2, customer.getCustomer_id());

        int rawsUpdated = preparedStatement.executeUpdate();
        if(rawsUpdated>0){
            logger.info("{} Updated Successfully", customer.getCustomer_id());
            return Boolean.TRUE;
        }
        else{
            logger.warn("{} Not Updated Successfully", customer.getCustomer_id());
            return Boolean.FALSE;
        }

    }

    @Override
    public Boolean removeCustomer(Customer customer) throws SQLException {

        Boolean flag = Boolean.FALSE;
        String sql = "DELETE FROM customers WHERE first_name=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, customer.getFirst_name());

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            logger.info("A user was deleted successfully!");
            flag = Boolean.TRUE;
        }
        else{
            logger.warn("No users deleted...");
        }

        return flag;
    }


}
