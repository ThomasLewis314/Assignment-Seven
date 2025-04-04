import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;


class Main
{
    static boolean systemStart = false;
    public static int[] getRandomArray(int size)
    {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; ++i)
        {
            int num = rand.nextInt(101);
            array[i] = num;
        }
        return array;
    }

    public static void commandLine()
    {
        if (systemStart == false)
        {
            System.out.print("Enter your command here (or help for assistance) : ");
            systemStart = true;
        }
        else
        {
            System.out.print("Enter your command here : ");
        }
        
        Scanner scnr = new Scanner(System.in);
        String command = scnr.nextLine();
        command = command.toUpperCase();
        command = command.trim();

        
        switch (command) 
        {
            case ("HELP") : 
            {
                System.out.println("\nEnter an integer to create an random array of that size\n");
                System.out.println("Write an integer array and filename to write the given array to the file");
                System.out.println("Integer array in the format \"a,b,c,d,e\", filename as a string\n");
                System.out.println("Enter a string to attempt to read an array from that file\n");
                System.out.println("Enter the command \"end\" to close the program\n");
                commandLine();
            }
            case ("END") : 
            {
                System.out.println("Thanks for using my program");
                System.exit(0);
            }
            default : 
            {
                String[] inputArray = command.split("\u0020");
                if (inputArray.length > 1)
                {
                    char[] elementOne = inputArray[0].toCharArray();
                    System.out.println((elementOne.length / 2) + 1);
                    int[] numArray = new int[(elementOne.length / 2) + 1];
                    int i = 0; 
                    for (char c : elementOne)
                    {
                        try 
                        {
                            numArray[i] = Character.getNumericValue(c);
                            if (numArray[i] == -1)
                            {
                                numArray[i] = 0;
                                continue;
                            }
                            else if (numArray[i] > 9)
                            {
                                System.out.println("Invalid input at index " + i);
                                break;
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            continue;
                        }
                        ++i;
                    }
                    try 
                    {
                        FileWriter filewriter = new FileWriter(inputArray[1] + ".txt");
                        filewriter.write(inputArray[0]);
                        filewriter.close();
                    }
                    catch (IOException e)
                    {
                        System.out.println("Failure to write into file");
                    }
                }
                else
                {
                    try 
                    {
                        int numberInput = Integer.parseInt(command);
                        int[] randomArray = getRandomArray(numberInput);
                        for (int i = 0; i < numberInput; ++i)
                        {
                            System.out.print(randomArray[i] + " ");
                        }
                        System.out.println("\n");
                    }
                    catch (NumberFormatException e)
                    {
                        char[] commandArray = command.toCharArray();
                        String firstCharacter = String.valueOf(commandArray[0]);
                        try 
                        {
                            int firstNumber = Integer.parseInt(firstCharacter);
                            
                        }
                        catch (NumberFormatException exc)
                        {
                            File file = new File(command + ".txt");
                            if (file.exists())
                            {
                                    try
                                    {
                                        FileReader reader = new FileReader(command + ".txt");
                                        int character;
                                        while ((character = reader.read()) != -1)
                                        {
                                            System.out.print((char) character);
                                        }
                                    }
                                    catch (IOException excep)
                                    {

                                    }
                                }
                                else
                                {
                                    System.out.println("File does not exist");
                                }
                            }
                        }
                    }
                }
                commandLine();
            }
        }
    }

    public static void main(String[] args)
    {
        commandLine();
    }
}

/* ------------  Recap
** I have coded into existance the following
**  1). Random int array generation, plus method of input for that (Requirement One)
**  2). I have a system that detects and reads the int array for requirement two, still need to write that into
        a file, thats next on the list
    
   ------------  Need to Do
    1). Try to compile to see what needs fixing.
    2). Code the file reading requirement using a single string as input
    3). Code the sorting mechanism for solo int array inputs
    */