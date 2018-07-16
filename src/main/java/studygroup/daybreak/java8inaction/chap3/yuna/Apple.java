package studygroup.daybreak.java8inaction.chap3.yuna;

public class Apple {

    public Apple(){
        super();
    }

    public Apple(int weignt, String color) {
        this.weight = weignt;
        this.color = color;
    }

    private int weight;

    private String color;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
