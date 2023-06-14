package test.executorModle;

public class Immutability {

    public static void main(String[] args) {
        testStringV2();
    }


    public static void testString(){
        String s0= "kvill";

        String s1=new String("kvill");

        String s2=new String("kvill");

        System.out.println( s0==s1 );

        s1 = s1.intern();

        System.out.println( s0==s1 );

        s2.intern();
        System.out.println( s0==s2 );

        //在这个方法中我们定义了一个kvill的敞亮，所以常量池开始是有kvill的，所以s0和s1是指向不同的引用，通过s1.intern()之后，s1和s1.intern()指向了同一个引用地址
    }


    public static void testStringV2(){

        String s1=new String("kvill");

        String s2 = s1.intern();

        System.out.println(s1 == s1.intern());

        System.out.println(s1 + "" + s2);

        System.out.println(s2 == s1.intern());


        //在这个类中我们没有声名一个”kvill”常量，所以常量池中一开始是没有”kvill”的，当我们调用s1.intern()后就在常量池中新添加了一个”kvill”常量，原来的不在常量池中的”kvill”仍然存在，也就不是“将自己的地址注册到常量池中”了。

        //s1==s1.intern()为false说明原来的“kvill”仍然存在；

        //s2现在为常量池中“kvill”的地址，所以有s2==s1.intern()为true。
    }
}
