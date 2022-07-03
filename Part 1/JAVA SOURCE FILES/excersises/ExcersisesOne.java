package excersises;
import java.util.*;

public class ExcersisesOne 
{
    public static void One()
    {
        try(Scanner scanner = new Scanner(System.in))
        {
            scanner.useLocale(Locale.US);
            System.out.println("Please write temperature in Celsius to convert to Farenheit: ");
            double Celsius = scanner.nextDouble();
            System.out.printf("Temperature converted to Farenheit is: % .2f", Celsius*1.8+32);
        }
    }

    public static void Two()
    {
        List<Integer> numbers = new ArrayList<>();
        try(Scanner scanner = new Scanner(System.in))
        {
            for(int x=0;x<3;x++)
            {
                System.out.println("Please insert "+(x+1)+" number: ");
                numbers.add(scanner.nextInt());
            }
        }

        int biggest=Collections.max(numbers);
        System.out.println("Biggest numbers of 3 inserted is: "+biggest);
    }

    public static void SimpleCalculator()
    {
        System.out.println("This is simplest calculator that i could program");

        double num1=0, num2=0;
        char choice=0;

        try(Scanner sc = new Scanner(System.in))
        {       
            System.out.println("Please enter first number: ");
            num1 = sc.nextInt();
            System.out.println("Please enter second number: ");
            num2 = sc.nextInt();

            System.out.println("Please enter what do you want to do with these numbers:\n + Add\n- Subtract\n * Multiply\n / Divide\nChoose: ");
            choice = sc.next().charAt(0);
        }

        switch(choice)
        {
            case '+':
                System.out.println(num1 + "+" + num2 +"="+ (num1+num2));
                break;
            case '-':
                System.out.println(num1 + "-" + num2 +"="+ (num1-num2));
                break;
            case '*':
                System.out.println(num1 + "*" + num2 +"="+ (num1*num2));
                break;
            case '/':
                System.out.println(num1 + "/" + num2 +"="+ (num1/num2));
                break;
            default:
                System.out.println("Undefined symbol !");
                break;
        }

    }

    public static void TooLittleTooMuch()
    {
        Random rn = new Random(System.nanoTime());
        int generatedNumber = rn.ints(1,100).findFirst().getAsInt();

        int userNum=0;
        try(Scanner sc = new Scanner(System.in))
        {
            while(true)
            {
                System.out.println("Please insert nubmer: ");
                userNum = sc.nextInt();

                if(userNum>generatedNumber)
                    System.out.println("Inserted number is too big");
                else if(userNum<generatedNumber)
                    System.out.println("Inserted number is too little");
                else
                {
                    System.out.println("Congratulations you guessed the nubmer!");
                    break;
                }
            }
        }
    }

    public static void ArrayTest()
    {
        Integer[][] arr = new Integer[5][5];
        Random rn = new Random(System.nanoTime());

        for(int x=0;x<5;x++)
            for(int y=0;y<5;y++)
                arr[x][y]=rn.nextInt(5- (-5))-5;
        
        for(int x = 0;x<5;x++)
        {
            System.out.println("Max in col " +x +": "+Collections.max(Arrays.asList(arr[x])));
            System.out.println("Min in col " +x +": "+Collections.min(Arrays.asList(arr[x])));
        }
    }

    public static void InverseString()
    {
        try(Scanner sc = new Scanner(System.in))
        {
            System.out.println("Please give a string to reverse: ");

            String word = sc.nextLine();
            StringBuilder builder = new StringBuilder(word);

            String inversedWord = builder.reverse().toString();

            System.out.println("Inversed string: "+ inversedWord);
        }
    }

    public static void CaesarsCipher()
    {
        try(Scanner sc = new Scanner(System.in))
        {
            System.out.println("Please insert a string to encrypt");
            String str = sc.nextLine();
            StringBuffer result = new StringBuffer();
            System.out.println("Enter the shift number: ");
            Integer shift = sc.nextInt();

            for (char c : str.toCharArray()) 
            {
                if(c!=' ')
                {
                    int originalAlphabetPosition = c - 'a';
                    int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                    char newCharacter = (char) ('a' + newAlphabetPosition);
                    result.append(newCharacter);
                }
                else
                    result.append(c);
            }

            System.out.println("Encrypted string: "+ result.toString());
        }
    }

    //pamiętać żeby w setterach dawać wartości sklonowane czyli z .clone();
    //a i jak chcemy obiekt przez "wartość" przekazać do funckcji to też z .clone();
}
