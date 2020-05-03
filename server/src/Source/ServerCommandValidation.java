package Source;

import CoreSource.CoreCommand;
import CoreSource.Factory;
import CoreSource.Worker;
import Exceptions.ServerValidateException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ServerCommandValidation {
    ArrayList<CoreCommand> coreCommands;

    Factory factory = new Factory();

    boolean CheckWorker(String commandName){
        for (CoreCommand coreCommand1 : coreCommands){
            if (commandName.equals(coreCommand1.getName())){
                return coreCommand1.isNeedWorker();
            }
        }
        throw new ServerValidateException();
    }

    void ValidateCommand(CoreCommand coreCommand, ArrayList<CoreCommand> coreCommands){
        boolean isValidate=false;
        try {
            for (CoreCommand coreCommand1 : coreCommands) {
                if (coreCommand1.getName().equals(coreCommand.getName())) {
                    isValidate = true;
                    if (coreCommand1.isNeedArg()) {
                        switch (coreCommand1.getTypeOfArg().toLowerCase().trim()) {
                            case "float":
                                Float.parseFloat(coreCommand.getArg());
                                break;
                            case "int":
                                Integer.parseInt(coreCommand.getArg());
                                break;
                            case "double":
                                Double.parseDouble(coreCommand.getArg());
                                break;
                            case "long":
                                Long.parseLong(coreCommand.getArg());
                                break;
                        }

                    }else if(coreCommand.getArg()!=null){
                        throw new ServerValidateException();
                    }
                    if (coreCommand1.isNeedWorker() && !ServerWorkerValidation.ValidateWorker(coreCommand.getWorker())){
                        throw  new ServerValidateException();
                    }else if(!coreCommand1.isNeedWorker() && coreCommand.getWorker()!=null){
                        throw new ServerValidateException();
                    }
                }
            }
            if (!isValidate) throw new ServerValidateException();
        }catch (NumberFormatException e){
            throw new ServerValidateException();
        }
    }
}
