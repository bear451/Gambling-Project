import java.io.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File history = new File("history.txt");
        //This creates a File called history to store a constant leaderboard of scores
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
        //Create or Load account of player
        while(a)
        {
            System.out.println("Do you already have an account? (Y/N)");
            String account = input.nextLine();
            if(account.equalsIgnoreCase("y"))
            {
                System.out.println("What was the name of your account?");
                String name = input.nextLine();


                try {
                    //Load their account
                    FileReader fr = new FileReader("History.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String line = br.readLine();
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
                //If they do not have an account, let them create one
                System.out.println("What do you want the name of your account to be?");
                String name = input.nextLine();
                player = new Player(name);
                a = false;
            }
            else{
                //Catch not valid inputs
                System.out.println("Invalid input");
            }

        }
        //Precondition-Player is not null
        BlackJack blackjack;
        boolean GAMELOOP = true;
        while(GAMELOOP)
        {
            //PICK A GAME
            if(player.getCash() > 0) {
                System.out.println("Would you like to play BlackJack or Slots? (B/S) (X=to exit)");
                String choice = input.nextLine();
                //HANDLES BLACKJACK
                if(choice.equalsIgnoreCase("B")) {
                    boolean blackjackloop = true;
                    while (blackjackloop) {
                        blackjack = new BlackJack(player);
                        System.out.println("How much do you want to bet? (INT)");
                        int bet = input.nextInt();
                        if (bet <= player.getCash()) {
                            blackjack.play(bet);
                            System.out.println("Your current balance is: " + player.getCash() + "$");
                            System.out.println("Would you like to play again? (Y/N)");
                            input.nextLine();
                            String keeponkeepingon = input.nextLine();
                            if (keeponkeepingon.equalsIgnoreCase("n")) {
                                blackjackloop = false;
                            }
                        } else {
                            System.out.println("That bet is more money than you have.");
                            System.out.println("You have: " + player.getCash() + "$");
                        }
                    }
                }
                //HANDLES SLOTS
                else if(choice.equalsIgnoreCase("S")) {
                    boolean SLOTLOOP = true;
                    while(SLOTLOOP) {
                        SlotMachine slot = new SlotMachine(player);
                        System.out.println("How much do you want to bet? (INT)");
                        int bet = input.nextInt();
                        if (bet <= player.getCash()) {
                            slot.play(bet);
                        } else {
                            System.out.println("That bet is more money than you have.");
                            System.out.println("You have: " + player.getCash() + "$");
                        }
                        System.out.println("Would you like to spin again? (Y/N)");
                        input.nextLine();
                        String spinagain = input.nextLine();
                        if(spinagain.equalsIgnoreCase("n")) {
                            SLOTLOOP = false;
                        }
                    }
                }
                else if(choice.equalsIgnoreCase("x"))
                {
                    GAMELOOP = false;
                }
                else{
                    System.out.println("That is not a valid response");
                }
            }
            else {
                GAMELOOP = false;
                System.out.println("I'm sorry, you're account is all out of cash");
            }

        }
        System.out.println("Thank you for playing!");
        //Display the leaderboard
        System.out.println("Here is the updated leaderboard:");
        try {
            FileReader fr = new FileReader("history.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while(line != null)
            {
                System.out.println(line);
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}