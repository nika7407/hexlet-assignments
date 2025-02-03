package exercise;
import java.lang.Math;
// BEGIN
public class Circle {
    private final Point point;
    private final Integer radius;

    public Circle(Point point, Integer radius) {
        this.point = point;
        this.radius = radius;
    }

    public Integer getRadius() {
        return radius;
    }

    public Double getSquare() throws NegativeRadiusException {
     Double answer = Math.PI*(radius*radius);
     if (answer<0 || radius <0){
         throw new NegativeRadiusException("");
     } else {
         return answer;
     }
    }
}
// END
