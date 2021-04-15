import java.util.InputMismatchException;
import java.util.logging.Logger;


public class Contacts {
    public static void main(String[] args) {
        Logger log=Logger.getLogger(Contacts.class.getName());
        PreContacts preContact=new PreContacts();

        try{
            preContact.start();
        }
        catch(InputMismatchException e){
            log.warning("Please Enter Proper Value");
        }
        catch(Exception e){
            log.warning("Something went wrong"+ e.getMessage());
        }
        finally {
            log.info("Finally Program terminated");
        }


    }
}
