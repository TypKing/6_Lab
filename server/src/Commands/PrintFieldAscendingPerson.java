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

public class PrintFieldAscendingPerson extends Command {
    private final CommandExecution commandExecution;

    public PrintFieldAscendingPerson(CommandExecution commandExecution) {
        setDescription("вывести значения поля person в порядке возрастания");
        this.commandExecution = commandExecution;
        setNeedWorker(false);
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды PRINT FIELD ASCENDING PERSON");
        if (arg.length > 0) {
            MyLogger.error("Выполнение провалено");
            throw new ArgException();
        }
        else {
            return commandExecution.printFieldAscendingPerson(collection);
        }
    }
}
