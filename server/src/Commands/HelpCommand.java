/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */

package Commands;


import Source.Collection;
import Source.CommandManager;
import Source.CommandExecution;
import CoreSource.*;
import Source.MyLogger;

import java.util.HashMap;

public class HelpCommand extends Command {
    private final CommandExecution commandExecution;

    public HelpCommand(CommandExecution commandExecution){
        setDescription("вывести справку по доступным командам");
        this.commandExecution = commandExecution;
        setNeedWorker(false);
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        MyLogger.info("Выполнение команды HELP");
        return commandExecution.help(commandMap);
    }
}
