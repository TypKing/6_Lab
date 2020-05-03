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

public class RemoveLower extends Command {
    private final CommandExecution commandExecution;

    public RemoveLower(CommandExecution commandExecution) {
        setDescription("удалить из коллекции все элементы, меньшие, чем заданный");
        this.commandExecution = commandExecution;
        setNeedWorker(true);
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды REMOVE LOWER");
        if (arg.length > 0) {
            MyLogger.error("Выполнение провалено");
            throw new ArgException();
        }
        else {
            return commandExecution.removeLower(collection,worker);
        }
    }
}
