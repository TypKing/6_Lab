package Source;

import Commands.*;
import CoreSource.Answer;
import CoreSource.CoreCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessing {
    private Command command;
    private CoreCommand coreCommand;
    private static final String DEFAULT_PATH = "server\\src\\Files\\data.json";
    CommandExecution commandExecution = new CommandExecution();
    CommandManager commandManager = new CommandManager();
    File collectionFile;
    Collection collection;
    public CommandProcessing(){
        String filePath = System.getenv("INPUT_PATH");
        if(filePath == null){
            filePath = DEFAULT_PATH;
        }
        collectionFile = new File(filePath);
        collection = new Collection(collectionFile);
    }
    public Answer processCommand(CoreCommand coreCommand){
        MyLogger.info("Обработка команд на сервере");
        if(coreCommand.getArg()==null) {
            return commandManager.execute(coreCommand.getName(), commandManager.getCommandMap(), collection, coreCommand.getWorker());
        }else{
            return commandManager.execute(coreCommand.getName(), commandManager.getCommandMap(), collection, coreCommand.getWorker(),coreCommand.getArg());
        }
    }
    public void registerServerCommands(){
        MyLogger.info("Регистрация команд на сервере");
        Command filter = new FilterCommand(commandExecution);
        Command help = new HelpCommand(commandExecution);
        Command info = new InfoCommand(commandExecution);
        Command exit = new ExitCommand(commandExecution);
        Command add = new AddCommand(commandExecution);
        Command show = new ShowCommand(commandExecution);
        Command remove = new RemoveCommand(commandExecution);
        Command update = new UpdateCommand(commandExecution);
        Command clear = new ClearCommand(commandExecution);
        Command addIfMax = new AddIfMaxCommand(commandExecution);
        Command removeGreater = new RemoveGreater(commandExecution);
        Command removeLower = new RemoveLower(commandExecution);
        Command countGreaterThanSalary = new CountGreaterThanSalary(commandExecution);
        Command printfields = new PrintFieldAscendingPerson(commandExecution);
        Command save = new SaveCommand(commandExecution);
        Command execute = new ExecuteScript(commandExecution);

        commandManager.register("filter_contains_name", filter);
        commandManager.register("save", save);
        commandManager.register("execute_script", execute);
        commandManager.register("count_greater_than_salary", countGreaterThanSalary);
        commandManager.register("print_field_ascending_person", printfields);
        commandManager.register("help", help);
        commandManager.register("info", info);
        commandManager.register("exit", exit);
        commandManager.register("add", add);
        commandManager.register("show", show);
        commandManager.register("update", update);
        commandManager.register("remove_by_id", remove);
        commandManager.register("clear", clear);
        commandManager.register("add_if_max", addIfMax);
        commandManager.register("remove_greater", removeGreater);
        commandManager.register("remove_lower", removeLower);


    }

    public Collection getCollection() {
        return collection;
    }

    public HashMap<String,Command> getServerCommands(){
        return commandManager.getCommandMap();
    }
    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public CoreCommand getCoreCommand() {
        return coreCommand;
    }

    public void setCoreCommand(CoreCommand coreCommand) {
        this.coreCommand = coreCommand;
    }
}
