package Source;/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */

import Commands.Command;
import CoreSource.*;
import Decoration.Colors;
import Exceptions.ArgException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Receiver
 * Обеспечивает выполнение комманд
 */
public class CommandExecution {
    public Answer help(HashMap<String, Command> commandMap) {
        String answer = Colors.green() + "СПРАВКА" + Colors.reset() + "\n";
        for (Map.Entry<String, Command> entry : commandMap.entrySet()) {
            answer += Colors.blue() + entry.getKey() + entry.getValue().getArgs() + ": " + Colors.reset() + entry.getValue().getDescription() + "\n";
        }
        MyLogger.info("Выполнение успешно");
        return new Answer(answer);
    }

    /**
     * Выполняет выход их консоли
     *
     * @return
     */
    public Answer exit() {
        MyLogger.info("Выполнение успешно");
        return new Answer("Пока");
    }

    /**
     * Добавляет элемент в коллекцию
     *
     * @param collection сама коллекция
     * @return
     */
    public Answer add(Collection collection, Worker worker) {
        worker.setId(IdGenerator.generate());
        worker.setCreationDate(LocalDateTime.now());
        if (collection.add(worker)) {
            MyLogger.info("Выполнение успешно");
            return new Answer("Рабочий успешно добавлен!");
        } else {
            MyLogger.error("Выполнение провалено");
            return new Answer("Не удалось добавить Рабочего!");
        }
    }

    /**
     * Выводит информацию о коллекции
     *
     * @param collection сама коллекция
     * @return
     */
    public Answer info(Collection collection) {
        MyLogger.info("Выполнение успешно");
        return new Answer(collection.getInformation());
    }

    /**
     * Построчно выводит все элементы коллекции
     *
     * @param collection коллекция которую необходимо выводить
     * @return
     */
    public Answer show(Collection collection) {
        MyLogger.info("Выполнение успешно");
        return collection.show();
    }

    /**
     * Удаляет из коллекции элемент id которого равен заданному
     *
     * @param collection коллекция
     * @param id         заданный id
     * @return
     */
    public Answer remove(Collection collection, int id) {
        MyLogger.info("Выполнение успешно");
        return collection.remove(id);
    }

    /**
     * Заменяет элемент коллекции элемент id которого равен заданному
     *
     * @param collection коллекция
     * @param id         заданный id
     * @return
     */
    public Answer update(Collection collection, int id, Worker worker) {
        worker.setId(IdGenerator.generate());
        worker.setCreationDate(LocalDateTime.now());
        MyLogger.info("Выполнение успешно");
        return collection.replace(id, worker);
    }

    /**
     * Очищает коллекцию
     *
     * @param collection коллекция
     * @return
     */
    public Answer clear(Collection collection) {
        MyLogger.info("Выполнение успешно");
        return collection.clear();
    }

    /**
     * Добавляет новый элемент в коллекцию если его значение выше максимального
     *
     * @param collection коллекция
     * @return
     */
    public Answer addIfMax(Collection collection, Worker worker) {
        worker.setId(IdGenerator.generate());
        worker.setCreationDate(LocalDateTime.now());
        if (collection.addIfMax(worker)) {
            MyLogger.info("Выполнение успешно");
            return new Answer ("Рабочий успешно добавлен");
        } else {
            MyLogger.info("Выполнение успешно");
           return new Answer("Значение рабочего меньше максимального!");
        }
    }

    /**
     * Удаляет все элементы коллекции превышающий заданный
     *
     * @param collection коллекция
     * @return
     */
    public Answer removeGreater(Collection collection, Worker worker) {
        worker.setId(IdGenerator.generate());
        worker.setCreationDate(LocalDateTime.now());
        MyLogger.info("Выполнение успешно");
        return collection.removeGreater(worker);
    }

    /**
     * Удаляет все элементы коллекции меньше заданного
     *
     * @param collection коллекция
     * @return
     */
    public Answer removeLower(Collection collection, Worker worker) {
        worker.setId(IdGenerator.generate());
        worker.setCreationDate(LocalDateTime.now());
        MyLogger.info("Выполнение успешно");
        return collection.removeLower(worker);
    }

    /**
     * Подсчитывает количество элементов коллекции с salary больше заданного
     *
     * @param collection коллекция
     * @param arg        заданная зарплата
     * @return
     */
    public Answer countGreaterThanSalary(Collection collection, String arg) {
        try {
            double salary = Double.parseDouble(arg);
            MyLogger.info("Выполнение успешно");
            return new Answer("На данный момент мы имеем " + collection.countGreaterThanSalary(salary) + " рабочих чья " +
                    "зарплата выше " + salary + " рублей.");
        } catch (NumberFormatException e) {
            MyLogger.error("Выполнение провалено");
            throw new ArgException();
        }
    }

    /**
     * Сохраняет коллекцию в файл
     *
     * @param collection коллекция
     * @return
     */
    public Answer save(Collection collection) {
        collection.save();
        MyLogger.info("Выполнение успешно");
        return new Answer("Коллекция успешно сохранена");
    }

    /**
     * Выводит значения поля CoreSource.Person по возрастанию
     *
     * @return
     */
    public Answer printFieldAscendingPerson(Collection collection) {
        MyLogger.info("Выполнение успешно");
        return collection.printPersons();
    }

    /**
     * Выполняет скрипт
     *
     * @param file         исполняемый файл
     * @param mySwitch     наш Инвокер
     * @param myCollection коллекция
     * @return
     */
    public Answer executeScript(File file, CommandManager mySwitch, Collection myCollection) {
        StringBuilder answer = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            InputStream initialStream = new FileInputStream(file);
            Factory factory = new Factory();

            factory.update(initialStream);
            String line = "";
            int counter = 0;
            while (!line.equals("exit") && scanner.hasNextLine()) {
                counter++;
                line = scanner.nextLine();
                String[] lines = line.split(" ");
                try {
                    String command = lines[0];
                    if (lines.length > 1) {
                        String arg = lines[1];
                        try {
                            answer.append(mySwitch.execute(command, mySwitch.getCommandMap(), myCollection, null, arg));
                        } catch (IllegalStateException | ArgException e) {
                            return new Answer("Не удалось выполнить скрипт, ошибка в строке номер:" + counter);
                        }
                    } else {
                        try {
                            answer.append(mySwitch.execute(command, mySwitch.getCommandMap(), myCollection, null));
                        } catch (IllegalStateException | ArgException e) {
                            return new Answer("Не удалось выполнить скрипт, ошибка в строке номер:" + counter);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new Answer("Не удалось выполнить скрипт, ошибка в строке номер:" + counter);
                }
            }
        } catch (FileNotFoundException e) {
           return new Answer("Скрипт не найден.");
        }
        MyLogger.info("Выполнение успешно");
        return new Answer(answer + "Скрипт успешно выполнен!");
    }

    public Answer filterName(Collection collection, String arg) {
        StringBuilder answer = new StringBuilder();
        for (Worker worker : collection.getCollection()) {
            if (worker.getName().contains(arg)) {
                answer.append(worker.toString());
            }
        }
        MyLogger.info("Выполнение успешно");
        return new Answer(answer.toString());
    }
}
