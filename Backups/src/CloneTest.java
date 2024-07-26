public class CloneTest implements Cloneable
{
    int x;
    Object y;
    public CloneTest(int x, Object y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public CloneTest clone(){
        CloneTest other = null;
        try{
            other = (CloneTest) super.clone();

        }catch(CloneNotSupportedException ignored){}
        return other;
    }
}

class CloneTest2 extends CloneTest{
    int z = 0;
    Object a;
    public CloneTest2(int x, Object y)
    {
        super(x, y);
    }
    public static void main(String[] args){
        CloneTest t1 = new CloneTest(1, new Object());
        CloneTest2 t2 = (CloneTest2) t1;
        System.out.println(t2);
        t1 = t2 != t1 ?  t2 : t1.clone();
    }
}
