package debug.classes;

import java.lang.reflect.InvocationHandler;
import java.util.Random;
import java.lang.reflect.*;
import java.util.*;

public class TraceHandler implements InvocationHandler
{
    //test
    public static void main(String... args)
    {
        Object[] elements = new Object[1000];

        for(int i=0;i<elements.length;i++)
        {
            Integer val = i+1;
            InvocationHandler handler = new TraceHandler(val);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);

            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length)+1;
        int result = Arrays.binarySearch(elements, key);

        if(result>=0) System.out.println(elements[result]);
    }

    private Object target;

    public TraceHandler(Object o) { target = o; }
    
    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
    {
        System.out.print(target);
        System.out.print("."+ m.getName()+"(");

        if(args!=null)
        {
            for (int i=0; i<args.length;i++) 
            {
                System.out.print(args[i]);
                if(i<args.length-1)
                    System.out.print(", ");
            }
        }
        System.out.println(")");

        return m.invoke(target, args);
    }
}
