package GUI;

import BusinessLogic.SimulationManager;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {
    private JTextArea logTextArea;

    public SimulationFrame(int timeLimit, int maxServiceTime, int minServiceTime, int numberOfServers, int numberOfClients, int minArrivalTime, int maxArrivalTime) {
        setTitle("Simulation Results");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        Font font = new Font("Arial", Font.PLAIN, 16);
        logTextArea.setFont(font);

        JScrollPane scrollPane = new JScrollPane(logTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Execute simulation based on the parameters passed
        SimulationManager simulationManager = new SimulationManager(timeLimit, maxServiceTime, minServiceTime, numberOfServers, numberOfClients, minArrivalTime, maxArrivalTime, this);
        Thread simulationThread = new Thread(simulationManager);
        simulationThread.start();
    }

    public void appendLog(String log) {
        logTextArea.append(log + "\n");
    }
}
