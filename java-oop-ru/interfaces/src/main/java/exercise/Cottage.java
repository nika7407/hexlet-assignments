package exercise;

// BEGIN
public class Cottage implements Home {

    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount){
        this.area = area;
        this.floorCount = floorCount;
    }


    public double getArea() {
        return area;
    }

    public int compareTo(Home homeToCompare) {
        return Double.compare(this.getArea(), homeToCompare.getArea());
    }

    @Override
    public String toString(){

       return floorCount + " этажный коттедж площадью " + area + " метров";
        // "2 этажный коттедж площадью 135 метров"
    }
}
// END
