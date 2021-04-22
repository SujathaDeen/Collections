import collections.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.Scanner;
import java.util.stream.Collectors;


class PreContacts implements Comparator {
    Logger log=Logger.getLogger(Contacts.class.getName());
    Scanner input=new Scanner(System.in);

    void start(){
        log.info("Contacts List");
        while(true){
            log.info("Do it");
            log.info("\n1.ContactList \n2.Create Contact \n3.Search \n4.Call \n5.Delete \n6.Exit");
            log.info("Enter to perform :");
            int choice=input.nextInt();
            switch (choice){
                case 1:
                    contactList();
                    break;
                case 2:
                    savingOneContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    callContact();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    System.exit(1);
                    break;
                default:
                    log.info("Enter Between 1 to 6 ");
                    break;
            }
        }
    }

    void savingOneContact(){
        ContactDetails newContact=new ContactDetails();
        log.info("Enter the Name : ");
        input.nextLine();
        String name=input.nextLine();
        newContact.setName(name);
        log.info("Enter the Mobile Number :");
        Long phoneNo=input.nextLong();
        newContact.setPhoneNo(phoneNo);
        log.info("Enter the Email : ");
        input.nextLine();
        String email=input.nextLine();
        newContact.setEmail(email);
        log.info("Enter the Notes : ");
        String notes=input.nextLine();
        newContact.setNotes(notes);
        savingContacts(newContact);
    }

    List<ContactDetails> contactList=new LinkedList();
    void savingContacts(ContactDetails newContact){
        contactList.add(newContact);
    }
    Iterator listIterator = contactList.iterator();

    void contactList(){
        if(contactList.isEmpty()){
            log.info("No contacts available");
        }
        else{
            PreContacts comparator=new PreContacts();
            Collections.sort(contactList, comparator);
            for(ContactDetails contacts:contactList){
                log.info("\nName : "+contacts.getName()+"\nPhone No : "+contacts.getPhoneNo()+"\nEmail : "+contacts.getEmail()+"\nNotes : "+contacts.getNotes());
            }
        }
    }

    void searchContact(){
        AtomicBoolean flag= new AtomicBoolean(true);
        log.info("Enter for Search : ");
        String searchElement = (String)input.next();

        contactList.stream()
                .filter(contacts -> contacts.getName().toLowerCase().contains(searchElement.toLowerCase()))
                .forEach(contacts -> {
                    log.info("\nName : " + contacts.getName() + "\nPhone No : " + contacts.getPhoneNo() + "\nEmail : " + contacts.getEmail() + "\nNotes : " + contacts.getNotes());
            flag.set(false);
        });
        contactList.stream()
                .filter(contacts->String.valueOf(contacts.getPhoneNo()).contains(searchElement))
                .forEach(contacts->{
                    log.info("\nName : "+contacts.getName()+"\nPhone No : "+contacts.getPhoneNo()+"\nEmail : "+contacts.getEmail()+"\nNotes : "+contacts.getNotes());
            flag.set(false);
        });
        noSuchContacts(flag);
    }

    void callContact(){
        AtomicBoolean flag= new AtomicBoolean(true);
        log.info("Enter for Search : ");
        String searchElement = (String)input.next();

        contactList.stream()
                .filter(contacts -> String.valueOf(contacts.getPhoneNo()).equals(searchElement))
                .forEach(contacts -> {
                    log.info("\n======================\n        Calling       \nName : "+contacts.getName()+"\nPhone No : "+contacts.getPhoneNo()+"\n======================");
            flag.set(false);
        });
        noSuchContacts(flag);
    }

    void deleteContact(){
        AtomicBoolean flag= new AtomicBoolean(true);
        log.info("Enter for Search : ");
        String searchElement = (String)input.next();

        for (ContactDetails contacts : contactList) {
            if (contacts.getName().equals(searchElement)||(String.valueOf(contacts.getPhoneNo())).equals(searchElement)){
                log.info("contact deleted "+contacts.getName());
                contactList.remove(contacts);
                flag.set(false);
                break;
            }
        }
        noSuchContacts(flag);
    }

    void noSuchContacts(AtomicBoolean flag){
        if(flag.get()){
            log.info("No such Contacts");
        }
    }

    @Override
    public int compare(Object contact1,Object contact2) {
        ContactDetails firstContact=(ContactDetails) contact1;
        ContactDetails secondContact=(ContactDetails) contact2;

        int result =firstContact.getName().compareTo(secondContact.getName());
        if(result>0)
            return +1;
        else if(result < 0)
            return -1;
        else
            if(firstContact.getPhoneNo() > secondContact.getPhoneNo())
                return 1;
            else if(firstContact.getPhoneNo() < secondContact.getPhoneNo())
                return -1;
            else
                return 0;
    }


}
