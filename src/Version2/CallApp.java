package Version2;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.net.URL;

public class CallApp {

    private static JLabel statusLabel;

    public static void setupCallApp() {
        JFrame frame = new JFrame("Call Application");
        JButton callButton = new JButton("Call");
        callButton.setBackground(Color.GREEN);
        callButton.setForeground(Color.white);
        JButton cancelCallButton = new JButton("Cancel Call");
        cancelCallButton.setBackground(Color.red);
        cancelCallButton.setForeground(Color.white);

        JButton muteButton = new JButton("Mute");
        JButton shareScreenButton = new JButton("Share Screen");
        shareScreenButton.setBackground(new Color(	128, 0, 128));
        shareScreenButton.setForeground(Color.white);


        statusLabel = new JLabel("Status: ");
        statusLabel.setForeground(Color.white);


        String imagePath = "call.png";
        URL imageURL = ContactsList.class.getResource(imagePath);
        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            frame.setIconImage(icon.getImage());
            frame.setVisible(true);
        } else {
            System.err.println("Image not found: " + imagePath);
        }

        callButton.addActionListener(e -> {
            initiateCall();
        });
        cancelCallButton.addActionListener(e -> cancelCall());
        muteButton.addActionListener(e -> toggleMute());
        shareScreenButton.addActionListener(e -> shareScreen());

        frame.getContentPane().setLayout(null);
        callButton.setBounds(50, 50, 100, 30);
        cancelCallButton.setBounds(180, 50, 120, 30);
        muteButton.setBounds(50, 100, 100, 30);
        shareScreenButton.setBounds(180, 100, 120, 30);
        statusLabel.setBounds(50, 150, 250, 30);

        frame.getContentPane().add(callButton);
        frame.getContentPane().add(cancelCallButton);
        frame.getContentPane().add(muteButton);
        frame.getContentPane().add(shareScreenButton);
        frame.getContentPane().add(statusLabel);

        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
    }

    private static void initiateCall() {
        // Simulate a call initiation
        setStatus("Calling...");

        // Simulate a call status update after 3 seconds
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                setStatus("Call connected!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void cancelCall() {
        setStatus("Call canceled");
    }

    private static void toggleMute() {
        setStatus("Toggle Mute");
    }

    private static void shareScreen() {
        setStatus("Sharing Screen");
    }

    private static void setStatus(String message) {
        statusLabel.setText("Status: " + message);
    }
}