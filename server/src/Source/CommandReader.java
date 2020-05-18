package Source;

import CoreSource.CoreCommand;
import Exceptions.ConnectException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class CommandReader {
    private CoreCommand coreCommand;
    public void getCommandFromClient(ServerConnection serverConnection){
        MyLogger.info("Получение команды с клиента");
        try {
            Thread.sleep(1000);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteBuffer byteBuffer = ByteBuffer.allocate(65536000);
            int n=0;
            int i=0;
            while ((n=serverConnection.getSocketChannel().read(byteBuffer))>0){
                i++;
                MyLogger.info("Заполнение буфера номер - " + i);
                byteBuffer.flip();
                byteArrayOutputStream.write(byteBuffer.array(),0,n);
                byteBuffer.clear();
            }
            if(i!=0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                setCoreCommand((CoreCommand) objectInputStream.readObject());
                MyLogger.info("Успешно");
            }
        }catch (IOException  e ){
            e.printStackTrace();
            MyLogger.error("Не удалось получить команду");
            serverConnection.closeSocketChannel();
            throw new ConnectException("Возникли проблемы с чтением команды с клиента");
        }catch (ClassNotFoundException e){
            MyLogger.error("Не удалось распознать класс команды");
            throw new ConnectException("Класс не найден");
        }catch (InterruptedException e){
            MyLogger.error("Не удалось поспать");
        }
    }
    public void sendToClient(Object object, SocketChannel socketChannel) throws IOException {
        MyLogger.info("Отправка клиенту");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        byte[] out = byteArrayOutputStream.toByteArray();
        ByteBuffer byteBuffer = ByteBuffer.wrap(out);
        if (socketChannel.isConnected() && byteBuffer.limit()!=0) {
            socketChannel.write(byteBuffer);
        }
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
