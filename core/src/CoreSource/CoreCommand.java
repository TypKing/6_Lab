package CoreSource;

import java.io.Serializable;

public class CoreCommand implements Serializable {
    private Worker worker;
    private String arg;
    private String name;
    private String description;
    private boolean needArg = false;
    private boolean needWorker = false;
    private String typeOfArg;

    public CoreCommand(String name, String arg, Worker worker){
        this.name =name;
        this.arg = arg;
        this.worker = worker;
    }
    public CoreCommand(String name){
        this.name =name;
    }
    public CoreCommand(String name, String arg){
        this.name =name;
        this.arg = arg;
    }
    public CoreCommand(String name, Worker worker){
        this.name =name;
        this.worker = worker;
    }
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNeedArg() {
        return needArg;
    }

    public void setNeedArg(boolean needArg) {
        this.needArg = needArg;
    }

    public boolean isNeedWorker() {
        return needWorker;
    }

    public void setNeedWorker(boolean needWorker) {
        this.needWorker = needWorker;
    }

    public String getTypeOfArg() {
        return typeOfArg;
    }

    @Override
    public String toString() {
        return "CoreCommand{" +
                "worker=" + worker +
                ", arg='" + arg + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", needArg=" + needArg +
                ", needWorker=" + needWorker +
                ", typeOfArg='" + typeOfArg + '\'' +
                '}';
    }

    public void setTypeOfArg(String typeOfArg) {
        this.typeOfArg = typeOfArg;
    }
}
