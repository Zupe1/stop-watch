import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This stopwatch class implements a simple stopwatch with start, stop, and clear buttons.
 * It uses swing to accomplish the user interface as well as a Thread for time management.
 */

public class Stopwatch implements ActionListener, Runnable {

    public static void main(String[] args) {
        new Stopwatch();
    }

    // Thread for running stopwatch
    Thread thread;

    // Running flag
    boolean running = false;

    // Time elapsed (ms)
    float time = 0;

    // Global user interface components
    private JLabel label;
    private JButton startButton;

    // Constructor
    public Stopwatch() {
        initializeInterface();

        thread = new Thread(this);
        thread.start();
    }

    // Action performed method for handling button events
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        if (button == startButton) {
            running = !running;

            if (running) {
                startButton.setText("Stop");
                return;
            }

            startButton.setText("Start");
            return;
        }

        reset();
    }

    // Method for formatting elapsed time as string (mm:ss:SSS)
    private String getFormattedTime() {
        int totalSeconds = (int) (time / 1000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        int milliseconds = (int) (time % 1000) / 10;

        if (minutes == 0) {
            if (seconds / 10 == 0) {
                return String.format("%01d:%02d", seconds, milliseconds);
            }
            return String.format("%02d:%02d", seconds, milliseconds);
        }

        if (minutes / 10 == 0) {
            return String.format("%01d:%02d:%02d", minutes, seconds, milliseconds);
        }

        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
    }

    // Method for resetting stopwatch
    private void reset() {
        time = 0;
        label.setText("0:00");
        running = false;
    }

    // Run method for Thread
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(5);

            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            }

            if (running){
                time += 5.f;
                label.setText(getFormattedTime());
            }
        }
    }

    // Method to initialize user interface
    private void initializeInterface() {
        // Font
        Font font = new Font("Dialogue", Font.PLAIN, 40);

        // JFrame
        JFrame frame = new JFrame("Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400,200);

        // Main JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));

        // Button JPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));

        // JButtons
        startButton = new JButton("Start");
        startButton.setFont(font);
        startButton.addActionListener(this);
        buttonPanel.add(startButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(font);
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        panel.add(buttonPanel);

        // Label JPanel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());

        // JLabel
        label = new JLabel("0:00");
        label.setFont(font);
        labelPanel.add(label, BorderLayout.EAST);
        panel.add(labelPanel);

        // Add main panel to frame and make visible
        frame.add(panel);
        frame.setVisible(true);
    }
}