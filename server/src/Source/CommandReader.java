package Source;

import CoreSource.CoreCommand;
import Exceptions.ConnectException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class CommandReader {
    private CoreCommand coreCommand;
    public void getCommandFromClient(SocketChannel socketChannel){
        MyLogger.info("Получение команды с клиента");
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteBuffer byteBuffer = ByteBuffer.allocate(6553600);
            int n=0;
            int i=0;
            while ((n=socketChannel.read(byteBuffer))>0){
                i++;
                MyLogger.info("Заполнение буфера номер - " + i);
                byteBuffer.flip();
                byteArrayOutputStream.write(byteBuffer.array(),0,n);
                byteBuffer.clear();
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            setCoreCommand((CoreCommand)objectInputStream.readObject());
            MyLogger.info("Успешно");

        }catch (IOException  e ){
            MyLogger.error("Не удалось получить команду");
            throw new ConnectException("Возникли проблемы с чтением команды с клиента");
        }catch (ClassNotFoundException e){
            MyLogger.error("Не удалось распознать класс команды");
            throw new ConnectException("Класс не найден");
        }
    }
    public void sendToClient(Object object, SocketChannel socketChannel) throws IOException {
        MyLogger.info("Отправка клиенту");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);

        byte[] out = byteArrayOutputStream.toByteArray();
        ByteBuffer byteBuffer = ByteBuffer.wrap(out);
        int send=0;
        while((send=socketChannel.write(byteBuffer))>0);

        byteBuffer.clear();
        byteArrayOutputStream.flush();
        objectOutputStream.flush();
        MyLogger.info("Успешно");
    }
    public CoreCommand getCoreCommand() {
        return coreCommand;
    }

    public void setCoreCommand(CoreCommand coreCommand) {
        this.coreCommand = coreCommand;
    }
}
