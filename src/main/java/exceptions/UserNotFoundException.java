package exceptions;

import models.Customer;

public class UserNotFoundException extends RuntimeException{

    public  UserNotFoundException(Customer customer){
        super(customer.getFirst_name()+" Not Found in DB...");
    }
}
