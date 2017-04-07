package test.addressbook.model;

public class ContactData {
    private final String name;
    private final String lastname;
    private final String mobilenumber;
    private final String email;
    private final String cityname;


    public ContactData(String name, String lastname, String mobilenumber, String email, String cityname) {
        this.name = name;
        this.lastname = lastname;
        this.mobilenumber = mobilenumber;
        this.email = email;
        this.cityname = cityname;
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
        return cityname;
    }

}
