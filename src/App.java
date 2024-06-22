import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class App implements KeyListener {

    private FileWriter fileWriter;
    private JLabel label;  // Add a JLabel to display the typed characters

    public App() {
        try {
            fileWriter = new FileWriter("src\\keylog.txt", true); // Open the file for appending data
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }

    static JFrame getFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 500, dimension.height / 2 - 320, 1000, 640);
        return jFrame;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char typedChar = e.getKeyChar();
        saveToFile(String.valueOf(typedChar));
        label.setText(String.valueOf(typedChar));  // Update the JLabel with the typed character
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Additional logic if needed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Additional logic if needed
    }

    public static void main(String[] args) {
        App keyLog = new App();
        JFrame frame = getFrame();

        // Initialize the JLabel and add it to the frame
        keyLog.label = new JLabel("", SwingConstants.CENTER);
        keyLog.label.setFont(new Font("Arial", Font.PLAIN, 100)); // Set the font size as needed
        frame.add(keyLog.label);

        frame.addKeyListener(keyLog);
    }

    public void startListening() {
        // Code to add the KeyListener to the component that needs to track keys
    }

    public void saveToFile(String data) {
        try {
            fileWriter.write(data);
            fileWriter.flush(); // Save data to the file
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void closeFile() {
        try {
            fileWriter.close(); // Close the file after finishing
        } catch (IOException e) {
            System.out.println("Error closing file: " + e.getMessage());
        }
    }
}
