package thisseasx.ObserverTest;

class Person implements Observer {

    private String name;
    private String email;

    Person(String name) {
        this.name = name;
        this.email = name + "@troll.com";
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    @Override
    public void update(Product o) {
        MailService.mail(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return name.equals(person.name) && email.equals(person.email);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
