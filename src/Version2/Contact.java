package Version2;

public class Contact {

    private int ID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String zip;
    private String streetAdress;
    private String state;
    private String city;

    public Contact() {}

    public int getID() {return ID;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getEmail() {return email;}
    public String getZip(){return zip;}
    public String getStreetAdress(){return streetAdress;}
    public String getState(){return state;}
    public String getCity(){return city;}

    public void setID(int ID) {this.ID=ID;}
    public void setFirstName(String firstName) {this.firstName=firstName;}
    public void setLastName(String lastName) {this.lastName=lastName;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber=phoneNumber;}
    public void setEmail(String email) {this.email = email;}
    public void setZip(String zip) {this.zip= zip;}
    public void setStreetAdress(String streetAdress) {this.streetAdress=streetAdress;}
    public void setState(String state) {this.state=state;}
    public void setCity(String city) {this.city=city;}

}