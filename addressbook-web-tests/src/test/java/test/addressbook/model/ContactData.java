package test.addressbook.model;

public class ContactData {
    private int id;
    private final String name;
    private final String lastname;
    private final String mobilenumber;
    private final String email;
    private final String cityname;

    public ContactData(String name, String lastname, String mobilenumber, String email, String cityname) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.lastname = lastname;
        this.mobilenumber = mobilenumber;
        this.email = email;
        this.cityname = cityname;
    }


    public ContactData(int id, String name, String lastname, String mobilenumber, String email, String cityname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mobilenumber = mobilenumber;
        this.email = email;
        this.cityname = cityname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
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

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
