package utils;

import models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GenericUtils {

    private final static Logger logger = LogManager.getLogger(GenericUtils.class);

    public void printAllCustomersFromDB(List<Customer> customers){

        customers.forEach(customer -> logger.info("ID {} First Name {} Last Name {} ",customer.getCustomer_id(), customer.getFirst_name(), customer.getLast_name() ));

//        for(int i=0;i<customers.size();i++){
//
//            int id = customers.get(i).getCustomer_id();
//            String firstName = customers.get(i).getFirst_name();
//            String lastname = customers.get(i).getLast_name();
//            System.out.println("ID "+id+" "+"First Name "+firstName+" "+"Last Name "+lastname);
//        }
//
//        for(Customer customer : customers){
//            System.out.println("ID "+customer.getCustomer_id()+" "+"First Name "+customer.getFirst_name()+" "+"Last Name "+customer.getLast_name());
//        }
    }

    public Boolean validateUserFirstName(String fname){

        if(fname.contains("$") || fname.contains("#") || fname.contains("%")) {
            logger.warn("first name should not contains special characters... ");
            return Boolean.FALSE;
        }
        else if(fname.length() == 1 || fname.length() >=10 ){
                logger.warn("first name length should be > 1 and <=10!!! ");
                return Boolean.FALSE;
            }
        else{
            return Boolean.TRUE;
        }
    }

    public Boolean validateUserLastName(String lname){

        if(lname.contains("$") || lname.contains("#") || lname.contains("%")) {
            logger.warn("Last name does not contains special characters... ");
            return Boolean.FALSE;
        }
        else if(lname.length() == 1 || lname.length() >=10 ){
            logger.warn("Last name length should be > 1 and <=10!!! ");
            return Boolean.FALSE;
        }
        else{
            return Boolean.TRUE;
        }
    }


}
