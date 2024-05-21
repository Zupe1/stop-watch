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
    JButton button;
    @Override
    public void actionPerformed(ActionEvent e) {
        running = !running;
        if(running) {
            button.setText("Stop");

        }
        else {
            button.setText("Start");
            time = 0;
        }
    }

    public Stopwatch() {
        initializeInterface();

        thread = new Thread(this);
        thread.start();
    }

    private void initializeInterface() {
        JFrame frame = new JFrame("Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400,200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));

        Font font = new Font("Dialogue", Font.PLAIN, 40);

        button = new JButton("Start");
        panel.add(button);

        button.setFont(font);


        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,75));


        label = new JLabel("0");
        labelPanel.add(label);
        panel.add(labelPanel);
        label.setFont(font);

        frame.add(panel);
        frame.setVisible(true);

        button.addActionListener(this);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(10);
                if(running){
                    time += 10;
                    label.setText(String.valueOf(time));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}