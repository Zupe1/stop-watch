import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch {
    public static void main(String[] args) {
        new Stopwatch();

    }
    public Stopwatch() {
        JFrame frame = new JFrame("text");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400,200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));

        JButton button = new JButton("Start/Stop");
        panel.add(button);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,75));

        JLabel label = new JLabel("1:23:342");
        labelPanel.add(label);
        panel.add(labelPanel);

        frame.add(panel);
        frame.setVisible(true);

        boolean isOn = false;

        float time = 0;

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isOn = !isOn;
            }

        });
        while (true){
            if(isOn){
                try {
                    Thread.sleep(10);
                    time += 10;
                    label.setText(String.valueOf(time));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }

    }


}