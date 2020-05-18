package Source;

import CoreSource.Answer;
import Exceptions.ConnectException;
import Exceptions.ServerValidateException;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class ServerMain {
    public static void main(String[] args) {
        ServerConnection serverConnection = new ServerConnection(8800);
        CommandReader commandReader = new CommandReader();
        CommandProcessing commandProcessing = new CommandProcessing();
        commandProcessing.registerServerCommands();
        Answer answer;
        AnswerSending answerSending = new AnswerSending();
        ServerCommandValidation serverCommandValidation = new ServerCommandValidation();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            MyLogger.info("Экстренное завершнение работы, сохраняю коллекцию");
            commandProcessing.commandExecution.save(commandProcessing.getCollection());
        }));
        serverConnection.connect();
        while (true) {
            int i = 0;
            while (serverConnection.getSocketChannel()!=null) {
                try {
                    commandReader.setCoreCommand(null);
                    if(i == 0) {
                        commandReader.sendToClient(CommandsTranform.transformArray(commandProcessing.getServerCommands()), serverConnection.getSocketChannel());
                        i++;
                    }
                    commandReader.getCommandFromClient(serverConnection);
                    if(commandReader.getCoreCommand()!=null) {
                        try {
                            serverCommandValidation.ValidateCommand(commandReader.getCoreCommand(), CommandsTranform.transformArray(commandProcessing.getServerCommands()));
                            answer = commandProcessing.processCommand(commandReader.getCoreCommand());
                            commandReader.sendToClient(answer, serverConnection.getSocketChannel());
                            serverConnection.closeSocketChannel();
                        } catch (ServerValidateException e) {
                            commandReader.sendToClient(new Answer("Команда не прошла серверную валидацию"), serverConnection.getSocketChannel());
                        }
                    }
                    //      answerSending.SendAnswer(commandReader,answer,serverConnection.getSocketChannel());
                } catch (ConnectException | IOException e) {
                    e.printStackTrace();
                    System.out.println("Ожидается команда от клиента.");
                }
            }
            while (serverConnection.getSocketChannel()==null){
                try {
                    MyLogger.info("Ожидаем подключение клиента");
                    Thread.sleep(3000);
                }catch (InterruptedException e){

                }
                serverConnection.connect();
            }

        }


    }

}
