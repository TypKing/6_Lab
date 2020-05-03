import CoreSource.CoreCommand;
import CoreSource.Factory;
import CoreSource.Worker;
import Exceptions.ValidateException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandValidation {
    ArrayList<CoreCommand> coreCommands;

    Factory factory = new Factory();

    void getCommands(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        coreCommands = (ArrayList<CoreCommand> ) objectInputStream.readObject();
    }
    CoreCommand createCommand(String com) throws ValidateException{
        Worker worker = null;
            String[] com1 = com.trim().toLowerCase().split(" ");
            if (com1.length>1 && CheckWorker(com1[0])){
                CoreCommand coreCommand = new CoreCommand(com1[0],com1[1],factory.createWorker());
                ValidateCommand(coreCommand);
                return coreCommand;
            } else if (com1.length>1){
                CoreCommand coreCommand = new CoreCommand(com1[0],com1[1]);
                ValidateCommand(coreCommand);
                return coreCommand;
            } else if (CheckWorker(com1[0])){
                CoreCommand coreCommand = new CoreCommand(com1[0],factory.createWorker());
                ValidateCommand(coreCommand);
                return coreCommand;
            } else {
                CoreCommand coreCommand = new CoreCommand(com1[0]);
                ValidateCommand(coreCommand);
                return coreCommand;
            }
    }
    boolean CheckWorker(String commandName){
        for (CoreCommand coreCommand1 : coreCommands){
            if (commandName.equals(coreCommand1.getName())){
                return coreCommand1.isNeedWorker();
            }
        }
        throw new ValidateException();
    }

    void ValidateCommand(CoreCommand coreCommand){
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
                        throw new ValidateException();
                    }
                    if (coreCommand1.isNeedWorker() && !WorkerValidation.ValidateWorker(coreCommand.getWorker())){
                        throw  new ValidateException();
                    }else if(!coreCommand1.isNeedWorker() && coreCommand.getWorker()!=null){
                        throw new ValidateException();
                    }
                }
            }
            if (!isValidate) throw new ValidateException();
        }catch (NumberFormatException e){
            throw new ValidateException();
        }
    }
}
