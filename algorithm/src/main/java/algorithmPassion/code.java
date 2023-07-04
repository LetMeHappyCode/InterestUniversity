package algorithmPassion;

public class code {
    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     */

    public static void main(String[] args) {

    }

    public String longestPalindrome(String s) {
        //暴力,从第一个开始，每次都向两边扩张，两边都不一样停止并统计长度。
        if (s.length() == 1){
            return s;
        }
        Integer start = 0;
        Integer end = 0;
        for (int i=1;i<s.length();i++){
            char c = s.charAt(i);
            while (--i >0 && ++i <s.length()){
                if (s.charAt(--i) == s.charAt(++i)){

                }
            }
        }


        return null;
    }
}
