import java.util.Random;

public class BlackJack extends Casino {
    public  BlackJack(Player player) {
        super(player);
    }
    public void printCard(String x)
    {
        System.out.println("-------------");
        System.out.println("|" + x +"         "+ x + "|");
        System.out.println("|           |");
        System.out.println("|           |");
        System.out.println("|     " +x+"     |");
        System.out.println("|           |");
        System.out.println("|           |");
        System.out.println("|" + x +"         "+ x + "|");
        System.out.println("-------------");
    }
    public void printCardGap()
    {
        System.out.println("  |  ");
        System.out.println("  |  ");
        System.out.println("  |  ");
        System.out.println("  |  ");
        System.out.println("  |  ");
        System.out.println("  |  ");
        System.out.println("  |  ");
        System.out.println("  |  ");
        System.out.println("  |  ");
    }
    public void play(int amount)
    {
        Random rand = new Random();
        int compCard1 = rand.nextInt(10) + 1;
        int compCard2 = rand.nextInt(10) + 1;
        int playerCard1 = rand.nextInt(10) + 1;
        int playerCard2 = rand.nextInt(10) + 1;

    }
}
