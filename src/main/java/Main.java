import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileOperation file = new FileOperation();
        ArrayList<String> betCodeList = file.readFile("betcode.txt");
        Handler handler = new Handler();
        ArrayList<SevenStar> sevenStars = handler.chaiFuShi(betCodeList);
        System.out.println(sevenStars.size() + " zhu, " + sevenStars.size() * 2 + " yuan");
        int totalMoney = handler.calculateMoney(sevenStars);
        System.out.println("totalMoney:" + totalMoney);
    }
}
