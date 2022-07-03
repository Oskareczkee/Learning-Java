package debug.classes;

import java.lang.reflect.*;
import java.util.ArrayList;

/*
 * This class is not able to analyze all objects, especially ones from classes that have final static fields
 * Although it is capable of analizying simple classes
 * But some classes contain arrays that are propably not analyzable by this class and will throw an error
 */

public class ObjectAnalyzer 
{
    //test
    /*AccesibleObejct.setAccessible will throw an error
     * being unable to make field accessible
     * 
     public static void main(String... args)
     {
         ArrayList<Integer> squares = new ArrayList<>();
         for(int x=1; x<=5;x++)
         squares.add(x*x);
         
         System.out.println(new ObjectAnalyzer().toString(squares));       
        }
        */

    private ArrayList<Object>visited = new ArrayList<>();

    @SuppressWarnings("all")
    public String toString(Object obj)
    {
        if(obj==null) return "null";
        if(visited.contains(obj)) return "...";
        visited.add(obj);

        Class cl = obj.getClass();
        if(cl==String.class) return (String)obj;
        if(cl.isArray())
        {
            String r = cl.getComponentType() +"[]{";
            for(int i =0; i<Array.getLength(obj);i++){
                if(i>0)
                    r+=",";
                Object val = Array.get(obj, i);
                if(cl.getComponentType().isPrimitive())
                    r+=val;
                else
                    r+=toString(val);
            }

            return r+"}";
        }

        String r = cl.getName();
        do {
            r+="[";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);

            for (Field f : fields) {
                if(!Modifier.isStatic(f.getModifiers()))
                {
                    if(!r.endsWith("["))
                        r+=",";
                    r+=f.getName()+"=";

                    try {
                        Class t = f.getType();
                        Object val = f.get(obj);
                        if(t.isPrimitive())
                            r+=val;
                        else
                            r+=toString(val);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            r+="]";
            cl=cl.getSuperclass();
        } while (cl!=null);

        return r;
    }
}
