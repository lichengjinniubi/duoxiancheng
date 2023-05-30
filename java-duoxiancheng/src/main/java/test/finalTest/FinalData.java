package test.finalTest;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;


public class FinalData {

    final static int aa = 1;

    int bb = 2;


    public static void setData(){

        final int cc = 1;
    }

}
