package Source;/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */

import Commands.Command;
import CoreSource.Answer;
import CoreSource.Worker;

import java.util.HashMap;
import java.util.Scanner;

public class CommandManager {
    private final HashMap<String, Command> commandMap = new HashMap<>();
    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }
    Scanner scanner;
    /**
     * Выполняет команду с заданной commandName находя ее в commandMap
     *  @param commandName название команды
     * @param commandMap  карта команд
     * @param collection  коллекция с которой работают команды
     * @param worker
     * @param args        аргументы
     */
    public Answer execute(String commandName, HashMap<String, Command> commandMap, Collection collection, Worker worker, String... args) {
        if (!commandName.equals("")) {
            Command command = commandMap.get(commandName);
            if (command == null) {
                throw new IllegalStateException("no command registered for " + commandName);
            }
            return command.execute(commandMap, collection, this,worker, args);
        }
        return null;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }
}
