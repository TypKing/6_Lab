package Source;

import CoreSource.Answer;
import Exceptions.ConnectException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.channels.SocketChannel;

public class AnswerSending {
    public void SendAnswer(CommandReader commandReader, Answer answer,SocketChannel socketChannel){
        MyLogger.info("Отправка ответа");
        try {

            commandReader.sendToClient(answer,socketChannel);
            MyLogger.info("Ответ успешно отправлен");
        }catch (IOException e){
            MyLogger.error("Ответ не удалось отправить");
            throw new ConnectException("Возникли проблемы с отправкой ответа клиенту.");
        }
    }
}
