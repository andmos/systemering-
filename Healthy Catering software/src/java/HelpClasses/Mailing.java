
package HelpClasses;

/**
 * ------------------------------------- READ THIS -----------------------------------------------------------
 *
 * This class is a template of what the main should look like when you wish to send a mail.
 * 
 * @author marius
 */
public class Mailing {
    public static void main(String[] args) {
        
        
        String[] recipients = new String[]{"TO_MAIL"};
        String[] bccRecipients = new String[]{"TO_MAIL"};
        String subject = "SUBJET_HERE";
        String messageBody = "ACTUAL_CONTENT";
        

        new Mail().sendMail(recipients, bccRecipients, subject, messageBody);


    }
}

    

