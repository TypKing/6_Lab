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

public class AddIfMaxCommand extends Command {
    private final CommandExecution commandExecution;

    public AddIfMaxCommand(CommandExecution commandExecution) {
        setDescription("добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.commandExecution = commandExecution;
        setNeedWorker(true);
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.error("Выполнение команды ADD IF MAX");
        if (arg.length > 0) {
            MyLogger.error("Выполнение неудалось");
            throw new ArgException();
        }
        else {
            return commandExecution.addIfMax(collection,worker);
        }
    }
}
