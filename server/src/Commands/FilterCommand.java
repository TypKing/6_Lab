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

public class FilterCommand extends Command {
    private final CommandExecution commandExecution;

    public FilterCommand(CommandExecution commandExecution) {
        setDescription("вывести элементы, значение поля name которых содержит заданную подстроку");
        setArgs(" name");
        this.commandExecution = commandExecution;
        setNeedWorker(false);
        setNeedArg(true);
        setTypeOfArg("string");
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды FILTER");
        if (arg.length != 1) {
            MyLogger.error("Выполнение провалено");
            throw new ArgException();
        }
        else {
            return commandExecution.filterName(collection,arg[0]);
        }
    }
}
