package BusinessLogic;

import GUI.SimulationFrame;
import Model.Server;
import Model.Task;

import javax.swing.*;
import java.util.*;

public class SimulationManager implements Runnable {
    public int timeLimit ;
    public int maxProcessingTime ;
    public int minProcessingTime ;
    public int numberOfServers ;
    public int numberOfClients ;
    public int minArrivalTime;
    public int maxArrivalTime;
    private int  ok=0;

    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks=Collections.synchronizedList(new ArrayList<>());
    private SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    // Adăugăm o referință către SimulationFrame



    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int numberOfServers, int numberOfClients, int minArrivalTime, int maxArrivalTime, SimulationFrame frame) {

        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.frame=frame;
        // Inițializăm schedulerul
        scheduler = new Scheduler(numberOfServers, maxProcessingTime);
        //creez threaduri
        for(int i=0;i<numberOfServers;i++)
        {
            // Server server = new Server();
            Thread serverThred= new Thread();
            serverThred.start();
        }
        // Inițializăm strategia de selecție
        scheduler.changeStrategy(selectionPolicy);


        // Generăm sarcinile aleatorii

        generateNRandomTasks();
    }



    private void generateNRandomTasks() {
        for (int i = 0; i < numberOfClients; i++) {
            int processingTime = (int) (Math.random() * (maxProcessingTime - minProcessingTime + 1) + minProcessingTime);
            int arrivalTime = (int) (Math.random() * (maxArrivalTime-minArrivalTime+1)+minArrivalTime);
            generatedTasks.add(new Task(i, arrivalTime, processingTime));
        }
        // Sortăm lista de sarcini în funcție de timpul de sosire
        if(selectionPolicy==SelectionPolicy.SHORTEST_TIME)
            generatedTasks.sort((task1, task2) -> task1.getArrivalTime() - task2.getArrivalTime());
        else if(selectionPolicy == SelectionPolicy.SHORTEST_QUEUE)
            generatedTasks.sort((task1, task2) -> task1.getServiceTime() - task2.getServiceTime());
    }

    @Override
    public void run() {
        frame.appendLog("Log of events:");
        int currentTime = 0;
        while (currentTime < timeLimit) {
            frame.appendLog("Time " + currentTime);

            // Verifică dacă trebuie să adaugi sarcini în cozi și le adaugă
            Iterator<Task> iterator = generatedTasks.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (selectionPolicy == SelectionPolicy.SHORTEST_TIME) {
                    if (task.getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(task);
                        iterator.remove(); // Elimină sarcina din lista de sarcini așteptate
                    }
                } else if (selectionPolicy == SelectionPolicy.SHORTEST_QUEUE) {
                    if (task.getServiceTime() == currentTime) {
                        scheduler.dispatchTask(task);
                        iterator.remove(); // Elimină sarcina din lista de sarcini așteptate
                    }
                }
            }

            // Afiseaza clientii asteptati
            frame.appendLog("Waiting clients:");
            for (Task t : generatedTasks) {
                frame.appendLog("(" + t.getID() + "," + t.getArrivalTime() + "," + t.getServiceTime() + ")");
            }

            // Afiseaza cozile de asteptare ale serverelor si actualizeaza serviceTime
            List<Task> queue = null;
            for (int i = 0; i < scheduler.getServers().size(); i++) {
                frame.appendLog("Queue " + (i + 1) + ": ");
                Server server = scheduler.getServers().get(i);
                queue = Arrays.asList(server.getTasks());
                if (queue.isEmpty()) {
                    frame.appendLog("closed");
                } else {
                    for (Task t : queue) {
                        frame.appendLog("(" + t.getID() + "," + t.getArrivalTime() + "," + t.getServiceTime() + "); ");
                        // Actualizare serviceTime
                        t.decreaseServiceTime();
                        // Daca serviceTime devine 0, elimina sarcina din server
                        if (t.getServiceTime() == 0) {
                            server.removeTask(t);
                        }
                        // Verifică dacă toate cozile sunt goale
                        if (t.getServiceTime() == 0 ) {
                            ok++;
                        }
                    }
                    System.out.println();
                }
            }

            // Actualizeaza interfata grafica
            // frame.updateServers(scheduler.getServers());

            currentTime++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(numberOfClients);
            System.out.println(ok);
            // Oprirea buclei dacă toate cozile sunt goale
            if (ok == numberOfClients && generatedTasks.isEmpty() && queue.isEmpty()) {
                break;
            }
        }

    }

}