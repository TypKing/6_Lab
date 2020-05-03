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
import CoreSource.*;
import Source.MyLogger;

import java.util.HashMap;

public class ClearCommand extends Command {
    private final CommandExecution commandExecution;

    public ClearCommand(CommandExecution commandExecution) {
        setDescription("очистить коллекцию");
        this.commandExecution = commandExecution;
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch,Worker worker, String... arg) {
        MyLogger.info("Выполнение команды CLEAR");
        if (arg.length > 0) {
            MyLogger.error("Выполнение провалено");
            throw new ArgException();
        }
        else {
            return commandExecution.clear(collection);
        }
    }
}
