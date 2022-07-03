package debug.classes;

//import java.util.*;
import java.lang.reflect.*;

/**
 * This class does not have any exception throwing
 * when using it consider catching these exceptions especially ClassNotFoundException
 * It is not required but will prevent your program from random quitting
 */
public class ClassPrinter
{
    //test
    /*
     * 
     public static void main(String... args)
     {
         String name;
        if(args.length>0)
        name = args[0];
        else
        {
            try(Scanner sc = new Scanner(System.in))
            {
                System.out.println("Please type a class name to print (e.g java.util.Date)");
                name = sc.next();
            }
        }
        
        try {
            printClassInfo(Class.forName(name));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    */
    

    @SuppressWarnings("all")
    public static void printClass(Class cl)
    {
        Class supercl = cl.getSuperclass();

        String name = cl.getName();
        String modifiers = Modifier.toString(cl.getModifiers());
        if(modifiers.length()>0)
            System.out.print(modifiers +" ");
        System.out.print("class "+name);

        if(supercl!=null && supercl!=Object.class)
            System.out.print(" extends "+supercl.getName());
    }

    @SuppressWarnings("all")
    public static void printClassInfo(Class cl)
    {
        printClass(cl);
        System.out.print("\n{\n");
        printFields(cl);
        System.out.println("\n");
        printConstructors(cl);
        System.out.println();
        printMethods(cl);
        System.out.println("}");
    }

    @SuppressWarnings("all")
    public static void printConstructors(Class cl)
    {
        Constructor[] constructors = cl.getDeclaredConstructors();

        for(Constructor c: constructors)
        {
            String name = c.getName();
            System.out.print("   ");
            String modifiers  = Modifier.toString(c.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers + " ");
            System.out.print(name +"(");

            Class[] paramTypes = c.getParameterTypes();
            for(int j=0;j<paramTypes.length;j++)
            {
                if(j>0)
                    System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }

            System.out.print(")");

            Class[]exceptionTypes = c.getExceptionTypes();
            if(exceptionTypes.length>0)
            System.out.print(" throws ");
            for(int j=0;j<exceptionTypes.length;j++)
            {
                if(j>0)
                    System.out.print(", ");
                System.out.print(exceptionTypes[j].getName());
            }
            System.out.println(";");
        }
    }

    @SuppressWarnings("all")
    public static void printMethods(Class cl)
    {
        Method[] methods = cl.getDeclaredMethods();

        for(Method m : methods)
        {
            Class retType = m.getReturnType();
            String name = m.getName();

            System.out.print("   ");

            String modifiers = Modifier.toString(m.getModifiers());
            if(modifiers.length()>0)
                System.out.print(modifiers +" ");
            System.out.print(retType.getName()+" "+name+"(");

            Class[] paramTypes = m.getParameterTypes();
            for(int j=0;j<paramTypes.length;j++)
            {
                if(j>0)
                    System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.print(")");

            Class[]exceptionTypes = m.getExceptionTypes();
            if(exceptionTypes.length>0)
            System.out.print(" throws ");
            for(int j=0;j<exceptionTypes.length;j++)
            {
                if(j>0)
                    System.out.print(", ");
                System.out.print(exceptionTypes[j].getName());
            }
            System.out.println(";");

        }
    }

    @SuppressWarnings("all")
    public static void printFields(Class cl)
    {
        Field[] fields = cl.getDeclaredFields();

        for(Field f: fields)
        {
            Class type = f.getType();
            String name = f.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(f.getModifiers());

            if(modifiers.length()>0)
                System.out.print(modifiers +" ");
            System.out.println(type.getName()+" "+name+";");
        }
    }
}
