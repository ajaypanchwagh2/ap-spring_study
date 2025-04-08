package Com.example.Testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Calculate {




    public void checkdup()
    {

        Map<Integer, Integer> input = new HashMap();

        input.put(1,1);
        input.put(1,2);

        System.out.println(input);



    }





}












/* public int Add(int a, int b)
    {
        return a+b;
    }

    public  boolean IsStrigPal(String str)
    {

        int i=0,j=str.length()-1;
        boolean isPal=false;
        while(i < str.length()/2)
        {

            if(str.charAt(i) == str.charAt(j))
            {
                i++;
                j--;

                isPal=true;

            }
            else
            {
                isPal=false;
                break;
            }

        }
        return isPal;


    }*/