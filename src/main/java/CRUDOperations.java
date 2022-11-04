import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.*;
import utils.GenericUtils;

public class CRUDOperations {

    private static final Logger logger = LogManager.getLogger(CRUDOperations.class);


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        boolean flag = true;
        UserServiceImpl userService = new UserServiceImpl();
        DbServiceImpl dbService = new DbServiceImpl();
        GenericUtils genericUtils = new GenericUtils();

        Scanner scanner = new Scanner(System.in);
        while (flag){
            logger.info("Which DB Operation do you want to perform? For Insert press 1, For Search Press 2, For Update 3, For Delete Press 4, For Exit Press 0:::");
            int dbOptions = scanner.nextInt();

            if(dbOptions == 1){

                logger.info("Do you want to add single customer (Press 1) or multiple customer (Press 2) ");
                int insertOption = scanner.nextInt();

                if(insertOption == 1){
                    Customer customer = userService.getCustomerDataFromUser();
                    dbService.insertCustomer(customer);
                }
                else if(insertOption == 2){
                    List<Customer> customers = userService.getCustomersDataFromUser();
                    dbService.insertCustomers(customers);
                }
                else
                {
                    logger.info("Please enter valid options for insert customer...");
                }
            }
            else if(dbOptions == 2){

                logger.info("Do you want to search all customer (Press 1), search customer by ID (Press 2) or search customer by Name (Press 3) ");
                int searchOption = scanner.nextInt();

                if (searchOption == 1){
                    List<Customer> customers = dbService.getAllCustomerDataFromDB();
                    genericUtils.printAllCustomersFromDB(customers);
                }
                else if(searchOption == 2){
                    Customer customer = userService.getCustomerByIdFromUser();
                    dbService.checkCustomerIfExistsByID(customer);
                }
                else if(searchOption == 3){
                    Customer customer = userService.getCustomerByNameFromUser();
                    dbService.checkCustomerIfExistsByName(customer);
                }
                else{
                    logger.warn("Enter valid search options...");
                }

            }
            else if(dbOptions == 3){

                Customer customer = userService.getCustomerUpdatedDataFromUser();
                dbService.updateCustomer(customer);
            }
            else if(dbOptions == 4){

                Customer customer = userService.deleteCustomerByFirstName();
                dbService.removeCustomer(customer);
            }
            else if(dbOptions == 0){
                logger.info("Thank You for the DB Operations!!!!");
                logger.info("Program Closed...");
                flag = false;
            }
            else{
                logger.warn("Enter Valid  Database Option...");
            }
        }


    }
}
