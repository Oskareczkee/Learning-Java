import excersises.*;


//I setted this as a starting point of jar file, but you rather propably want to start from other main methods, every package should have one that will work
//At least i hope so
public class App {
    public static void main(String[] args) throws Exception {
        try 
        {
            //ExcersisesOne.One();
            //ExcersisesOne.Two();
            //ExcersisesOne.SimpleCalculator();
            //ExcersisesOne.TooLittleTooMuch();
            //ExcersisesOne.ArrayTest();
            //ExcersisesOne.InverseString();
            ExcersisesOne.CaesarsCipher();
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
}
