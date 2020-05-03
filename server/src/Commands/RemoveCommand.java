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

public class RemoveCommand extends Command {
    private final CommandExecution commandExecution;

    public RemoveCommand(CommandExecution commandExecution){
        setDescription("удалить элемент из коллекции по его id");
        setArgs(" id");
        this.commandExecution = commandExecution;
        setNeedWorker(false);
        setNeedArg(true);
        setTypeOfArg("int");
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды REMOVE");
        try {
            return commandExecution.remove(collection, Integer.parseInt(arg[0]));
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            MyLogger.error("Выполнение провалено");
            throw new ArgException();
        }
    }
}
