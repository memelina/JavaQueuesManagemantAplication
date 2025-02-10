package BusinessLogic;

import GUI.FrameManager;
import GUI.SimulationFrame;

public class App {
    public static void main(String[] args) {
        FrameManager frameManager = new FrameManager();
        frameManager.setVisible(true);
        //SimulationManager simulationManager = new SimulationManager();
        Thread managerThread = new Thread(String.valueOf(frameManager));
         managerThread.start();
    }
}
