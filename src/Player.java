import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Player {
    private String name;
    private int cash;
    public Player(String name) {
        this.name = name;
        cash = 500;
        try {
            FileWriter fw = new FileWriter("history.txt", true);
            FileReader fr = new FileReader("history.txt");
            BufferedReader br = new BufferedReader(fr);
            if(br.readLine() == null) {
                fw.write(name + ":" + cash);
            }
            else{
                fw.write("\n" + name + ":" + cash);
            }
            fw.close();
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Player(String name, int cash) {
        this.name = name;
        this.cash = cash;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCash() {
        return cash;
    }
    public void setCash(int cash) {
        this.cash = cash;
    }
    public void addCash(int cash) {
        this.cash += cash;
    }
}
