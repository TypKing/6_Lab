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

public class CountGreaterThanSalary extends Command {
    private final CommandExecution commandExecution;

    public CountGreaterThanSalary(CommandExecution commandExecution) {
        setDescription("вывести элементы, значение поля name которых больше заданного");
        setArgs(" salary");
        this.commandExecution = commandExecution;
        setNeedWorker(false);
        setNeedArg(true);
        setTypeOfArg("double");
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды COUNT GREATER THAN SALARY");
        if (arg.length != 1){
            MyLogger.error("Выполнение провалено");
            throw new ArgException();
        }
        else {
            return commandExecution.countGreaterThanSalary(collection,arg[0]);
        }
    }
}
