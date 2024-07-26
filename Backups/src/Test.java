import java.util.ArrayList;
import java.util.HashSet;

public class Test implements Cloneable {
    int test;
    static final double PI = 3.14159265359;
    protected Test(){
        var a = Math.PI;
    }
}
class Test2 extends Test{
    public Test2(){
        super.test = 5;
        var a = Math.PI;
    }
}



class TestUsage{
    public static void main(String[] args) {
        System.out.println(Test.PI);
        Test test = new Test();
        System.out.println(test.PI);
        ArrayList a = new ArrayList();
        a.add("abc");
        a.add(test);
        for (int i = 0; i < a.size(); i++) {
            //System.out.println((String) a.get(i));
        }
        HashSet<Test> set = new HashSet<>();
        set.add(test);  

    }
}