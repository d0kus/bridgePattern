package operatingsystem;

public class Linux implements OperatingSystem {
    @Override
    public void openWindow(String title){
        System.out.println("Opening window with Linux interface: " + title);
    }

    @Override
    public void showInfo(){
        System.out.println("Operating system: Linux\nVersion: \"Ubuntu 26.04\"");
    }

}
