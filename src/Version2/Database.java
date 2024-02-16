package Version2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    private String url = "jdbc:mysql://localhost/test";
    private String user = "root";
    private String pass = "";
    private Statement statement;

    public Database() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, pass);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
    }

    public ArrayList<Contact> getContacts() throws SQLException {
        ArrayList<Contact> contacts = new ArrayList<>();
        String select = "SELECT * FROM `contacts` ORDER BY `First Name` ASC;";
        ResultSet rs = statement.executeQuery(select);
        while (rs.next()) {
            Contact c = new Contact();
            c.setID(rs.getInt("ID"));
            c.setFirstName(rs.getString("First Name"));
            c.setLastName(rs.getString("Last Name"));
            c.setPhoneNumber(rs.getString("Phone Number"));
            c.setEmail(rs.getString("Email"));
            c.setCity(rs.getString("City"));
            c.setState(rs.getString("State"));
            c.setStreetAdress(rs.getString("StreetAddress"));
            c.setZip(rs.getString("Zip"));
            contacts.add(c);
        }
        return contacts;
    }


    public void insertContact(Contact c) throws SQLException {
        String insert = "INSERT INTO `contacts`(`ID`, `First Name`, `Last Name`, "
                + "`Phone Number`, `Email`, `City`, `State`, `StreetAddress`, `Zip`) VALUES ('"+c.getID()+"','"+c.getFirstName()+
                "','"+c.getLastName()+"','"+c.getPhoneNumber()+"','"+c.getEmail()+"','"+c.getCity()+"','"+c.getState()+"','"+c.getStreetAdress()+"','"+c.getZip()+"')"; // added city, state, street address, zip
        statement.execute(insert);
    }

    public void updateContact(Contact c) throws SQLException {
        String update = "UPDATE `contacts` SET `First Name`='"+c.getFirstName()+
                "',`Last Name`='"+c.getLastName()+"',`Phone Number`='"+c.getPhoneNumber()
                +"',`Email`='"+c.getEmail()+"',`City`='"+c.getCity()+"',`State`='"+c.getState()+"',`StreetAddress`='"+c.getStreetAdress()+"',`Zip`='"+c.getZip()+"' WHERE `ID` = "+c.getID()+" ;"; // added city, state, street address, zip
        statement.execute(update);
    }

    public void deleteContact(Contact c) throws SQLException {
        String delete = "DELETE FROM `contacts` WHERE `ID` = "+c.getID()+" ;";
        statement.execute(delete);
    }

    public int getNextID() throws SQLException {
        int id = 0;
        ArrayList<Contact> contacts = getContacts();
        if (contacts.size()!=0) {
            Contact last = contacts.get(contacts.size()-1);
            id = last.getID()+1;
        }
        return id;
    }

    public ArrayList<Contact> searchContacts(String searchText) throws SQLException {
        ArrayList<Contact> contacts = new ArrayList<>();
        String select = "SELECT * FROM `contacts` WHERE `First Name` LIKE '%" + searchText + "%' OR `Last Name` LIKE '%" + searchText + "%' OR `Email` LIKE '%" + searchText + "%' OR `City` LIKE '%" + searchText + "%' OR `Phone Number` LIKE '%" + searchText + "%' OR `State` LIKE '%" + searchText + "%' OR `StreetAddress` LIKE '%" + searchText + "%' OR `Zip` LIKE '%" + searchText + "%' ORDER BY `First Name` ASC;";
        ResultSet rs = statement.executeQuery(select);
        while (rs.next()) {
            Contact c = new Contact();
            c.setID(rs.getInt("ID"));
            c.setFirstName(rs.getString("First Name"));
            c.setLastName(rs.getString("Last Name"));
            c.setPhoneNumber(rs.getString("Phone Number"));
            c.setEmail(rs.getString("Email"));
            c.setCity(rs.getString("City"));
            c.setState(rs.getString("State"));
            c.setStreetAdress(rs.getString("StreetAddress"));
            c.setZip(rs.getString("Zip"));
            contacts.add(c);
        }
        return contacts;
    }
}