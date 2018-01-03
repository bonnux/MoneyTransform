import java.text.DecimalFormat;
import java.util.Scanner;

public class RMBTransform
{
    private final static String[] MONEY_NUM = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private final static String[] MONEY_UNIT = {"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" ,"万", "拾", "佰"};
    private final static String[] MONEY_UNIT2 = {"角","分"};
    public static String intergerToMoney(String num)
    {
        if (num.indexOf(".") != -1)
        {
            num = num.substring(0, num.indexOf("."));
        }
        num = new StringBuilder(num).reverse().toString();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < num.length(); i++)
        {
            temp.append(MONEY_UNIT[i]);
            temp.append(MONEY_NUM[num.charAt(i) - 48]);
        }
        num = temp.reverse().toString();
        while (true)
        {
            num = num.replaceAll("零亿", "亿零");
            num = num.replaceAll("零万", "万零");
            num = num.replaceAll("零仟", "零");
            num = num.replaceAll("零佰", "零");
            num = num.replaceAll("零拾", "零");
            num = num.replaceAll("亿万", "亿");
            num = num.replaceAll("零零", "零");
            if (num.indexOf("零零") == -1
                    && num.indexOf("零亿") == -1
                    && num.indexOf("零万") == -1
                    && num.indexOf("零仟") == -1
                    && num.indexOf("零佰") == -1
                    && num.indexOf("零拾") == -1
                    && num.indexOf("亿万") == -1)
            {
                break;
            }
        }
        if (num.lastIndexOf("零") == num.length() - 1)
        {
            num = num.substring(0, num.length() - 1);
        }
        return num;
    }

    public static String decimalToMoney(String num)
    {
        if (num.indexOf(".") == -1)
        {
            return "";
        }
        num = num.substring(num.indexOf(".") + 1);
        num = new StringBuffer(num).toString();
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < num.length(); i++)
        {
            temp.append(MONEY_NUM[num.charAt(i) - 48]);
            temp.append(MONEY_UNIT2[i]);
        }
        num = temp.toString();
        while (true)
        {
            num = num.replaceAll("零角", "零");
            num = num.replaceAll("零分", "零");
            num = num.replaceAll("零零", "零");
            if (num.indexOf("零零") == -1)
            {
                break;
            }
        }
        if (num.lastIndexOf("零") == num.length() - 1)
        {
            num = num.substring(0, num.length() - 1);
        }
        return num;
    }
    public static String numToMoney(double num)
    {
        if( num <= 0 || num > 9999999999999.99)
        {
            return "超出范围，输入的数字必须大于0，且小于13位";
        }
        DecimalFormat df = new DecimalFormat("#0.##");
        String dfNum = df.format(num);
        String decimalPart = decimalToMoney(dfNum);
        String intergerPart = intergerToMoney(dfNum);
        if(intergerPart.length() == 0)
        {
            if(decimalPart.startsWith("零"))
            {
                return decimalPart.substring(1, decimalPart.length());
            }
            else
            {
                return decimalPart;
            }

        }
        else if(decimalPart.length() == 0)
        {
            return intergerPart + "元整";
        }
        else
        {
            return intergerPart + "元" + decimalPart;
        }
    }
    public static void main(String []args)
    {
        Scanner in = new Scanner(System.in);
        while(true)
        {
            System.out.print("请输入金额：");
            double num = in.nextDouble();
            if(num == -1)
            {
                break;
            }
            System.out.print("大写：人民币"+numToMoney(num)+"\n");
        }
        in.close();
    }
}