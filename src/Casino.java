import java.io.*;
import java.util.ArrayList;

public class Casino {
    Player player;
    public Casino(Player player) {
        this.player = player;
    }
    //Updates the .txt leaderboard to store scores a
    public void updateHistory() throws IOException
    {
        FileReader fr = new FileReader("history.txt");
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> str = new ArrayList<String>();
        String line = br.readLine();
                while (line != null)
                {
                    if(line.contains(player.getName()))
                    {
                        str.add(player.getName() + ":" + player.getCash());
                    }
                    else {
                        str.add(line);
                    }
                    line = br.readLine();
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
    public Player getPlayer() {
        return player;
    }
}
