/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */

package Commands;

import CoreSource.Answer;
import CoreSource.Worker;
import Exceptions.ArgException;
import Source.Collection;
import Source.CommandManager;
import Source.CommandExecution;
import Source.MyLogger;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

public class ExecuteScript extends Command {
    private final CommandExecution commandExecution;

    public ExecuteScript(CommandExecution commandExecution) {
        setDescription("добавить новый элемент в коллекцию");
        setArgs(" file_name");
        this.commandExecution = commandExecution;
        setNeedArg(true);
        setTypeOfArg("string");
        setNeedWorker(false);
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды EXECUTE SCRIPT");
        String path;
        String filePath = System.getenv("INPUT_PATH");
        if (filePath == null) {
            path = "server\\src\\Files\\";
        } else {
            path = filePath;
        }
        if (arg.length != 1) {
            MyLogger.error("Выполнение неудалось");
            throw new ArgException();
        }
        else {
            File file = new File(path+arg[0]);
            return commandExecution.executeScript(file,mySwitch,collection);
        }
    }
}
