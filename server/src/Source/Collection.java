package Source;/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */


import CoreSource.Answer;
import CoreSource.IdGenerator;
import CoreSource.Person;
import CoreSource.Worker;
import Decoration.*;
import Exceptions.FileException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Collection {
    private LinkedHashSet<Worker> collection = new LinkedHashSet<Worker>();
    private HashSet<String> passportSet = new HashSet<String>();
    private String type = getCollection().getClass().getTypeName();
    private LocalDateTime creationTime;
    private int countOfWorkers = 0;
    private File file;

    Collection(File file) {
        FileReader fileReader = new FileReader();
        this.file = file;
        try {
            LinkedHashSet <Worker> collection1 = fileReader.read(file);
            setCountOfWorkers(getCountOfWorkers() + collection1.size());
            collection.addAll(collection1);
            passportSet.addAll(fileReader.getPassportSet());
            IdGenerator.addAll(fileReader.getIdSet());
        }catch (FileException e){
            System.out.println(e.getMessage());
        }
        setCreationTime(LocalDateTime.now());
    }
    /**
     * Создает нового рабочего и добавляет его в коллекцию
     * @return
     */
    public boolean add(Worker worker) {
        int size = getCollection().size();
        getCollection().add(worker);
        setCountOfWorkers(getCountOfWorkers() + 1);
        return getCollection().size() > size;
    }

    /**
     * Создает рабочего и добавляет его в коллекцию если его значение максимально
     * @return возвращает true если получилось добавить и false если нет
     */
    public boolean addIfMax(Worker worker){
        if(isMax(worker)){
            getCollection().add(worker);
            setCountOfWorkers(getCountOfWorkers()+1);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Проверяет рабочего на максимальность в коллекции
     * @param worker рабочий для сравнения
     * @return true если максимален и false если нет
     */
    public boolean isMax(Worker worker) {
        boolean i = true;
        for (Worker worker1 : collection) {
            if (worker.compareTo(worker1) <= 0) {
                i = false;
                break;
            }
        }
        return i;
    }

    /**
     * Выводит построчно все элементы коллекции
     * @return
     */
    public Answer show() {
        Answer answer;
        ArrayList<Worker> workers = new ArrayList<Worker>(Arrays.asList(getCollection().stream().toArray(Worker[]::new)));
        return new Answer("Список рабочих:",workers);
    }

    /**
     * Заменяет элемент коллекции по его id
     * @param id заданный id
     * @return
     */
    public Answer replace(int id, Worker newWorker) {
        if (!getCollection().isEmpty()) {
            if (collection.stream().filter(worker -> worker.getId()==id).count() >= 1) {
                LinkedHashSet<Worker> workers = new LinkedHashSet<Worker>();
                collection.stream().filter(worker -> worker.getId() != id).forEach(workers::add);
                newWorker.setId(id);
                workers.add(newWorker);
                collection = workers;
                return new Answer("Рабочик заменен");
            }else return new Answer("Рабочий не найдет");

        } else {
            return new Answer("Коллекция пуста!");
        }
    }

    /**
     * Удаляет элемент коллекции с заданным id
     * @param id заданный id
     * @return
     */
    public Answer remove(int id) {
        LinkedHashSet<Worker> collection1 = new LinkedHashSet<Worker>();
        if (!getCollection().isEmpty()) {
            if(collection.stream().filter(worker -> worker.getId()==id).count() == 1) {
                collection.stream().filter(worker -> worker.getId() != id).forEach(collection1::add);
                countOfWorkers--;
                IdGenerator.remove(id);
                collection = collection1;
                return new Answer("Рабочик успешно устранен");
            }else{
                return new Answer("Рабочий не найден");
            }
        } else {
            return new Answer("Коллекция пуста");
        }
    }
    /**
     * Удаляет все элементы коллекции превыщающие заданный
     * @return
     */
    public Answer removeGreater(Worker newWorker){
        LinkedHashSet<Worker> collection1 = new LinkedHashSet<>();
        IdGenerator.clear();
        countOfWorkers = 0;
        collection.forEach(worker -> {
            if(newWorker.compareTo(worker)>=0){
                collection1.add(worker);
                countOfWorkers++;
                IdGenerator.push(worker.getId());
            }
        });
        collection = collection1;
        return new Answer("Удалены все рабочие больше заданного!");
    }
    /**
     * Удаляет все элементы коллекции меньшие чем заданный
     * @return
     */
    public Answer removeLower(Worker newWorker){
        LinkedHashSet<Worker> collection1 = new LinkedHashSet<>();
        IdGenerator.clear();
        countOfWorkers = 0;
        collection.stream().forEach(worker -> {
            if(newWorker.compareTo(worker)<=0){
                collection1.add(worker);
                countOfWorkers++;
                IdGenerator.push(worker.getId());
            }
        });
        collection = collection1;
        return new Answer("Рабочие меньше текущего удалены.");
    }

    /**
     * Очищает коллекцию
     * @return
     */
    public Answer clear(){
        countOfWorkers -= collection.size();
        collection.clear();
        IdGenerator.clear();
        return new Answer("Коллекция очищена!");
    }
    public Answer printPersons(){
        ArrayList<Person> persons = new ArrayList<Person>();
        collection.stream().forEach(worker -> persons.add(worker.getPerson()));
        AtomicReference<String> answer = new AtomicReference<>("");
        persons.stream().sorted().forEach(person -> answer.updateAndGet(v -> v + person + "\n"));
        return new Answer(answer.get());
    }
    /**
     * Выводит информацию о коллекции
     * @return строка информации
     */
    public String getInformation() {
        return Colors.green() + "Информация о коллекции:\n" + Colors.blue() + " Дата инициализации коллекции: " + Colors.reset() +
                getCreationTime() + Colors.blue() + "\n Тип коллекции: " + Colors.reset() + getType() + "\n " +
                Colors.blue() + "Количество элементов в коллекции: " + Colors.reset() + getCountOfWorkers();
    }

    /**
     * Подсчитывает количество элементов коллекции с salary больше заданной
     * @param salary1 заданная зарплата
     * @return количество элементов
     */
    public long countGreaterThanSalary(double salary1){
        return collection.stream().filter(worker -> worker.getSalary() > salary1).count();
    }

    /**
     * @return возвращает коллекцию
     */
    public LinkedHashSet<Worker> getCollection() {
        return collection;
    }

    public void setCollection(LinkedHashSet<Worker> collection) {
        this.collection = collection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreationTime() {
        return creationTime.toString();
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public int getCountOfWorkers() {
        return countOfWorkers;
    }

    /**
     * Сохраняет коллекцию
     */
    public void save(){
        try {
            FileWriter writer1 = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(writer1);
            writer.write("[\n");
            int i = 0;
            for(Iterator<Worker> iterator = collection.iterator(); iterator.hasNext();){
                i++;
                writer.write(iterator.next().toJSON());
                if(i<collection.size()){
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Не получилось записать файл");
            e.printStackTrace();
        }
    }

    /**
     * изменяет число рабочих
     * @param countOfWorkers количество рабочих
     */
    public void setCountOfWorkers(int countOfWorkers) {
        this.countOfWorkers = countOfWorkers;
    }
}
