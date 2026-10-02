package operatingsystem;

public class MacOS implements OperatingSystem {
    @Override
    public void openWindow(String title){
        System.out.println("Opening window with MacOS interface: " + title);
    }

    @Override
    public void showInfo(){
        System.out.println("Operating system: MacOS\nVersion: \"27 Golden Gate\"" );
    }

}