import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch implements ActionListener, Runnable {

    public static void main(String[] args) {
        new Stopwatch();
    }
    Thread thread;

    boolean running = false;
    float time = 0;

    JLabel label;
    JButton startButton, resetButton;

    public Stopwatch() {
        initializeInterface();

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        if (button == startButton) {
            running = !running;

            if (running) {
                startButton.setText("Stop");
                return;
            }

            startButton.setText("Stop");
            return;
        }

        reset();
    }

    private String getFormattedTime() {
        int totalSeconds = (int) (time / 1000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        int milliseconds = (int) (time % 1000);

        return String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + ":" + String.format("%03d", milliseconds);
    }

    private void reset() {
        time = 0;
        label.setText("00:00:000");
        running = false;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(10);

            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            }

            if (running){
                time += 10.f;
                label.setText(getFormattedTime());
            }
        }
    }

    private void initializeInterface() {
        // Font
        Font font = new Font("Dialogue", Font.PLAIN, 40);

        // Frame
        JFrame frame = new JFrame("Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400,200);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));

        // Buttons
        startButton = new JButton("Start");
        startButton.setFont(font);
        startButton.addActionListener(this);
        buttonPanel.add(startButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(font);
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        panel.add(buttonPanel);

        // Label Panel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());

        // Label
        label = new JLabel("00:00:000");
        label.setFont(font);
        labelPanel.add(label, BorderLayout.EAST);

        panel.add(labelPanel);
        frame.add(panel);
        frame.setVisible(true);
    }
}