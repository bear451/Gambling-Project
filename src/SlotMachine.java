import java.io.IOException;
import java.util.Random;

public class SlotMachine extends Casino{
    public SlotMachine(Player player)
    {
        super(player);
    }
    public int spin(int amount)
    {
        Random rand = new Random();
        int spin = rand.nextInt(1000000) + 1;
        if(spin < 750000)
        {
            return amount * -1;
        }
        else if(spin < 950000)
        {
            return amount * 2;
        }
        else if(spin < 999999)
        {
            return amount * 3;
        }
        else if(spin < 1000000)
        {
            return amount * 10;
        }
        return 0;
    }
    //Slots play
    public void play(int amount)
    {
        System.out.println("It's time to spin!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int returnvalue = spin(amount);
        if(returnvalue <= 0)
        {
            System.out.println("You lose " + returnvalue);
        }
        else
        {
            System.out.println("You gained " + returnvalue);
        }
        getPlayer().addCash(returnvalue);
        try {
            updateHistory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
