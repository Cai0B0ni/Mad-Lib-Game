//Allows the user to either create or a view a short mad lib story unless otherwise 
// specified. If user chooses to create it will compile an already made text file
// along with user inputs to then create a newly unique story. If user chooses to 
// view it will print and already exisitng file so the user can see.

import java.util.*;
import java.io.*;

public class MadLibs{
   
   public static void main(String[] args)throws FileNotFoundException{
      Scanner console = new Scanner(System.in);
      boolean newMadLib = true;
      
      intro();
      
      while(newMadLib){
         System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
         String userChoice = console.nextLine();
         
         if(userChoice.equalsIgnoreCase("c")){
            createFile(console);
         }else if(userChoice.equalsIgnoreCase("v")){
            viewFile(console);
         }else if(userChoice.equalsIgnoreCase("q")){
            newMadLib = false;
         }
      }
   }
   
   //Prints out the introduction.
   public static void intro(){
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");
      System.out.println();
   }
   
   //Reads through input file and asks user what he/she wished to replace each 
   // placeholder word with new words. While replacing the placeholding words it 
   // also copies down all other non-replaced words into an entire new .txt file
   // and saves it, so it can then be seen at any desired time. 
   //Parameters used:
   // <Scanner console> = in order to read users input
   public static void createFile(Scanner console)throws FileNotFoundException{
      Scanner input = inputFile(console);
      
      System.out.print("Output file name: ");
      File outputFileName = new File(console.nextLine());
      System.out.println();
      
      PrintStream outputFile = new PrintStream(outputFileName);
      String word = "";
      
      while(input.hasNextLine()){
         String line = input.nextLine();
         Scanner token = new Scanner(line);
         
         while(token.hasNext()){
            word = token.next();
            
            if(word.startsWith("<") && word.endsWith(">")){
               word = word.replace("<", "").replace(">", "").replace("-"," ");
               char beginChar = word.toLowerCase().charAt(0);
               
               if(beginChar == 'a' || beginChar == 'e' || beginChar == 'i'
                  || beginChar == 'o' || beginChar == 'u'){
                  System.out.print("Please type an " + word + ": ");
               }else{
                  System.out.print("Please type a " + word + ": ");
               }
               word = console.nextLine();
            }
            outputFile.print(word + " ");
         }
         outputFile.println();
      }
      System.out.println("Your mad-lib has been created!");
      System.out.println();
   }
   
   //Reads through input file and outputs the file word for word as seen on .txt file
   // so user is able to view file.
   //Parameters used:
   // <Scanner console> = in order to read users input
   public static void viewFile(Scanner console) throws FileNotFoundException{
      Scanner input = inputFile(console);
      System.out.println();
      
      while(input.hasNextLine()) {
         System.out.println(input.nextLine());
      }
      System.out.println();
   }
   
   //Prompts user for a desired file unitl user types in an existing file name.
   //Returns input file user wishes to work with.
   //Parameters used:
   // <Scanner console> = in order to read users input
   public static Scanner inputFile(Scanner console)throws FileNotFoundException{
      System.out.print("Input file name: ");
      String fileName = console.nextLine();
      File file = new File(fileName);
      
      while(!file.exists()){
         System.out.print("File not found. Try again: ");
         fileName = console.nextLine();
         file = new File(fileName);
      }
      return new Scanner(file);
   }
}