package operatingsystem;

public class Windows implements OperatingSystem {
    @Override
    public void openWindow(String title) {
        System.out.println("Opening window with Windows interface: " + title);
    }

    @Override
    public void showInfo() {
        System.out.println("Operating system: Windows\nVersion: \"Windows 11\"");
    }
}