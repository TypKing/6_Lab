package Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {
    private static Logger logger = LoggerFactory.getLogger("ServerLogging by TypicalKing");

    public static Logger getLogger(){
        return logger;
    }
    public static void info(String s){
        logger.info(s);
    }
    public static void error(String s){
        logger.error(s);
    }
}