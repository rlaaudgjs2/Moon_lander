package moon_lander;

import javax.swing.*;
import java.io.*;

public class BestRanking {
    private static BestRanking bestRanking = null;
    private static String userRankFilePath = System.getProperty("user.dir") + "moon_lander_bestranking";

    BestRanking() {
        if (!isUserRankFileExists()) {
            File file = new File(userRankFilePath);

            try {
                if (file.createNewFile()) {
                    System.out.println("----- User Rank File Create -----");
                    System.out.println(userRankFilePath);
                }

            } catch (IOException e) {
                System.out.println("----- User Rank File Create ERROR! -----");
            }

        } else {
            System.out.println(userRankFilePath);
        }
    }

    public static BestRanking getInstance() {
        if (bestRanking == null) {
            bestRanking = new BestRanking();
        }

        return bestRanking;
    }

    public boolean isUserRankFileExists() {
        File file = new File(userRankFilePath);

        return file.exists();
    }

    public void ShowRanking() {

            JOptionPane.showMessageDialog(null, "Best Ranking score is : " + GetBestRanking());


    }

    public void SetRenewRanking(int score) {
        try {
//            Integer.parseInt(score)
            if (score < GetBestRanking()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(userRankFilePath));

                writer.write(score);
                writer.flush();
                writer.close();
            }

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public int GetBestRanking() {
        int returnRank = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(userRankFilePath));

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                returnRank = Integer.parseInt(line);
            }

        } catch (NumberFormatException | IOException e) {
            return returnRank;
        }

        return returnRank;
    }

}