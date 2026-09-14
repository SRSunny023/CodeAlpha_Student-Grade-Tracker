import ui.Splash_Screen;

public class Welcome {

    Welcome() {
        new Splash_Screen();
    }

    public static void main(String[] args) {
        global.Inactivity_Manager.startWatching();
        new Welcome();
    }

}