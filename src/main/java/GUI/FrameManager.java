package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameManager extends JFrame {
    private JTextField timeLimitField;
    private JTextField maxServiceTimeField;
    private JTextField minServiceTimeField;
    private JTextField numberOfServersField;
    private JTextField numberOfClientsField;
    private JTextField minArrivalTimeField;
    private JTextField maxArrivalTimeField;
    private JButton simulateButton;

    public FrameManager() {
        setTitle("Simulation Configuration");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel timeLimitLabel = new JLabel("Time Limit:");
        timeLimitLabel.setBounds(20, 20, 100, 20);
        add(timeLimitLabel);

        timeLimitField = new JTextField();
        timeLimitField.setBounds(150, 20, 200, 20);
        add(timeLimitField);

        JLabel maxServiceTimeLabel = new JLabel("Max Service Time:");
        maxServiceTimeLabel.setBounds(20, 50, 120, 20);
        add(maxServiceTimeLabel);

        maxServiceTimeField = new JTextField();
        maxServiceTimeField.setBounds(150, 50, 200, 20);
        add(maxServiceTimeField);

        JLabel minServiceTimeLabel = new JLabel("Min Service Time:");
        minServiceTimeLabel.setBounds(20, 80, 120, 20);
        add(minServiceTimeLabel);

        minServiceTimeField = new JTextField();
        minServiceTimeField.setBounds(150, 80, 200, 20);
        add(minServiceTimeField);

        JLabel numberOfServersLabel = new JLabel("Number of Servers:");
        numberOfServersLabel.setBounds(20, 110, 120, 20);
        add(numberOfServersLabel);

        numberOfServersField = new JTextField();
        numberOfServersField.setBounds(150, 110, 200, 20);
        add(numberOfServersField);

        JLabel numberOfClientsLabel = new JLabel("Number of Clients:");
        numberOfClientsLabel.setBounds(20, 140, 120, 20);
        add(numberOfClientsLabel);

        numberOfClientsField = new JTextField();
        numberOfClientsField.setBounds(150, 140, 200, 20);
        add(numberOfClientsField);

        JLabel minArrivalTimeLabel = new JLabel("Min Arrival Time:");
        minArrivalTimeLabel.setBounds(20, 170, 120, 20);
        add(minArrivalTimeLabel);

        minArrivalTimeField = new JTextField();
        minArrivalTimeField.setBounds(150, 170, 200, 20);
        add(minArrivalTimeField);

        JLabel maxArrivalTimeLabel = new JLabel("Max Arrival Time:");
        maxArrivalTimeLabel.setBounds(20, 200, 120, 20);
        add(maxArrivalTimeLabel);

        maxArrivalTimeField = new JTextField();
        maxArrivalTimeField.setBounds(150, 200, 200, 20);
        add(maxArrivalTimeField);

        simulateButton = new JButton("Simulate");
        simulateButton.setBounds(150, 250, 100, 30);
        add(simulateButton);

        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int timeLimit = Integer.parseInt(timeLimitField.getText());
                int maxServiceTime = Integer.parseInt(maxServiceTimeField.getText());
                int minServiceTime = Integer.parseInt(minServiceTimeField.getText());
                int numberOfServers = Integer.parseInt(numberOfServersField.getText());
                int numberOfClients = Integer.parseInt(numberOfClientsField.getText());
                int minArrivalTime = Integer.parseInt(minArrivalTimeField.getText());
                int maxArrivalTime = Integer.parseInt(maxArrivalTimeField.getText());

                // Create SimulationFrame and pass the parameters to it

                SimulationFrame frame = new SimulationFrame(timeLimit, maxServiceTime, minServiceTime,
                        numberOfServers, numberOfClients, minArrivalTime, maxArrivalTime);
                frame.setVisible(true);
            }
        });
    }
}
