package ai.yuanxing;

import java.util.concurrent.ThreadLocalRandom;

public class CommonUtil {

    public static  final class PhoneVerificationCode{

        public static boolean isValidChinesePhoneNumber(String phoneNumber) {
            // 去除手机号码中的空格和特殊字符
            String normalizedNumber = phoneNumber.replaceAll("[\\s-]+", "");

            // 验证手机号码的格式
            // ^ 表示字符串的开头
            //  1 表示以1开头
            //  [3-9] 表示第二位数字在3到9之间
            // \\d{9} 表示后面跟着9位数字
            // $ 表示字符串的结尾
            String pattern = "^1[3-9]\\d{9}$";

            return normalizedNumber.matches(pattern);
        }


        public static String generateVerificationCode() {
            StringBuilder codeBuilder = new StringBuilder();

            int CODE_LENGTH = 4;
            for (int i = 0; i < CODE_LENGTH; i++) {
                int randomNumber = ThreadLocalRandom.current().nextInt(10);
                codeBuilder.append(randomNumber);
            }

            return codeBuilder.toString();
        }
    }

}
