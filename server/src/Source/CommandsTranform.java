package Source;

import Commands.Command;
import CoreSource.CoreCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandsTranform {
    public static ArrayList<CoreCommand> transformArray(HashMap<String,Command> commands){
        ArrayList<CoreCommand> coreCommands = new ArrayList<CoreCommand>();
        for (Map.Entry<String,Command> entry: commands.entrySet()) {
            CoreCommand coreCommand = new CoreCommand(entry.getKey());
            coreCommand.setNeedArg(entry.getValue().isNeedArg());
            coreCommand.setNeedWorker(entry.getValue().isNeedWorker());
            coreCommand.setTypeOfArg(entry.getValue().getTypeOfArg());
            coreCommands.add(coreCommand);
        }
        return coreCommands;
    }
}
