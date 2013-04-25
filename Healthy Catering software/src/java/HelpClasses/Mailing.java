
package HelpClasses;

/**
 * ------------------------------------- READ THIS -----------------------------------------------------------
 *
 * This class is a template of what the main should look like when you wish to send a mail.
 * 
 * @author marius
 */
public class Mailing {
    public void sendMail(String[] recipients, String[] bccRecipients, String subject,String messageBody){
        new Mail().sendMail(recipients, bccRecipients, subject, messageBody);
    }
//    public static void main(String[] args) {
//        
//        
//        String[] recipients = new String[]{"marius_lian@hotmail.com"};
//        String[] bccRecipients = new String[]{"marius_lian@hotmail.com"};
//        String subject = "thanks";
//        String messageBody = "innhold";
//        
//
//        new Mail().sendMail(recipients, bccRecipients, subject, messageBody);
//
//
//    }
}

    

