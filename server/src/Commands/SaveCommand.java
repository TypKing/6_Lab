/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */

package Commands;
import CoreSource.*;
import Exceptions.ArgException;
import Source.Collection;
import Source.CommandManager;
import Source.CommandExecution;
import Source.MyLogger;

import java.util.HashMap;

public class SaveCommand extends Command{
    private final CommandExecution commandExecution;

    public SaveCommand(CommandExecution commandExecution) {
        setDescription("сохранить коллекцию в файл");
        this.commandExecution = commandExecution;
        setNeedWorker(false);
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды SAVE");
        if (arg.length > 0) {
            MyLogger.error("Выполнение провалено");
            throw new ArgException();
        }
        else {
            return commandExecution.save(collection);
        }
    }
}
