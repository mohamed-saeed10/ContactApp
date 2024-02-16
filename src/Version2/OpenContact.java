package Version2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OpenContact {

    public OpenContact(Contact c, String oper, Database database, ContactsList contacts) throws SQLException {
        JFrame frame = new JFrame("Contacts Management System");
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.black);

        JPanel table = new JPanel(new GridLayout(9, 2, 20, 15));
        table.setBackground(Color.black);
        table.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel id = GUI.label(String.valueOf(c.getID()));




        table.add(GUI.label("First Name:"));
        JTextField firstName = GUI.textField(c.getFirstName());


        table.add(firstName);
        table.add(GUI.label("Last Name:"));
        JTextField lastName = GUI.textField(c.getLastName());

        table.add(lastName);

        table.add(GUI.label("Phone Number:"));
        JTextField phoneNumber = GUI.textField(c.getPhoneNumber());


        table.add(phoneNumber);
        table.add(GUI.label("Email:"));
        JTextField email = GUI.textField(c.getEmail());
        table.add(email);


        table.add(GUI.label("City:"));
        JTextField city = GUI.textField(c.getCity());
        table.add(city);
        table.add(GUI.label("State:"));
        JTextField state = GUI.textField(c.getState());
        table.add(state);
        table.add(GUI.label("Street Address:"));
        JTextField streetAddress = GUI.textField(c.getStreetAdress());
        table.add(streetAddress);
        table.add(GUI.label("Zip:"));
        JTextField zip = GUI.textField(c.getZip());
        table.add(zip);


        JButton cancel = GUI.button("Cancel", new Color(153, 0, 0));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        table.add(cancel);

        JButton save = GUI.button("Save", new Color(63, 134, 196));
        table.add(save);

        frame.add(table, BorderLayout.CENTER);

        if (oper.equals("new")) {
            id.setText(String.valueOf(database.getNextID()));

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (
                            Validation.isValidName(firstName.getText().toString()) &&
                                    Validation.isValidName(lastName.getText().toString()) &&
                                    Validation.isPhoneNumberValid(phoneNumber.getText().toString())&&
                                    Validation.isZipValid(zip.getText().toString())&&
                                    Validation.isEmailValid(email.getText().toString())
                    ) {
                        c.setID(Integer.parseInt(id.getText().toString()));
                        c.setFirstName(firstName.getText().toString());
                        c.setLastName(lastName.getText().toString());
                        c.setPhoneNumber(phoneNumber.getText().toString());
                        c.setEmail(email.getText().toString());
                        c.setCity(city.getText().toString());
                        c.setState(state.getText().toString());
                        c.setStreetAdress(streetAddress.getText().toString());
                        c.setZip(zip.getText().toString());
                        try {
                            database.insertContact(c);
                            frame.dispose();
                            contacts.refresh(database.getContacts());
                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(null, e1.toString());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Data isn't valid."); ;
                    }



                }
            });
        } else if (oper.equals("edit")) {
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.setID(Integer.parseInt(id.getText().toString()));
                    c.setFirstName(firstName.getText().toString());
                    c.setLastName(lastName.getText().toString());
                    c.setPhoneNumber(phoneNumber.getText().toString());
                    c.setEmail(email.getText().toString());
                    c.setCity(city.getText().toString());
                    c.setState(state.getText().toString());
                    c.setStreetAdress(streetAddress.getText().toString());
                    c.setZip(zip.getText().toString());
                    try {
                        database.updateContact(c);
                        frame.dispose();
                        contacts.refresh(database.getContacts());
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }
            });
        } else if (oper.equals("view")) {
            firstName.setEditable(false);
            lastName.setEditable(false);
            phoneNumber.setEditable(false);
            email.setEditable(false);
            city.setEditable(false);
            state.setEditable(false);
            streetAddress.setEditable(false);
            zip.setEditable(false);
            save.setVisible(false);
            cancel.setVisible(false);
        }

        frame.setVisible(true);
    }

}