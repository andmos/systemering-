
package HelpClasses;

/**
 *
 * @author marius
 */
public class Mailing {
    public static void main(String[] args) {
        String[] recipients = new String[]{"espenrh@stud.hist.no"};
        String[] bccRecipients = new String[]{"espenrh@stud.hist.no"};
        String subject = "Dette er en testmail";
        String messageBody = "her ser du innholdet av mailen";
        String fromaddress = "fra adresse";
        String password = "passord";

        new Mail().sendMail(recipients, bccRecipients, subject, messageBody, fromaddress, password);


    }
}

    

