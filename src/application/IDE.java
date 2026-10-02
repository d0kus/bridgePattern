package application;

public class IDE extends Application{
    public IDE(String name) {
        super(name);
    }

    @Override
    public void openApp() {
        this.os.openWindow(name);
        this.os.showInfo();
        System.out.println();
    }
    

}