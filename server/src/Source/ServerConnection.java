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
    private ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;
    private int port;
    public ServerConnection(int port){
        try {
            port = 8800;
            SocketAddress socketAddress = new InetSocketAddress(port);
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(socketAddress);
            serverSocketChannel.configureBlocking(false);
            MyLogger.info("Каналы связи переведены в неблокируемый режим");
        }catch (IOException e){
            MyLogger.error("Соединение неуспешно!");
            throw new ConnectException("Возникли проблемы с подключением.");
        }
    }
    public void connect(){
        try {
                MyLogger.info("Попытка соединения с клиентом");
                socketChannel = serverSocketChannel.accept();
                MyLogger.info("Соединение успешно");
                if(socketChannel!=null) {
                    socketChannel.configureBlocking(false);
                }
        }catch (IOException e){
            MyLogger.error("Соединение неуспешно!");
            throw new ConnectException("Возникли проблемы с подключением.");
        }
    }
    public void closeSocketChannel(){
        try {
            MyLogger.info("Закрытие сокета");
            socketChannel.close();
            socketChannel = null;
            MyLogger.info("Успешно");
        } catch (IOException e) {
            MyLogger.error("Не удалось закрыть сокет");
        }
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

}
