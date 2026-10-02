package application;

import operatingsystem.OperatingSystem;

import java.util.Objects;

public class WebBrowser extends Application{
    public WebBrowser(String name, OperatingSystem os) {
        super(name, os);
    }

    @Override
    public void openApp() {
        this.os.openWindow(name);
        System.out.println("Opening default home page: https://google.com");
        this.os.showInfo();
        System.out.println();
    }

    public void browse(String url){
        if (url.strip().toLowerCase().startsWith("https://")){
            this.os.openWindow(name);
            System.out.println("Readdressing to "+ url + "...");
            System.out.println();
        }
        else throw new IllegalArgumentException("Invalid url address");
    }
}
