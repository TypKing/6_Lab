package Source;

import Exceptions.ConnectException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerConnection {
    private SocketAddress socketAddress;
    private ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Selector selector;
    public void connect(int port){
        try {
                MyLogger.info("Попытка соединения с клиентом");
                setSocketAddress(new InetSocketAddress(port));
                setServerSocketChannel(ServerSocketChannel.open());
                serverSocketChannel.bind(socketAddress);
                setSocketChannel(getServerSocketChannel().accept());
                MyLogger.info("Соединение успешно");
                selector = Selector.open();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ);
            MyLogger.info("Каналы связи переведены в неблокируемый режим");
        }catch (IOException e){
            MyLogger.error("Соединение неуспешно!");
            throw new ConnectException("Возникли проблемы с подключением.");
        }
    }
    public void disconnect(){
        try {
            MyLogger.info("Попытка отсоединения сервера");
            getServerSocketChannel().close();
            getSocketChannel().close();
            selector.close();
            MyLogger.info("Отсоединение успешно");
        }catch (IOException e){
            MyLogger.error("Возникли проблемы с отключением");
            throw new ConnectException("Возникли проблемы с отключением.");
        }

    }
    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public void setServerSocketChannel(ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public Selector getSelector() {
        return selector;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }
}
