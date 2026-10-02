package application;

import operatingsystem.OperatingSystem;

public class IDE extends Application{
    public IDE(String name, OperatingSystem os) {
        super(name, os);
    }

    @Override
    public void openApp() {
        this.os.openWindow(name);
        System.out.println("Indexing project workspace files...\n");
        this.os.showInfo();
        System.out.println();
    }

    public void createProject(String name){
        this.os.openWindow(this.name);
        System.out.println("Project: " + name + " created");
        System.out.println();
    }


}