package Version2;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CameraViewer extends JFrame {

    private WebcamPanel webcamPanel;
    private JButton startButton;
    private JButton stopButton;

    public CameraViewer() {
        // Create a Start Webcam button
        startButton = new JButton("Start a video call");
        startButton.setBackground(new Color(0, 152, 41 ));

        // Add an ActionListener to the Start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the Start button is pressed, start the webcam and hide the button
                startWebcam();
            }
        });
        stopButton = new JButton("end video  call");
        stopButton.setBackground(Color.red);

        // Add an ActionListener to the Stop button
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the Stop button is pressed, stop the webcam and hide the webcam panel
                stopWebcam();
            }
        });

        // Initially, only the Start button is visible
        startButton.setVisible(true);
        stopButton.setVisible(false);

        // Set up the webcam panel (without starting it immediately)
        Webcam webcam = Webcam.getDefault();
        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setPreferredSize(new Dimension(400, 300)); // Set the preferred size
        webcamPanel.setVisible(false); // Initially hide the webcam panel

        // Add components to the JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(startButton, BorderLayout.NORTH);
        getContentPane().add(stopButton, BorderLayout.SOUTH);
        getContentPane().add(webcamPanel, BorderLayout.CENTER);




        // Make the window non-resizable
        setResizable(false);
    }

    private void startWebcam() {
        startButton.setVisible(false); // Make the Start button disappear
        stopButton.setVisible(true); // Make the Stop button visible
        webcamPanel.setVisible(true); // Make the webcam panel visible
        webcamPanel.start(); // Start webcam capture
    }

    private void stopWebcam() {
        startButton.setVisible(true); // Make the Start button visible
        stopButton.setVisible(false); // Make the Stop button disappear
        webcamPanel.stop(); // Stop webcam capture
        webcamPanel.setVisible(false); // Make the webcam panel invisible
    }
}