package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor){
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public double getArea(){
        return area + balconyArea;
    }


    public int compareTo(Home home){
       return Double.compare(this.getArea(), home.getArea());
    }

    @Override
    public String toString(){
        return "Квартира площадью " + this.getArea() + " метров на " + floor + " этаже";
    }
    // "Квартира площадью 58.5 метров на 3 этаже"


}
// END
