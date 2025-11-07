package text_case;

public interface DoInterface {
    void eat();
    String vois();
    String vois(String comand);
    int vois(String comand, String comand2);
    int vois(int comand, String comand2);
    int vois(String comand, int comand2);

    default String eat2() {
        return "Привет";
    }
}
