import java.io.*;
import java.util.ArrayList;

public class Casino {
    Player player;
    public Casino(Player player) {
        this.player = player;
    }
    public void updateHistory() throws IOException
    {
        FileReader fr = new FileReader("history.txt");
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> str = new ArrayList<String>();
                while (br.readLine() != null)
                {
                    if(br.readLine().equals(player.getName()))
                    {
                        str.add(player.getName() + ":" + player.getCash());
                    }
                    else {
                        str.add(br.readLine());
                    }
                }
                FileWriter fw = new FileWriter("history.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < str.size(); i++)
                {
                    bw.write(str.get(i));
                }
                bw.close();
                fw.close();

    }
}
