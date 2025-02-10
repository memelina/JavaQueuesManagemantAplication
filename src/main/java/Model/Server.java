package Model;

import java.util.concurrent.ArrayBlockingQueue; // Importăm ArrayBlockingQueue pentru inițializarea cozii
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server() {
        // Inițializăm coada de sarcini cu o capacitate fixă (de exemplu, 100)
        this.tasks = new LinkedBlockingQueue<>();
        // Inițializăm perioada de așteptare cu 0
        this.waitingPeriod = new AtomicInteger(0);
    }



    // Restul codului rămâne neschimbat
    public void addTask(Task newTask) {
        tasks.add(newTask);
        // System.out.println("task adaugat");
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task currentTask = tasks.take();
                Thread.sleep(currentTask.getServiceTime());
                waitingPeriod.addAndGet(-currentTask.getServiceTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public int getNumTask(){
        return tasks.size();
    }
    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }


}
