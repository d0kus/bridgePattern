import application.Application;
import application.IDE;
import application.WebBrowser;
import operatingsystem.Linux;
import operatingsystem.MacOS;
import operatingsystem.OperatingSystem;
import operatingsystem.Windows;

public class Main {
    public static void main(String[] args){
        OperatingSystem windows = new Windows();
        OperatingSystem linux = new Linux();
        OperatingSystem macos = new MacOS();

        WebBrowser chrome = new WebBrowser("Google Chrome", windows);
        chrome.openApp();
        chrome.browse("Https://youtube.com");

        chrome.setOs(linux);
        chrome.browse("https://github.com");

        IDE idea = new IDE("InteliJ IDEA", macos);
        idea.openApp();
        idea.createProject("bridgePattern");

        idea.setOs(linux);
        idea.createProject("Example");

    }
}
