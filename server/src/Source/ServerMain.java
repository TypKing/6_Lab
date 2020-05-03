package Source;
import CoreSource.Answer;
import Exceptions.ConnectException;
import Exceptions.ServerValidateException;

import java.io.IOException;
import java.util.Scanner;


public class ServerMain {
    public static void main(String[] args) {
        ServerConnection serverConnection = new ServerConnection();
        CommandReader commandReader = new CommandReader();
        CommandProcessing commandProcessing = new CommandProcessing();
        commandProcessing.registerServerCommands();
        Answer answer;
        AnswerSending answerSending = new AnswerSending();
        ServerCommandValidation serverCommandValidation = new ServerCommandValidation();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            MyLogger.info("Экстренное завершнение работы, сохраняю коллекцию");
            commandProcessing.commandExecution.save(commandProcessing.getCollection());
        }));
            while (true) {
                try {
                serverConnection.connect(8800);
                commandReader.sendToClient(CommandsTranform.transformArray(commandProcessing.getServerCommands()),serverConnection.getSocketChannel());
                serverConnection.getSelector().select();
                commandReader.getCommandFromClient(serverConnection.getSocketChannel());
                try {
                    serverCommandValidation.ValidateCommand(commandReader.getCoreCommand(),CommandsTranform.transformArray(commandProcessing.getServerCommands()));
                    answer = commandProcessing.processCommand(commandReader.getCoreCommand());
                    commandReader.sendToClient(answer,serverConnection.getSocketChannel());
                }catch (ServerValidateException e){
                    commandReader.sendToClient(new Answer("Команда не прошла серверную валидацию"),serverConnection.getSocketChannel());
                }

          //      answerSending.SendAnswer(commandReader,answer,serverConnection.getSocketChannel());
                serverConnection.disconnect();
                }catch (ConnectException | IOException e) {
                    e.printStackTrace();
                    System.out.println("Ожидается команда от клиента.");
                    serverConnection.disconnect();
                }
            }



    }

}
