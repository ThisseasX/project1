package thisseasx.ObserverTest;

class MailService {

    static void mail(Person person, Product product) {
        String availability = product.isAvailable() ? "available" : "sold out";
        System.out.printf("Sending an e-mail to '%s' notifying that the product '%s' is now %s!%n",
                person.getEmail(), product.getProductName(), availability);
    }
}
