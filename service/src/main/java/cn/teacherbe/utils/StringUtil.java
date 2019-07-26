package cn.teacherbe.utils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public StringUtil() {
    }

    public static final String replace(String str, String oldString,
                                       String newString) {
        if (str == null)
            return null;
        int i = 0;
        if ((i = str.indexOf(oldString, i)) >= 0) {
            char strCharArr[] = str.toCharArray();
            char newStringCharArr[] = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(strCharArr.length);
            buf.append(strCharArr, 0, i).append(newStringCharArr);
            i += oLength;
            int j;
            for (j = i; (i = str.indexOf(oldString, i)) > 0; j = i) {
                buf.append(strCharArr, j, i - j).append(newStringCharArr);
                i += oLength;
            }
            buf.append(strCharArr, j, strCharArr.length - j);
            return buf.toString();
        } else {
            return str;
        }
    }

    /**
     * 格式化日期样式（yyyy-mm-dd）

     */
    public static String getDateFashionStr(String str) {
        if (StringUtil.isEmpty(str))
            return "";
        if (str.indexOf("-") == -1)
            return "";
        String[] tempArr = str.split("-");
        if (tempArr.length != 3)
            return "";
        if (tempArr[1].length() < 2)
            tempArr[1] = "0" + tempArr[1];
        if (tempArr[2].length() < 2)
            tempArr[2] = "0" + tempArr[2];
        return tempArr[0] + "-" + tempArr[1] + "-" + tempArr[2];
    }

    /**
     * 把str1追加到str2中，过滤掉重复的元素，其中元素以字符窜regex
     */
    public static String getUnionStr(String str1, String str2, String regex) {
        //如果str2为空或者str2值为regex直接返回str1
        if (StringUtil.isEmpty(str2) || str2.equals(regex)) {
            //System.out.println(" str2 is null ");
            return str1;
        }
        //如果str1为空或者str1值为regex直接返回str2
        if (StringUtil.isEmpty(str1) || str1.equals(regex)) {
            //System.out.println(" str1 is null ");
            return str2;
        }
        //str1串以regex为分割符转换成List
        List<String> list = new ArrayList<String>();
        StringTokenizer stz = new StringTokenizer(str1, regex);
        for (; stz.hasMoreTokens(); list.add(stz.nextToken()))
            ;
        //System.out.println("str1==="+str1);
        //System.out.println("str2==="+str2);
        //System.out.println("lsit.szie()==="+list.size());
        //把不存在的元素追加到list
        StringTokenizer stz1 = new StringTokenizer(str2, regex);
        for (; stz1.hasMoreTokens();) {
            String tempStr = stz1.nextToken();
            if (list.contains(tempStr))
                continue;
            list.add(tempStr);
        }
        //System.out.println("list.size()11===="+list.size());
        //list 拼成串 以regex分割
        String rstr = "";
        for (String tempStr : list) {
            rstr += tempStr + regex;
        }
        //去掉最好一个regex
        if (StringUtil.notEmpty(rstr) && rstr.length() > regex.length()) {
            rstr = rstr.substring(0, rstr.length() - regex.length());
        }
        //System.out.println("rstr==="+rstr);
        return rstr;
    }

    /**
     * 用于数据库操作时，特殊字符处理

     * 字符串中的单引号换成两个单引号

     */
    public static String dbStr(String str) {
        return str.replaceAll("'", "''");
    }

    /**
     * 防止sql注入攻击
     * 判断参数中是否含有数据库操作关键字

     */
    public static boolean hasDbKeyword(String str) {
        if (StringUtil.isEmpty(str))
            return false;
        List<String> list = StringUtil.getDbKeyword();
        boolean hasFlag = false;
        for (String keyword : list) {
            if (str.indexOf(keyword) != -1) {
                hasFlag = true;
                break;
            }
        }
        return hasFlag;
    }

    /**
     * 获取数据库操作关键字
     */
    public static List<String> getDbKeyword() {
        List<String> list = new ArrayList<String>();
        list.add("drop");
        list.add("auto");
        list.add("select");
        list.add("update");
        list.add("delete");
        return list;
    }

    /**
     * 字符串为空

     */
    public static boolean isEmpty(Object src) {
        if (src == null || src.toString().trim().equals(""))
            return true;
        else
            return false;
    }

    /**
     * 字符串不为空
     */
    public static boolean notEmpty(Object src) {
        if (src == null || src.toString().trim().equals("") || src.toString().trim().equals("null"))
            return false;
        else
            return true;
    }

    /**
     * 随机获取num位字符

     */
    public static String getRandomStr(int num) {
        String[] strArea = new String[] { "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
                "v", "w", "x", "y", "z" };
        String index = "";
        for (int i = 0; i < num; i++) {
            int position = (int) (Math.random() * strArea.length);
            index = index + strArea[position];

        }
        return index;
    }

    /**
     * 判断该字符是不是汉字
     */
    public static boolean isChi(char c) {
        boolean flag = false;
        byte[] bytes = String.valueOf(c).getBytes();
        if (bytes.length == 2) {
            int[] ints = new int[2];
            ints[0] = bytes[0] & 0xff;
            ints[1] = bytes[1] & 0xff;
            if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
                    && ints[1] <= 0xFE) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 限制字符串长度

     */
    public static String getShowPart(String str, int listNum) {
        if (isEmpty(str))
            return "";
        //判断字符串长度是否超过上限

        int len = getBLength(str);
        if (len <= listNum)
            return str;
        String showStr = "";
        int num = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = String.valueOf(chars[i]).getBytes();
            num += bytes.length;
            if (num > listNum)
                break;
            showStr += String.valueOf(chars[i]);
        }
        return showStr;
    }

    /**
     * 限制字符串长度，超长部分用..替换
     */
    public static String getShowPartDotII(String str, int listNum) {
        if (isEmpty(str))
            return "";
        //判断字符串长度是否超过上限

        int len = getBLength(str);
        if (len <= listNum)
            return str;
        String showStr = "";
        int num = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = String.valueOf(chars[i]).getBytes();
            num += bytes.length;
            if (num + 2 > listNum)
                break;
            showStr += String.valueOf(chars[i]);
        }
        return showStr + "..";
    }

    /**
     * 限制字符串长度，超长部分用...替换
     */
    public static String getShowPartDotIII(String str, int listNum) {
        if (isEmpty(str))
            return "";
        //判断字符串长度是否超过上限

        int len = getBLength(str);
        if (len <= listNum)
            return str;
        String showStr = "";
        int num = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = String.valueOf(chars[i]).getBytes();
            num += bytes.length;
            if (num + 3 > listNum)
                break;
            showStr += String.valueOf(chars[i]);
        }
        return showStr + "...";
    }

    /**
     * 获取字符串的字节长度
     */
    public static int getBLength(String str) {
        if (isEmpty(str))
            return 0;
        int num = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = String.valueOf(chars[i]).getBytes();
            num += bytes.length;
        }
        return num;
    }

    /**
     * 判断字符串是否都是数字

     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断HashMap的key值是否为空

     */
    public static boolean notNullInHm(String key, HashMap<String, String> hm) {
        boolean flag = false;
        if (null == key || null == hm)
            return flag;
        if (hm.containsKey(key)) {
            if (notEmpty(hm.get(key))) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 由数组转换字符串，以regex变量的值隔开
     */
    public static String getArrToStr(String[] arr, String regex) {
        if (null == arr || arr.length == 0)
            return "";
        if (arr.length == 1)
            return arr[0];
        String str = "";
        for (String temp : arr) {
            str += temp + regex;
        }
        str = str.substring(0, str.length() - regex.length());

        return str;
    }

    /**
     * 由数组转换字符串，以regex变量的值隔开
     * 返回字符串中的元素以“'”引起来
     */
    public static String getArrToSingleQuotesStr(String[] arr, String regex) {
        if (null == arr || arr.length == 0)
            return "";
        if (arr.length == 1)
            return "'" + arr[0] + "'";
        String str = "";
        for (String temp : arr) {
            str += "'" + temp + "'" + regex;
        }
        str = str.substring(0, str.length() - regex.length());

        return str;
    }

    /**
     * 由字符串转换数组，以regex变量的值隔开
     */
    public static String[] getStrToArr(String str, String regex) {
        if (StringUtil.isEmpty(str))
            return null;
        if (str.indexOf(regex) == -1) {
            return new String[] { str };
        }
        return str.split(regex);
    }

    /**
     * 规范url，去除无用参数o
     */
    public static String kcStr(String url, String o) {
        if (StringUtil.isEmpty(url))
            return "";
        if (url.indexOf(o) == -1)
            return url;
        String s = url.substring(url.indexOf(o), url.length());
        int ind = s.indexOf("&");
        if (ind == -1) {//如果o是最后一个参数

            if (url.length() == s.length())
                return "";
            else
                return url.substring(0, url.indexOf(o));
        }
        //如果不是最后一个参数

        String s1 = s.substring(ind + 1, s.length());
        return url.substring(0, url.indexOf(o)) + s1;
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     */
    public static String replaceBlank(String str) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        //String str="I am a, I am Hello ok, \n new line ffdsa!";
        //System.out.println("before:"+str);
        Matcher m = p.matcher(str);
        String rStr = m.replaceAll("");
        //System.out.println("before:"+str);
        //System.out.println("after:"+rStr);
        return rStr;
    }

    public static String getNotNull(Object src){
        return String.valueOf(src==null||src.toString().trim().equals("")?"":src);
    }
    public static String timeStr(){
        Calendar cal =Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }


}
