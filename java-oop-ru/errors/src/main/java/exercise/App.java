package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle){

        try {
         Double square = circle.getSquare();
         System.out.println(Math.round(square));
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь");
        }
        System.out.println("Вычисление окончено");

    }
}
// END
