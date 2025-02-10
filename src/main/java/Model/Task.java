package Model;

public class Task {
    private int ID;
    private int arrivalTime;
    private int serviceTime; // Corectăm numele atributului

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public void decreaseServiceTime() {
        if (serviceTime > 0) {
            serviceTime--;
        }
    }
    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    // Adăugăm metoda pentru a obține timpul de procesare
    public int getProcessingTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", arrivalTime=" + arrivalTime +
                ", serviceTime=" + serviceTime +
                '}';
    }
}
