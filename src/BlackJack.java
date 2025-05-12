import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackJack extends Casino {
    ArrayList<String> player = new ArrayList<>();
    ArrayList<String> computer = new ArrayList<>();
    public  BlackJack(Player player) {
        super(player);
    }
    public void printStartingCards(String x, String y)
    {
        System.out.println("-------------" +"       "+"-------------");
        System.out.println("|" + x +"         "+ x + "|" +"       "+"|" + y +"         "+ y + "|");
        System.out.println("|           |"+"       "+"|           |");
        System.out.println("|           |"+"       "+"|           |");
        System.out.println("|     " +x+"     |"+"       "+"|     " +y+"     |");
        System.out.println("|           |"+"       "+"|           |");
        System.out.println("|           |"+"       "+"|           |");
        System.out.println("|" + x +"         "+ x + "|"+"       "+"|" + y +"         "+ y + "|");
        System.out.println("-------------"+"       "+"-------------");
    }
    public void hit(String value)
    {
        for(int i = 0; i <= 1; i++) {
            System.out.println();
        }
        System.out.println("-------------");
        System.out.println("|" + value +"         "+ value + "|");
        System.out.println("|           |");
        System.out.println("|           |");
        System.out.println("|     " +value+"     |");
        System.out.println("|           |");
        System.out.println("|           |");
        System.out.println("|" + value +"         "+ value + "|");
        System.out.println("-------------");
    }
    public void printCards(boolean comporplayer)
    {
        //TRUE IS PLAYER
        printStartingCards(computer.get(0)," ");
        if(!comporplayer)
        {
            for(int i = 2; i < computer.size(); i++) {
                hit(computer.get(i));
            }
        }
        for(int i = 0; i <= 4; i++) {
            System.out.println();
        }
        printStartingCards(player.get(0),player.get(1));
        if(comporplayer)
        {
            for(int i = 2; i < player.size(); i++) {
                hit(player.get(i));
            }
        }
    }
    public void printCards(boolean comporplayer, String y)
    {
        //TRUE IS PLAYER
        printStartingCards(computer.get(0),y);
        if(!comporplayer)
        {
            for(int i = 2; i < computer.size(); i++) {
                hit(computer.get(i));
            }
        }
        for(int i = 0; i <= 4; i++) {
            System.out.println();
        }
        printStartingCards(player.get(0),player.get(1));
        if(comporplayer)
        {
            for(int i = 2; i < player.size(); i++) {
                hit(player.get(i));
            }
        }
    }
    public String assignCard(int value)
    {
        if(value == 1)
        {
            return "A";
        }
        else if(value > 1 && value < 10)
        {
            return "" + value;
        }
        else if(value == 10)
        {
            return "X";
        }
        else if(value == 11)
        {
            return "J";
        }
        else if(value == 12)
        {
            return "Q";
        }
        else if(value == 13)
        {
            return "K";
        }
        return "ERROR";
    }
    public int value(ArrayList<String> side)
    {
        int sum = 0;
        for(String str : side) {
            if(str.equalsIgnoreCase("k"))
            {
                sum += 10;
            }
            else if(str.equalsIgnoreCase("q")){
                sum += 10;
            }
            else if(str.equalsIgnoreCase("j")){
                sum += 10;
            }
            else if(str.equalsIgnoreCase("x")){
                sum += 10;
            }
            else if(str.equalsIgnoreCase("a")){
                sum += 1;
            }
            else{
                sum+=Integer.parseInt(str);
            }
        }
        return sum;
    }
    public boolean bust(ArrayList<String> side)
    {
        int sum = value(side);
        return !(sum <= 21);
    }

    public void play(int amount)
    {
        if(getPlayer().getCash() > 0) {
            //GENERATE CARDS
            Random rand = new Random();
            int compCard1Value = rand.nextInt(13) + 1;
            int compCard2Value = rand.nextInt(13) + 1;
            int playerCard1Value = rand.nextInt(13) + 1;
            int playerCard2Value = rand.nextInt(13) + 1;
            String compCard1 = assignCard(compCard1Value);
            String compCard2 = assignCard(compCard2Value);
            String playerCard1 = assignCard(playerCard1Value);
            String playerCard2 = assignCard(playerCard2Value);
            printStartingCards(compCard1, " ");
            for (int i = 0; i <= 4; i++) {
                System.out.println();
            }
            printStartingCards(playerCard1, playerCard2);
            player.add(playerCard1);
            player.add(playerCard2);
            computer.add(compCard1);
            computer.add(compCard2);
            boolean go = true;
            //GAME LOOP
            while (go) {
                Scanner input = new Scanner(System.in);
                //TRUE == Player, FALSE == Computer
                String hit = "";
                //HIT OR STAND LOOP
                while (!(hit.equalsIgnoreCase("s"))) {
                    System.out.println("Would you like to hit or stand? (H/S)");
                    hit = input.nextLine();
                    if (hit.equalsIgnoreCase("h")) {
                        int card = rand.nextInt(13) + 1;
                        String name = assignCard(card);
                        player.add(name);
                    }
                    printCards(true);
                    if (bust(player)) {
                        System.out.println("Oops! That's a bust. . . You lose");
                        go = false;
                        hit = "s";
                    }
                }
                System.out.println();
                System.out.println();
                System.out.println("Revealing Cards");
                printCards(true, computer.get(1));
                boolean computerHits = true;
                while (computerHits) {
                    if (bust(computer)) {
                        System.out.println("Computer Busted! You win!");
                        System.out.println("You gain " + amount + " dollars");
                        getPlayer().addCash(amount);
                        try {
                            updateHistory();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        computerHits = false;
                        go = false;
                    } else if (value(computer) >= 18 && value(computer) <= 21) {
                        if (value(computer) == value(player)) {
                            System.out.println("Push! It's a Draw!");
                            computerHits = false;
                            go = false;
                        } else if (value(computer) > value(player)) {
                            System.out.println("Computer wins :(");
                            System.out.println("You lose " + amount + " dollars");
                            getPlayer().addCash(amount * -1);
                            try {
                                updateHistory();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            go = false;
                            computerHits = false;
                        } else {
                            System.out.println("You Win!");
                            System.out.println("You gain " + amount + " dollars");
                            getPlayer().addCash(amount);
                            try {
                                updateHistory();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            computerHits = false;
                            go = false;
                        }
                    } else {
                        System.out.println("Computer Hits");
                        int card = rand.nextInt(13) + 1;
                        String name = assignCard(card);
                        computer.add(name);
                        printCards(false, computer.get(1));
                    }
                }
            }
        }
        else{
            System.out.println("You can't play because you are broke :(");
        }
    }
}
