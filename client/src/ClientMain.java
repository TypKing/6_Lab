import CoreSource.Answer;
import CoreSource.CoreCommand;
import CoreSource.Factory;
import CoreSource.Worker;
import Exceptions.ConnectException;
import Exceptions.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Мой логер");
        Factory factory = new Factory();
        CommandValidation commandValidation = new CommandValidation();
        Scanner scanner = new Scanner(System.in);
        try {
            ConnectManager connectManager = new ConnectManager(8800,InetAddress.getLocalHost());
            String command = "";
            while (!command.trim().toLowerCase().equals("exit")){
                try {
                    System.out.println("Введите команду");
                    command = scanner.nextLine();
                    connectManager.connect(commandValidation);
                    CoreCommand coreCommand = commandValidation.createCommand(command);
                    connectManager.sendCommand(coreCommand);
                    Answer answer = connectManager.getAnswerFromServer();
                    if(answer!=null) {
                        answer.sort();
                    }
                    System.out.println(answer);

                }catch (ValidateException e){
                    System.out.println("Команда не прошла валидацию!");
                }finally {
                    connectManager.disconnect();
                }

            }
        }catch (ConnectException e){

        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Говно давай сначала");
        }
    }
}
