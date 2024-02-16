package Version2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



import java.net.URL;

public class ContactsList {

    private GridLayout gridLayout;
    private JPanel table;
    private Database database;


    public ContactsList(Database database) {


        this.database = database;
        JFrame frame = new JFrame("Contacts System ");
        frame.setLayout(new BorderLayout());
        frame.setSize(1800, 1080);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        JTextField searchField = new JTextField(15);
        searchField.setBackground(new Color(0, 0,0));
        searchField.setForeground(new Color(255,255,255));
        searchField.setFont(new Font("Consolas",Font.PLAIN,18));
        searchField.setText("Search");

        JButton switchLightModeDarkmode = new JButton("Switch");
        switchLightModeDarkmode.setVisible(true);
        switchLightModeDarkmode.setBounds(1300,20,100,45);
        switchLightModeDarkmode.setBackground(Color.white);






        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0, 0, 0));
        searchButton.setFocusable(false);
        searchButton.setBounds(204,77,100,45);
        searchButton.setVisible(true);


        searchButton.setForeground(Color.white);





        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                try {
                    ArrayList<Contact> searchResults = database.searchContacts(searchText);
                    refresh(searchResults);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1.toString());
                }
            }
        });




        JPanel top = new JPanel(new BorderLayout());
        top.setBorder(BorderFactory.createEmptyBorder(77, 50, 50, 50));
        top.setBackground(null);

        JLabel title = new JLabel("Contacts Project via blackHats");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(new Color(224,224,224));
        top.add(title, BorderLayout.CENTER);
        top.add(searchField, BorderLayout.WEST);
        frame.add(searchButton);
        frame.add(switchLightModeDarkmode);


        JButton newContact = new JButton("New Contact");
        newContact.setFont(new Font("Tahoma", Font.BOLD, 18));
        newContact.setBackground(new Color(0, 153, 153));
        newContact.setForeground(Color.white);
        newContact.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        newContact.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new OpenContact(new Contact(), "new", database, ContactsList.this);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1.toString());
                }
            }
        });

        top.add(newContact, BorderLayout.EAST);
        frame.add(top, BorderLayout.NORTH);

        gridLayout = new GridLayout(8, 1, 0, 0);
        table = new JPanel(gridLayout);
        table.setBackground(new Color(64,64,64));
        try {
            refresh(database.getContacts());
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.toString());
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        sp.setBackground(null);

        frame.add(sp, BorderLayout.CENTER);

        frame.setVisible(true);


        String imagePath = "Contacts.png";
        URL imageURL = ContactsList.class.getResource(imagePath);
        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            frame.setIconImage(icon.getImage());
            frame.setVisible(true);
        } else {
            System.err.println("Image not found: " + imagePath);
        }

        switchLightModeDarkmode.addActionListener(new ActionListener() {
            Boolean isDarkMode=false;
            @Override
            public void actionPerformed(ActionEvent e){
                if (isDarkMode){
                    frame.getContentPane().setBackground(Color.BLACK);
                    title.setForeground(Color.white);
                    searchButton.setBackground(Color.BLACK);
                    searchField.setBackground(Color.BLACK);
                    table.setBackground(Color.white);
                    table.setBackground(new Color(64,64,64));
                    switchLightModeDarkmode.setBackground(Color.white);
                    switchLightModeDarkmode.setForeground(Color.black);
                    searchField.setForeground(Color.WHITE);






                    isDarkMode=false;

                }else{
                    frame.getContentPane().setBackground(Color.white);
                    title.setForeground(Color.black);
                    searchButton.setBackground(Color.BLUE);
                    searchField.setBackground(Color.white);
                    table.setBackground(Color.white);
                    switchLightModeDarkmode.setBackground(Color.BLACK);
                    switchLightModeDarkmode.setForeground(Color.white);
                    searchField.setForeground(Color.black);





                    isDarkMode=true;
                }
            }
        });



    }
    public void refresh(ArrayList<Contact> contacts) {
        table.removeAll();
        table.repaint();
        table.revalidate();

        int rows = contacts.size();
        if (rows<8) rows=8;
        gridLayout.setRows(rows);

        for (int i=0;i<contacts.size();i++) {
            Contact c = contacts.get(i);
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
            panel.setBackground(null);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            if (i%2==0) panel.setBackground(new Color(96,96,96));
            panel.setPreferredSize(new Dimension(100, 55));
            panel.add(GUI.label(c.getFirstName()+" "+c.getLastName()));
            panel.add(GUI.label(c.getPhoneNumber()));
            panel.add(GUI.label(c.getEmail()));
            JButton view = GUI.button("view", new Color(0, 153, 153));
            view.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new OpenContact(c, "view", database, ContactsList.this);
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }
            });
            panel.add(view);
            JButton edit = GUI.button("edit", new Color(63, 134, 196));
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new OpenContact(c, "edit", database, ContactsList.this);
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }
            });
            panel.add(edit);
            JButton delete = GUI.button("delete", new Color(153, 0, 0));
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        database.deleteContact(c);
                        refresh(database.getContacts());
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }
            });

            panel.add(delete);

            JButton call = GUI.button("Call", new Color(0, 0, 255));
            call.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // When the Call button is pressed, start the call
                    try {
                        CallApp.setupCallApp();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }
            });
            panel.add(call);




            JButton videoCall = GUI.button("Video Call", new Color(0, 164, 0));

// Add an ActionListener to the Video Call button
            videoCall.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // When the Video Call button is pressed, start the video call
                    // You'll need to implement the startVideoCall method
                    try {
                        startVideoCall(c);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }

                private void startVideoCall(Contact c) {
                    org.xml.LoggerFactory.getILoggerFactory();

                    // Create an instance of the CameraViewer class
                    CameraViewer cameraViewer = new CameraViewer();

                    // Set up the JFrame
                    cameraViewer.setTitle("Camera Viewer");
                    cameraViewer.setSize(600, 600); // Set an initial size

                    String imagePath = "video.png";
                    URL imageURL = ContactsList.class.getResource(imagePath);
                    if (imageURL != null) {
                        ImageIcon icon = new ImageIcon(imageURL);
                        cameraViewer.setIconImage(icon.getImage());
                        cameraViewer.setVisible(true);
                    } else {
                        System.err.println("Image not found: " + imagePath);
                    }

                    // Display the JFrame
                    cameraViewer.setVisible(true);

                }
            });




            panel.add(videoCall);
            table.add(panel);


            // disabled video call btn
            JButton Block = GUI.button("Block", new Color(0, 0, 0));
            final boolean[] isBlocked = {false};
            Block.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isBlocked[0] = !isBlocked[0];
                    if (isBlocked[0]) {
                        Block.setText("UnBlock");
                        Block.setBackground(new Color(147, 0, 116));
                        videoCall.setEnabled(false);
                        call.setEnabled(false);
                    } else {
                        Block.setText("Block");
                        Block.setBackground(new Color(0, 0, 0));
                        videoCall.setEnabled(true);
                        call.setEnabled(true);

                    }
                }});
            panel.add(Block);










        }
    }

}