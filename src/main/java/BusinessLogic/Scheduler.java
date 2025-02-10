package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
   private List<Server> servers= new ArrayList<>();
   private int maxNoServers;
   private int maxTasksPerServer;
   private Strategy strategy;

   public Scheduler(int maxNoServers, int maxTasksPerServer) {

      for (int i = 0; i < maxNoServers; i++) {
         Server server = new Server();
         servers.add(server);
         Thread serverThread = new Thread(server);
        // serverThread.start();

      }
   }

   public void changeStrategy(SelectionPolicy policy) {
      // Aplicăm pattern-ul Strategy pentru a instanția strategia cu strategia concretă corespunzătoare politicii
      if (policy == SelectionPolicy.SHORTEST_QUEUE) {
         this.strategy = new ConcreteStrategyQueue();
      }
      if (policy == SelectionPolicy.SHORTEST_TIME) {
         this.strategy = new ConcreteStrategyTime();
      }
   }

   public void dispatchTask(Task t) {
     if(strategy instanceof  ConcreteStrategyQueue)
     {
        ConcreteStrategyQueue s= (ConcreteStrategyQueue) strategy;
        s.addTask(servers,t);
     }
     else if(strategy instanceof ConcreteStrategyTime)
     {
        ConcreteStrategyTime st= (ConcreteStrategyTime) strategy;
        st.addTask(servers,t);
     }
   }

   public List<Server> getServers() {
      return servers;
   }

}
