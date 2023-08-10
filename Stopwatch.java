import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch extends JFrame {
    private JLabel timerLabel;
    private Timer timer;
    private long startTime;
    private boolean isRunning;

    public Stopwatch() {
        setTitle("Timer and Stopwatch");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timerLabel = new JLabel("00:00:000");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        add(timerLabel);

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });

        add(startButton);
        add(stopButton);
        add(resetButton);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    updateTimerLabel(elapsedTime);
                }
            }
        });
    }

    private void updateTimerLabel(long elapsedTime) {
        long minutes = elapsedTime / 60000;
        elapsedTime %= 60000;
        long seconds = elapsedTime / 1000;
        elapsedTime %= 1000;
        long milliseconds = elapsedTime;

        String timeString = String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
        timerLabel.setText(timeString);
    }

    private void startTimer() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - (isRunning ? startTime : 0);
            timer.start();
            isRunning = true;
        }
    }

    private void stopTimer() {
        if (isRunning) {
            timer.stop();
            isRunning = false;
        }
    }

    private void resetTimer() {
        timer.stop();
        isRunning = false;
        updateTimerLabel(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Stopwatch().setVisible(true);
            }
        });
    }
}
