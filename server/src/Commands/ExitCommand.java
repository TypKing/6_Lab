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

public class ExitCommand extends Command {
        private final CommandExecution commandExecution;

        public ExitCommand(CommandExecution commandExecution){
            setDescription("завершить программу с сохранением файла");
            this.commandExecution = commandExecution;
            setNeedWorker(false);
        }

        @Override
        public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
            MyLogger.info("Выполнение команды EXIT");
            return commandExecution.exit();
        }
    }

