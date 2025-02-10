package BusinessLogic;

import BusinessLogic.Strategy;
import Model.Server;
import Model.Task;


import java.util.List;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        int minTasks = Integer.MAX_VALUE;
        Server minServer = null;

        // Iterate through servers to find the one with the minimum number of tasks
        for (Server server : servers) {
            int numTasks = server.getNumTask();
            if (numTasks < minTasks) {
                minTasks = numTasks;
                minServer = server;
            }
        }

        if (minServer != null) {
            minServer.addTask(task);
        }
    }


}