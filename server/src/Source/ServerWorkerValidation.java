package Source;

import CoreSource.Worker;
import Exceptions.FieldException;

public class ServerWorkerValidation {
    public static boolean ValidateWorker(Worker worker){
        MyLogger.info("Валидация рабочего");
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
                MyLogger.info("Рабочий подходит");
                return true;
            }catch (FieldException | NullPointerException e){
                MyLogger.error("Рабочий говно");
                return false;
            }
    }
    }

