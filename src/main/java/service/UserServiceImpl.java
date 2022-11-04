package service;
import models.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.GenericUtils;

public class UserServiceImpl implements UserService {

    @Override
    public Customer getCustomerDataFromUser() {

        GenericUtils genericUtils = new GenericUtils();
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();

        Boolean flag = Boolean.TRUE;

        while (Boolean.TRUE.equals(flag)){
            System.out.println("Enter First Name: ");
            String fname = scanner.next();
            Boolean validation  = genericUtils.validateUserFirstName(fname);
            if(Boolean.TRUE.equals(validation)){
                customer.setFirst_name(fname);
                flag = false;
            }
        }
        flag = true;

        while (Boolean.TRUE.equals(flag)){
            System.out.println("Enter Last Name: ");
            String lname = scanner.next();
            Boolean validation  = genericUtils.validateUserLastName(lname);
            if(Boolean.TRUE.equals(validation)){
                customer.setLast_name(lname);
                flag = false;
            }
        }

        return customer;
    }

    @Override
    public List<Customer> getCustomersDataFromUser() {
        Scanner scanner = new Scanner(System.in);
        List<Customer> listOfCustomer = new ArrayList<>();

        System.out.println("How Many Customers do you want to add? ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {

            Customer customer = new Customer();

            System.out.println("Enter First Name: ");
            String fname = scanner.next();
            customer.setFirst_name(fname);

            System.out.println("Enter Last Name: ");
            String lname = scanner.next();
            customer.setLast_name(lname);

            listOfCustomer.add(customer);

        }

        return listOfCustomer;
    }

    @Override
    public Customer getCustomerByIdFromUser() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which customer details do you want to see? Please enter Customer ID number: ");
        int custId = scanner.nextInt();

        Customer customer = new Customer();
        customer.setCustomer_id(custId);

        return customer;
    }

    @Override
    public Customer getCustomerByNameFromUser() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which customer details do you want to see? Please enter Customer First Name: ");
        String custFName = scanner.next();

        Customer customer = new Customer();
        customer.setFirst_name(custFName);

        return customer;
    }

    @Override
    public Customer getCustomerUpdatedDataFromUser() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer ID whom details you want to update?");
        int custId = scanner.nextInt();

        Customer customer = new Customer();
        customer.setCustomer_id(custId);

        DbServiceImpl dbService = new DbServiceImpl();
        Boolean flag = dbService.checkCustomerIfExistsByID(customer);

        if(Boolean.TRUE.equals(flag)){
            System.out.println("Enter Customer First Name:");
            String custFName = scanner.next();
            customer.setFirst_name(custFName);
        }
        else{
            System.out.println("This id is not exists");
        }

        return customer;
    }

    @Override
    public Customer deleteCustomerByFirstName() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name: ");
        String fname = scanner.next();

        Customer customer = new Customer();
        customer.setFirst_name(fname);

        return customer;
    }

}
