package application;

import operatingsystem.OperatingSystem;

public abstract class Application {
    protected String name;
    protected OperatingSystem os;

    public Application(String name, OperatingSystem os){
        this.name = name;
        this.os = os;
    }
    public void setOs(OperatingSystem os){
        this.os = os;
    }
    public abstract void openApp();
}
