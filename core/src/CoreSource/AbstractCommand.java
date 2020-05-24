package CoreSource;

public interface AbstractCommand{
    boolean isNeedArg();
    boolean isNeedWorker();
    String getTypeOfArg();
    String getName();
}
