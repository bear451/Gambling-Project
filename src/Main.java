import java.io.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File history = new File("history.txt");
        if (!history.exists()){
            try{
                //noinspection ResultOfMethodCallIgnored
                history.createNewFile();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        Scanner input = new Scanner(System.in);
        //Casino Intro
        System.out.println("WELCOME TO THE CASINO");
        //Handles the account
        boolean a = true;
        Player player = new Player("Default",500);
        while(a)
        {
            System.out.println("Do you already have an account? (Y/N)");
            String account = input.nextLine();
            if(account.equalsIgnoreCase("y"))
            {
                System.out.println("What was the name of your account?");
                String name = input.nextLine();


                try {
                    FileReader fr = new FileReader("History.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String line = br.readLine();
                    boolean found = false;
                    while(line != null)
                    {
                        if(line.toLowerCase().contains(name.toLowerCase()))
                        {
                            String s = line.substring(0, line.indexOf(":"));
                            int numInt = Integer.parseInt(line.substring(line.indexOf(":") + 1));
                            player = new Player(s, numInt);
                            line = br.readLine();
                            a = false;
                        }
                        else
                        {
                            line = br.readLine();
                        }
                    }

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(account.equalsIgnoreCase("N"))
            {
                System.out.println("What do you want the name of your account to be?");
                String name = input.nextLine();
                player = new Player(name);
                a = false;
            }
            else{
                System.out.println("Invalid input");
            }

        }
        //Precondition-Player is not null
        Casino casino = new Casino(player);
        BlackJack blackjack = new BlackJack(player);
        blackjack.play(30);
    }
}