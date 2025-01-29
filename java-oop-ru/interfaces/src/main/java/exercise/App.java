package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> list, int size){

         List<String> sortedList = list.stream()
                .sorted(Comparator.comparingDouble(home -> home.getArea()))
                 .map(h -> h.toString())
                 .limit(size)
                .collect(Collectors.toList());
         return sortedList;
}

public static void main(){
       String string = "meow";
        System.out.println(string.toString());
}
}
// END
