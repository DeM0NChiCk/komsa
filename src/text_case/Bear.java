package text_case;

public class Bear<T> extends Animal implements NewDoInt<T>{

    private String name;

    public Bear(String name, String poroda, String age, String high) {
        super(poroda, age);
        super.rost = high;
        this.name = name;
    }

    @Override
    public String run() {
        return super.go();
    }

    @Override
    public void eat() {

    }

    @Override
    public String vois() {
        return "BYYYYYYYYYYYY";
    }

    @Override
    public String vois(String comand) {
        return "AAAAAAAAAAAAAA" + comand;
    }

    @Override
    public int vois(String comand, String comand2) {
        return 5;
    }

    @Override
    public int vois(int comand, String comand2) {
        return 2 + comand;
    }

    @Override
    public int vois(String comand, int comand2) {
        return comand2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
         this.name = name;
    }

    @Override
    public String go() {
        return "Я МЕДВЕДЬ";
    }

    @Override
    public T rune() {
        String a = "aaaaaaaaaa";
        return null;
    }

    @Override
    public void eat(T corm) {
        String a = "aaaaaaaaaa";
        System.out.println(corm + a);
    }
}
