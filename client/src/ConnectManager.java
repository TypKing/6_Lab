import CoreSource.Answer;
import CoreSource.CoreCommand;
import Exceptions.ConnectException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ConnectManager {
    InetAddress address;
    private Integer port;
    Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    public ConnectManager(Integer port, InetAddress address){
        this.address = address;
        this.setPort(port);
    }
    public void connect(CommandValidation commandValidation){
        try {
            socket = new Socket(address, getPort());
            setObjectInputStream(new ObjectInputStream(socket.getInputStream()));
            System.out.println("Соединение с сервером установлено. Получаю список команд");
            commandValidation.getCommands(getObjectInputStream());
            System.out.println("Команды с сервера получены");

        }catch (IOException e){
            System.out.println("Не удалось подключиться к серверу!");
            System.out.println("Желаете попробовать еще раз? Да/Нет");
            Scanner scanner = new Scanner(System.in);
            if ("Да".equals(scanner.nextLine())) {
                connect(commandValidation);
            }
            throw new ConnectException();
        }catch (ClassNotFoundException e){
            System.out.println("Не удалось получить список команд");
            System.out.println("Желаете попробовать еще раз? Да/Нет");
            Scanner scanner = new Scanner(System.in);
            if ("Да".equals(scanner.nextLine())) {
                connect(commandValidation);
            }
        }

    }
    public void disconnect(){
        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();

        }catch (IOException ignored){
        }
    }
    public void sendCommand(CoreCommand coreCommand){
        try {
            setObjectOutputStream(new ObjectOutputStream(socket.getOutputStream()));
            objectOutputStream.writeObject(coreCommand);
        }catch (IOException e){
            System.out.println("Возникли проблемы с отправкой команды");
        }
    }
    public Answer getAnswerFromServer(){
        try {
            return (Answer) new ObjectInputStream(socket.getInputStream()).readObject();
        }catch (ClassNotFoundException | IOException e){
            System.out.println("Ответ не получен");
            return null;
        }
    }
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }
}
