public class Util {

    /**
     * 字符串数组转
     * @param strings
     * @return
     */
    public static int[] StringArrayToIntArray(String[] strings){
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }
}
