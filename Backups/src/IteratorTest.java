import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class IteratorTest {
    private enum Colors{RED, GREEN, BLUE}
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1,2,3,4,5));
        int factor = 3;
        int currentIndex = 0;
        while (currentIndex < numbers.size() * factor) {
            //System.out.println(currentIndex % numbers.size());
            System.out.println(numbers.get(currentIndex / factor));
            currentIndex++;

        }
        Double number = 7.0;
        ArrayList<TreeTest> list = new ArrayList<>(List.of(new TreeTest(), new TreeTest()));
        TreeSet<TreeTest> tree = new TreeSet<>(list);
    }
}
class TreeTest implements Comparable<TreeTest>{
    @Override
    public int compareTo(TreeTest o) {
        return 0;
    }

}
