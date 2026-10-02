package application;

import java.util.Objects;

public class WebBrowser extends Application{
    public WebBrowser(String name) {
        super(name);
    }

    @Override
    public void openApp() {
        this.os.openWindow(name);
        this.os.showInfo();
        System.out.println();
    }

    public void browse(String url){
        if (url.strip().toLowerCase().startsWith("https://")){
            this.os.openWindow(name);
            System.out.println("Readdressing to "+ url + "...");

        }
        else throw new IllegalArgumentException("Invalid url address");
    }
}
