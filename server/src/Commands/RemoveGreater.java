/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */

package Commands;

import Exceptions.ArgException;
import Source.Collection;
import Source.CommandManager;
import Source.CommandExecution;

import java.util.HashMap;
import CoreSource.*;
import Source.MyLogger;

public class RemoveGreater extends Command {
    private final CommandExecution commandExecution;

    public RemoveGreater(CommandExecution commandExecution) {
        setDescription("удалить из коллекции все элементы, превышающие заданный");
        this.commandExecution = commandExecution;
        setNeedWorker(true);
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды REMOVE GREATER");
        if (arg.length > 0) {
            MyLogger.error("Выполнение провалено");
            throw new ArgException();
        }
        else {
            return commandExecution.removeGreater(collection,worker);
        }
    }
}
