import java.util.ArrayList;

public class Handler {

    //各等奖奖金，单位分
    private static final int FIRST_PRIZE = 400000000;
    private static final int SECOND_PRIZE = 6537360;
    private static final int THIRD_PRIZE = 180000;
    private static final int FOURTH_PRIZE = 30000;
    private static final int FIFTH_PRIZE = 2000;
    private static final int SIXTH_PRIZE = 500;

    //内定中奖号码
    private static final int FIRST_WIN = 7;
    private static final int SECOND_WIN = 0;
    private static final int THIRD_WIN = 6;
    private static final int FOURTH_WIN = 7;
    private static final int FIFTH_WIN = 2;
    private static final int SIXTH_WIN = 7;
    private static final int SEVENTH_WIN = 4;
    /**
     * 把复式拆成每注7个号码的形式。
     * @param betCodeList 待拆单的betCode字符串列表
     * @return 最小拆单列表
     */
    ArrayList<SevenStar> chaiFuShi(ArrayList<String> betCodeList) {

        ArrayList<SevenStar> sevenStarList = new ArrayList<>();
        for (String betCodeStr : betCodeList) {
            //竖线分割不同的位
            String[] parts = betCodeStr.split("\\|");
            int[] firstPart =   Util.StringArrayToIntArray(parts[0].split(","));
            int[] secondPart =  Util.StringArrayToIntArray(parts[1].split(","));
            int[] thirdPart =   Util.StringArrayToIntArray(parts[2].split(","));
            int[] fourthPart =  Util.StringArrayToIntArray(parts[3].split(","));
            int[] fifthPart =   Util.StringArrayToIntArray(parts[4].split(","));
            int[] sixthPart =   Util.StringArrayToIntArray(parts[5].split(","));
            int[] seventhPart = Util.StringArrayToIntArray(parts[6].split(","));
            for (int first : firstPart) {
                for(int second : secondPart) {
                    for (int third : thirdPart) {
                        for (int fourth : fourthPart) {
                            for (int fifth : fifthPart) {
                                for (int sixth : sixthPart) {
                                    for (int seven : seventhPart) {
                                        SevenStar ss = new SevenStar();
                                        ss.setFirst(first);
                                        ss.setSecond(second);
                                        ss.setThird(third);
                                        ss.setFourth(fourth);
                                        ss.setFifth(fifth);
                                        ss.setSixth(sixth);
                                        ss.setSeventh(seven);
                                        sevenStarList.add(ss);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return sevenStarList;
    }

    /**
     * 计算总奖金
     * @param sevenStars 拆单后的betcode列表
     * @return 总奖金，单位分
     */
    int calculateMoney(ArrayList<SevenStar> sevenStars) {
        int totalMoney;
        int firstNum = 0;
        int secondNum = 0;
        int thirdNum = 0;
        int fourthNum = 0;
        int fifthNum = 0;
        int sixthNum = 0;

        for (SevenStar ss : sevenStars) {
            if (firstPrize(ss)) {
                firstNum++;
            } else if (secondPrize(ss)) {
                secondNum++;
            } else if (thirdPrize(ss)) {
                thirdNum++;
            } else if (fourthPrize(ss)) {
                fourthNum++;
            } else if (fifthPrize(ss)) {
                fifthNum++;
            } else if (sixthPrize(ss)) {
                sixthNum++;
            }

        }
        totalMoney = (firstNum * FIRST_PRIZE) + (secondNum * SECOND_PRIZE) + (thirdNum * THIRD_PRIZE) +
                (fourthNum * FOURTH_PRIZE) + (fifthNum * FIFTH_PRIZE) + (sixthNum * SIXTH_PRIZE);
        System.out.println("firstNum: " + firstNum);
        System.out.println("secondNum: " + secondNum);
        System.out.println("thirdNum: " + thirdNum);
        System.out.println("fourthNum; " + fourthNum);
        System.out.println("fifthNum: " + fifthNum);
        System.out.println("sixthNum: " + sixthNum);
        return totalMoney;
    }

    //一等奖：定位中7位码
    private boolean firstPrize(SevenStar ss) {
        return ss.getFirst() == FIRST_WIN && ss.getSecond() == SECOND_WIN && ss.getThird() == THIRD_WIN &&
                ss.getFourth() == FOURTH_WIN && ss.getFifth() == FIFTH_WIN && ss.getSixth() == SIXTH_WIN &&
                ss.getSeventh() == SEVENTH_WIN;
    }

    //二等奖：定位中连续6码
    private boolean secondPrize(SevenStar ss) {
        return (ss.getFirst() == FIRST_WIN && ss.getSecond() == SECOND_WIN && ss.getThird() == THIRD_WIN &&
                ss.getFourth() == FOURTH_WIN && ss.getFifth() == FIFTH_WIN && ss.getSixth() == SIXTH_WIN) ||
                (ss.getSecond() == SECOND_WIN && ss.getThird() == THIRD_WIN && ss.getFourth() == FOURTH_WIN &&
                        ss.getFifth() == FIFTH_WIN && ss.getSixth() == SIXTH_WIN && ss.getSeventh() == SEVENTH_WIN);
    }

    //三等奖：定位中连续5码12345XX, X23456X, XX34567
    private boolean thirdPrize(SevenStar ss) {
        return (ss.getFirst() == FIRST_WIN && ss.getSecond() == SECOND_WIN && ss.getThird() == THIRD_WIN &&
                ss.getFourth() == FOURTH_WIN && ss.getFifth() == FIFTH_WIN) ||
                (ss.getSecond() == SECOND_WIN && ss.getThird() == THIRD_WIN && ss.getFourth() == FOURTH_WIN &&
                        ss.getFifth() == FIFTH_WIN && ss.getSixth() == SIXTH_WIN) || (ss.getThird() == THIRD_WIN &&
                ss.getFourth() == FOURTH_WIN && ss.getFifth() == FIFTH_WIN && ss.getSixth() == SIXTH_WIN &&
                ss.getSeventh() == SEVENTH_WIN);
    }

    //四等奖：定位连中4码 1234XXX, X2345XX, XX3456X, XXX4567
    private boolean fourthPrize(SevenStar ss) {
        return (ss.getFirst() == FIRST_WIN && ss.getSecond() == SECOND_WIN && ss.getThird() == THIRD_WIN && ss.getFourth() == FOURTH_WIN) ||
                (ss.getSecond() == SECOND_WIN && ss.getThird() == THIRD_WIN && ss.getFourth() == FOURTH_WIN && ss.getFifth() == FIFTH_WIN) ||
                (ss.getThird() == THIRD_WIN && ss.getFourth() == FOURTH_WIN && ss.getFifth() == FIFTH_WIN && ss.getSixth() == SIXTH_WIN) ||
                (ss.getFourth() == FOURTH_WIN && ss.getFifth() == FIFTH_WIN && ss.getSixth() == SIXTH_WIN && ss.getSeventh() == SEVENTH_WIN);
    }

    //五等奖：定位中连续3码 123XXXX, X234XXX, XX345XX, XXX456X, XXXX567
    private boolean fifthPrize(SevenStar ss) {
        return (ss.getFirst() == FIRST_WIN && ss.getSecond() == SECOND_WIN && ss.getThird() == THIRD_WIN) ||
                (ss.getSecond() == SECOND_WIN && ss.getThird() == THIRD_WIN && ss.getFourth() == FOURTH_WIN) ||
                (ss.getThird() == THIRD_WIN && ss.getFourth() == FOURTH_WIN && ss.getFifth() == FIFTH_WIN) ||
                (ss.getFourth() == FOURTH_WIN && ss.getFifth() == FIFTH_WIN && ss.getSixth() == SIXTH_WIN) ||
                (ss.getFifth() == FIFTH_WIN && ss.getSixth() == SIXTH_WIN && ss.getSeventh() == SEVENTH_WIN);
    }

    //六等奖：定位中连续2码： 12XXXXX, X23XXXX, XX34XXX, XXX45XX, XXXX56X, XXXXX67
    private boolean sixthPrize(SevenStar ss) {
        return (ss.getFirst() == FIRST_WIN && ss.getSecond() == SECOND_WIN) ||
                (ss.getSecond() == SECOND_WIN && ss.getThird() == THIRD_WIN) ||
                (ss.getThird() == THIRD_WIN && ss.getFourth() == FOURTH_WIN) ||
                (ss.getFourth() == FOURTH_WIN && ss.getFifth() == FIFTH_WIN) ||
                (ss.getFifth() == FIFTH_WIN && ss.getSixth() == SIXTH_WIN) ||
                (ss.getSixth() == SIXTH_WIN && ss.getSeventh() == SEVENTH_WIN);
    }
}
