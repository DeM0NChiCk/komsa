package text_case;

public class Fish implements DoInterface{
    @Override
    public void eat() {

    }

    @Override
    public String vois() {
        return "OoOoOoOo";
    }

    public String vois(String name) {
        return "Карась: " + name;
    }

    @Override
    public int vois(String comand, String comand2) {
        return 3;
    }

    @Override
    public int vois(int comand, String comand2) {
        return 11;
    }

    @Override
    public int vois(String comand, int comand2) {
        return 0;
    }
}
