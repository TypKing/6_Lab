/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */

package Commands;

import CoreSource.Answer;
import Source.Collection;
import Source.CommandManager;
import CoreSource.Worker;

import java.util.HashMap;

public abstract class Command {
    /**
     * Абсрактный класс Команды содержит в себе описание команды, ее аргументы и выполение
     */
    private String description;
    private String args = "";
    private boolean needWorker = false;
    private boolean needArg = false;
    private String typeOfArg = "no";

    public boolean isNeedArg() {
        return needArg;
    }

    public void setNeedArg(boolean needArg) {
        this.needArg = needArg;
    }

    public void setTypeOfArg(String typeOfArg) {
        this.typeOfArg = typeOfArg;
    }

    public String getTypeOfArg() {
        return typeOfArg;
    }

    /**
     * Выполняет команду
     * @param hashMap Карта всех команд
     * @param collection коллекция для работы
     * @param mySwitch инвокер
     * @param worker
     * @param arg аргументы
     */
    public Answer execute(HashMap<String, Command> hashMap, Collection collection, CommandManager mySwitch, Worker worker, String... arg) {
        return null;
    };

    /**
     * @return Описание команды
     */
    public String getDescription() {
        return description;
    }

    public boolean isNeedWorker() {
        return needWorker;
    }

    public void setNeedWorker(boolean needWorker) {
        this.needWorker = needWorker;
    }

    /**
     * Изменяет аргументы
     * @param args аргументы команды
     */
    public void setArgs(String args) {
        this.args = args;
    }

    /**
     *
     * @return аргументы команды
     */
    public String getArgs() {
        return args;
    }

    /**
     * Изменяет описание команды
     * @param description новое описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
