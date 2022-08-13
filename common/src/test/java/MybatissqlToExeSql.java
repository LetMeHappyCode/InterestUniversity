import jdk.nashorn.internal.runtime.regexp.RegExp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 转换sql语句
 */
public class MybatissqlToExeSql {


    public static void main(String[] args) {
        //没有处理的sql语句
        String beforePreparing ="11";
        //没有处理的参数
        String beforeParameters="";
        //处理后的sql语句
        String Preparing="";
        //处理后的参数
        String Parameters="";

//        beforePreparing = "2022-08-10 15:44:29.790  INFO [-,8fd09fb13945183c,8fd09fb13945183c,false] 20344 --- [nio-9091-exec-3] o.a.ibatis.logging.stdout.StdOutImpl     : ==>  Preparing: select code1 from ldcode1 where codetype='excelRsCount' and a=a?aand code = ? \n";

//        beforeParameters="2022-08-10 15:44:29.790  INFO [-,8fd09fb13945183c,8fd09fb13945183c,false] 20344 --- [nio-9091-exec-3] o.a.ibatis.logging.stdout.StdOutImpl     : ==> Parameters: caseinfovo(String),caseinfovo(String)\n";

        //读取文件
        String[] strings=new String[2];
        try {
            strings =readFile(beforePreparing,beforeParameters);
        } catch (IOException e) {
            System.out.println("读取文件异常");
            e.printStackTrace();
        }
        System.out.println(beforePreparing);

        //提取出sql语句和参数
        Preparing = MybatissqlToExeSql.extract(strings[0], "Preparing:");
        Parameters = MybatissqlToExeSql.extract(strings[1], "Parameters:");

        //合并
        StringBuffer merge = MybatissqlToExeSql.merge(Preparing, Parameters);
        //得到结果
        System.out.println("========最终结果=========");
        System.out.println(merge);

        //向文件中写入
        try {
            writeFile(merge);
        } catch (FileNotFoundException e) {
            System.out.println("写入文件异常");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writeFile(StringBuffer merge) throws IOException {
        FileWriter fileWriter=null;
        BufferedWriter bufferedWriter =null;

        fileWriter = new FileWriter("D:\\InterestUniversity\\common\\src\\test\\java\\sql.txt",true);
        bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.newLine();
        bufferedWriter.append(new String(merge));
    }

    public static String[] readFile(String beforePreparing,String beforeParameters) throws IOException {
        FileReader fileReader =null;
        BufferedReader bufferedReader =null;
        String[] strings=new String[2];
        //读取文件
        try {
            StringBuffer stringBufferPreparing=new StringBuffer();
            StringBuffer stringBufferParameters=new StringBuffer();
            fileReader = new FileReader("D:\\InterestUniversity\\common\\src\\test\\java\\sql.txt");
            bufferedReader = new BufferedReader(fileReader);
            String s = "";

            boolean flag=true;
            while ((s = bufferedReader.readLine())!=null){

                if (!s.trim().isEmpty()){
                    stringBufferPreparing.append(s);

                }else {
                    System.out.println("跳出");
                    break;
                }
            }

            while ((s = bufferedReader.readLine())!=null){
                stringBufferParameters.append(s);
            }
            beforePreparing=new String(stringBufferPreparing);
            beforeParameters=new String(stringBufferParameters);
            System.out.println("stringBufferPreparing======"+stringBufferPreparing);
            System.out.println("stringBufferParameters======="+stringBufferParameters);
            strings[0]=beforePreparing;
            strings[1]=beforeParameters;

            return strings;

        }  finally {
            fileReader.close();
            bufferedReader.close();
        }
    }


    public static Boolean isExist(String s,String t){
        //是否存在
        boolean isfind = false;
        for (int i = 0; i < s.length() - t.length() + 1; i++) {
            if (s.charAt(i) == t.charAt(0)) {
                int jc = 0;
                for (int j = 0; j < t.length(); j++) {
                    if (s.charAt(i + j) != t.charAt(j)) {
                        break;
                    }
                    jc = j;
                }
                if (jc == t.length() - 1) {
                    isfind = true;
                }
            }
        }
        return isfind;
    }
    //根据s 在t位置  后面的所有字符串，例子： s=12345 ，t=2  result=345
    public static String extract(String s, String t){
        //指针
        int p =0;
        //是否存在
        int isfind = 0;
        for (int i = 0; i < s.length() - t.length() + 1; i++) {
            if (s.charAt(i) == t.charAt(0)) {
                int jc = 0;
                for (int j = 0; j < t.length(); j++) {
                    if (s.charAt(i + j) != t.charAt(j)) {
                        break;
                    }
                    jc = j;
                }
                if (jc == t.length() - 1) {
                    isfind = 1;
                    p=i;
                }
            }
        }
        //指针移动到需要截取的位置
        p=p+t.length()+1;
        //截取
        return s.substring(p);
    }


    public static StringBuffer merge(String Preparing, String Parameters){
        //使用StringBuffer中的替换第一个字符的方法
        StringBuffer PreparingBuffer = new StringBuffer(Preparing);

        //每一个splited括号起始位置
        int startP=0;
        //每一个splited括号结束位置
        int endP=0;
        //参数类型
        String type="";
        //参数值
        String value=";";
        //已经分离
        String splited="";

        //根据','分离
        String[] split = Parameters.split(",");
        //找到每一个'？'，替换成split
        for (int i=0;i<split.length;i++){
            splited = split[i].trim();
            System.out.println(split[i].trim());
            startP = splited.indexOf("(");
            endP = splited.indexOf(")");

            value=splited.substring(0,startP);
            type=splited.substring(startP+1,endP);
//            System.out.println("分离开value=="+value);
//            System.out.println("分离开type=="+type);

            //只要不是int类型，全部都加 ‘ ’ 号，int不需要加
            if (!type.equals("int")){
                value="'"+value+"'";
//                System.out.println("修改后的value"+value);
            }
            System.out.println("======Preparing======"+Preparing);
            int p = PreparingBuffer.indexOf("?");
            PreparingBuffer.replace(p,p+1,value);
            System.out.println(PreparingBuffer);
        }

        return PreparingBuffer;


    }

}

