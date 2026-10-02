package application;

import operatingsystem.OperatingSystem;

public abstract class Application {
    protected String name;
    protected OperatingSystem os;

    public Application(String name){
        this.name = name;
    }
    public void setOs(OperatingSystem os){
        this.os = os;
    }
    public abstract void openApp();
}
