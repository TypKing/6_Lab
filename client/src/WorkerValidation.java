import CoreSource.Worker;
import Exceptions.FieldException;
import Exceptions.FileArgsException;
import com.google.gson.Gson;

public class WorkerValidation {
    public static boolean ValidateWorker(Worker worker){
            try {
                worker.getSalary();
                worker.getId();
                worker.getName();
                worker.getCoordinates();
                worker.getCoordinates().getX();
                worker.getCoordinates().getY();
                if(worker.getPerson() != null){
                    worker.getPerson().getLocation();
                    worker.getPerson().getWeight();
                    worker.getPerson().getHeight();
                    worker.getPerson().getPassportID();
                    worker.getPerson().getLocation().getX();
                    worker.getPerson().getLocation().getY();
                }
                worker.getCreationDate();
                worker.getPosition();
                worker.getStatus();
                return true;
            }catch (FieldException | NullPointerException e){
                return false;
            }
    }
    }

