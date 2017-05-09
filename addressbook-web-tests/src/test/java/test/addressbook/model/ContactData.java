package test.addressbook.model;

import com.google.gson.annotations.Expose;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    @Expose
    private String name;
    @Expose
    private String lastname;
    @Expose
    private String mobilenumber;
    @Expose
    private String email;
    @Expose
    private String address;
    @Expose
    private String home;
    @Expose
    private String work;
    @Expose
    private String email2;
    @Expose
    private String email3;
    @Expose
    private String allPhones;
    private String allEmails;

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public int getId() {

        return id; }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withCityname(String address) {
        this.address = address;
        return this;
    }
    public ContactData withHomePhone(String home) {
        this.home = home;
        return this;
    }
    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }

        public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCityname() {
        return address;
    }

    public String getHomePhone() {
        return home;
    }

    public String getWorkPhone() {
        return work;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

}
