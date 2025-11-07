package text_case;

public abstract class Animal implements DoInterface{

    private String poroda;
    private String age;
    public String rost;

    public Animal(String poroda, String age, String rost) {
        this.poroda = poroda;
        this.age = age;
        this.rost = rost;
    }

    public Animal(String poroda, String age) {
        this.poroda = poroda;
        this.age = age;
    }

    public Animal() {
    }

    public abstract String run();

    public String go() {
        return "GOOOOOOOOOOO";
    }
}
