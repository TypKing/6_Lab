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

public class UpdateCommand extends Command {
    private final CommandExecution commandExecution;

    public UpdateCommand(CommandExecution commandExecution) {
        setDescription("обновить значение элемента коллекции, id которого равен заданному");
        setArgs(" id");
        this.commandExecution = commandExecution;
        setNeedWorker(true);
        setNeedArg(true);
        setTypeOfArg("int");
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды UPDATE");
        try {
            return commandExecution.update(collection,Integer.parseInt(arg[0]),worker);
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            MyLogger.error("Выполнение провалено");
            throw new ArgException();
        }

    }
}
