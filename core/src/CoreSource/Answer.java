package CoreSource;

import java.io.Serializable;
import java.util.ArrayList;

public class Answer implements Serializable {
    private String message;
    private ArrayList<Worker> workers;

    public Answer(String message, ArrayList<Worker> workers){
        this.workers = workers;
        this.message = message;
    }
    public Answer(String message){
        this.message = message;
    }
    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setWorkers(ArrayList<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        String answer = "";

        if (workers != null) {
            for (Worker worker:workers) {
                answer += worker.toString() + "\n";
            }

        }
        return
        "Ответ:\n" +
        message + "\n" + answer;

    }
}

