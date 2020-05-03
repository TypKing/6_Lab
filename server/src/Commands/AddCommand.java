/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */

package Commands;

import Exceptions.ArgException;
import CoreSource.*;
import Source.Collection;
import Source.CommandManager;
import Source.CommandExecution;
import Source.MyLogger;


import java.util.HashMap;

public class AddCommand extends Command {
    private final CommandExecution commandExecution;

    public AddCommand(CommandExecution commandExecution) {
        setDescription("добавить новый элемент в коллекцию");
        this.commandExecution = commandExecution;
        setNeedWorker(true);
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды ADD");
        if (arg.length > 0) {
            MyLogger.error("Выполнение команды провалилось");
            throw new ArgException();
        }
        else {
            return commandExecution.add(collection,worker);
        }
    }
}
