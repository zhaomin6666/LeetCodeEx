package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 65. 有效数字
 * <p>
 * 验证给定的字符串是否可以解释为十进制数字。
 * <p>
 * 例如:
 * <p>
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * <p>
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 * <p>
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 * <p>
 * 更新于 2015-02-10:
 * C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码。
 *
 * <p>
 *
 * @author zm
 */
public class LEET065 {
    public static void main(String[] args) {
        LEET065 l065 = new LEET065();
        System.out.println(l065.new Solution3().isNumber("0"));//true
        System.out.println(l065.new Solution3().isNumber(" 0.1 "));//true
        System.out.println(l065.new Solution3().isNumber("abc"));//false
        System.out.println(l065.new Solution3().isNumber("1 a"));//false
        System.out.println(l065.new Solution3().isNumber("2e10"));//true
        System.out.println(l065.new Solution3().isNumber(" -90e3   "));//true
        System.out.println(l065.new Solution3().isNumber(" 1e"));//false
        /*System.out.println(l065.new Solution2().isNumber("e3"));//false
        System.out.println(l065.new Solution2().isNumber(" 6e-1"));//true
        System.out.println(l065.new Solution2().isNumber(" 99e2.5 "));//false
        System.out.println(l065.new Solution2().isNumber("53.5e93"));//true
        System.out.println(l065.new Solution2().isNumber(" --6 "));//false
        System.out.println(l065.new Solution2().isNumber("-+3"));//false
        System.out.println(l065.new Solution2().isNumber("95a54e53"));//false
        System.out.println(l065.new Solution2().isNumber("2eee."));*/

    }


    private class Solution {
        private HashSet<Character> digitSet;
        private HashSet<Character> symbolSet;

        public Solution() {
            digitSet = new HashSet<>();
            symbolSet = new HashSet<>();
            digitSet.add('0');
            digitSet.add('1');
            digitSet.add('2');
            digitSet.add('3');
            digitSet.add('4');
            digitSet.add('5');
            digitSet.add('6');
            digitSet.add('7');
            digitSet.add('8');
            digitSet.add('9');
            symbolSet.add('+');
            symbolSet.add('-');
        }

        public boolean isNumber(String s) {
            s = s.trim();
            String[] parts = s.split("e");
            if (parts.length == 0 || parts.length >= 3) {
                return false;
            }
            if ("".equals(parts[0])) {
                return false;
            }
            char[] chars1 = parts[0].toCharArray();
            boolean hasDigit1 = false;
            int hasDot = 0;
            if (digitSet.contains(chars1[0])) {
                hasDigit1 = true;
            } else if (chars1[0] == '.') {
                hasDot++;
            } else if (!symbolSet.contains(chars1[0])) {
                return false;
            }

            for (int i = 1; i < chars1.length; i++) {
                if (digitSet.contains(chars1[i])) {
                    hasDigit1 = true;
                } else if (chars1[i] == '.') {
                    if (hasDot > 0) {
                        return false;
                    } else {
                        hasDot = 1;
                    }
                } else {
                    return false;
                }
                if (hasDot > 0) {
                    hasDot++;
                }
            }
            if (hasDot == 1 || !hasDigit1) {
                return false;
            }
            if (s.charAt(s.length() - 1) == 'e') {
                return false;
            }
            if (parts.length == 2) {
                if ("".equals(parts[1])) {
                    return false;
                }
                char[] chars2 = parts[1].toCharArray();
                boolean hasDigit2 = false;
                if (digitSet.contains(chars2[0])) {
                    hasDigit2 = true;
                } else if (!symbolSet.contains(chars2[0])) {
                    return false;
                }
                for (int i = 1; i < chars2.length; i++) {
                    if (!digitSet.contains(chars2[i])) {
                        return false;
                    }
                    hasDigit2 = true;
                }
                if (!hasDigit2) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 表驱动法
     */
    private class Solution2 {
        int[][] table = new int[][]{
                {0, 1, 6, 2, -1},
                {-1, -1, 6, 2, -1},
                {-1, -1, 3, -1, -1},
                {8, -1, 3, -1, 4},
                {-1, 7, 5, -1, -1},
                {8, -1, 5, -1, -1},
                {8, -1, 6, 3, 4},
                {-1, -1, 5, -1, -1},
                {8, -1, -1, -1, -1}
        };
        int finals = 0b101101000;

        public boolean isNumber(String s) {
            int state = 0;
            for (int i = 0; i < s.length(); i++) {
                int id = getIndexByCharType(s.charAt(i));
                if (id < 0) {
                    return false;
                }
                state = table[state][id];
                if (state < 0) {
                    return false;
                }
            }
            // 最后这一步是标识最后的状态是否可以作为结束状态，比如最后一位是e，那么state就为4
            // 此时1<<state = 0b000010000 和 0b101101000做与操作=0所以不是数字
            return (finals & (1 << state)) > 0;
        }

        private int getIndexByCharType(char c) {
            if (c == ' ') {
                return 0;
            } else if (c == '+' || c == '-') {
                return 1;
            } else if (c >= '0' && c <= '9') {
                return 2;
            } else if (c == '.') {
                return 3;
            } else if (c == 'e') {
                return 4;
            } else {
                return -1;
            }
        }
    }

    /**
     * 用正则表达式
     */
    private class Solution3 {
        public boolean isNumber(String s) {
            return s.matches(" *[+-]?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)(e[+-]?[0-9]+)? *$");
        }
    }

    /**
     * 以下使用工厂模式创建验证类
     */
    interface NumberValidate {
        boolean validate(String str);
    }

    enum ValidatorType {
        INT_VALIDATOR, FLOAT_VALIDATOR, SCI_VALIDATOR;
    }

    static class ValidatorFactory {

        private static final NumberValidateTemplate integerValidator = new IntegerValidator();
        private static final NumberValidateTemplate floatValidator = new FloatValidator();
        private static final NumberValidateTemplate sciValidator = new ScienceNumberValidator();

        public static NumberValidateTemplate getValidator(ValidatorType type) {
            if (type == ValidatorType.INT_VALIDATOR) {
                return integerValidator;
            } else if (type == ValidatorType.FLOAT_VALIDATOR) {
                return floatValidator;
            } else if (type == ValidatorType.SCI_VALIDATOR) {
                return sciValidator;
            }
            return null;
        }
    }

    abstract static class NumberValidateTemplate implements NumberValidate {
        @Override
        public final boolean validate(String str) {
            if (str == null || str.length() == 0) {
                return false;
            }

            // remove empty space.
            str = str.trim();

            // remove overhaed sign if necessary.
            if (str.startsWith("+") || str.startsWith("-")) {
                str = str.substring(1);
            }

            if (str.length() == 0) {
                return false;
            }

            return doValidate(str.toCharArray());
        }

        public abstract boolean doValidate(char[] strArr);
    }


    static class IntegerValidator extends NumberValidateTemplate {
        @Override
        public boolean doValidate(char[] strArr) {

            for (int idx = 0; idx < strArr.length; idx++) {
                char ch = strArr[idx];
                if (!Character.isDigit(ch)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class FloatValidator extends NumberValidateTemplate {
        @Override
        public boolean doValidate(char[] strArr) {
            // boundary case: cannot contains only one dot Character.
            if (strArr.length < 2) {
                return false;
            }

            int pos = findPointPosition(strArr);
            if (pos == -1) return false;

            NumberValidateTemplate integerValidator = ValidatorFactory.getValidator(ValidatorType.INT_VALIDATOR);
            if (integerValidator.doValidate(Arrays.copyOfRange(strArr, 0, pos))
                    && integerValidator.doValidate(Arrays.copyOfRange(strArr, pos + 1, strArr.length))) {
                return true;
            }

            return false;

        }

        private int findPointPosition(char[] arr) {
            for (int idx = 0; idx < arr.length; idx++) {
                if (arr[idx] == '.') return idx;
            }
            return -1;
        }
    }

    static class ScienceNumberValidator extends NumberValidateTemplate {

        @Override
        public boolean doValidate(char[] strArr) {
            // boundary case: cannot contains only one exponent Component e.
            if (strArr.length < 2) {
                return false;
            }

            int pos = findExponentPosition(strArr);
            if (pos == -1) return false;

            if (checkBefore(strArr, 0, pos)
                    && checkAfter(strArr, pos + 1, strArr.length)) {
                return true;
            }

            return false;
        }

        private int findExponentPosition(char[] arr) {
            for (int idx = 0; idx < arr.length; idx++) {
                if (arr[idx] == 'e')
                    return idx;
            }
            return -1;
        }

        /**
         * Character before  "e" can be either a float number or an integer.
         */
        private boolean checkBefore(char[] arr, int start, int end) {
            if (start == end) return false;
            String part = new String(Arrays.copyOfRange(arr, start, end));
            // spaces are not allowed before and after e
            if (part.startsWith(" ") || part.endsWith(" ")) {
                return false;
            }

            NumberValidateTemplate floatValidator = ValidatorFactory.getValidator(ValidatorType.FLOAT_VALIDATOR);
            NumberValidateTemplate integerValidator = ValidatorFactory.getValidator(ValidatorType.INT_VALIDATOR);

            if (floatValidator.validate(part)
                    || integerValidator.validate(part)) {
                return true;
            }

            return false;
        }

        /**
         * Character after "e" must be  an integer.
         */
        private boolean checkAfter(char[] arr, int start, int end) {
            String part = new String(Arrays.copyOfRange(arr, start, end));
            if (part.startsWith(" ") || part.endsWith(" ")) {
                return false;
            }

            NumberValidateTemplate integerValidator = ValidatorFactory.getValidator(ValidatorType.INT_VALIDATOR);


            if (integerValidator.validate(part)) {
                return true;
            }

            return false;
        }
    }

    /**
     * Check Number is valid or not through number of filters
     */
    static class NumberValidator implements NumberValidate {
        @Override
        public boolean validate(String str) {
            for (ValidatorType type : ValidatorType.values()) {
                if (ValidatorFactory.getValidator(type).validate(str)) {
                    //System.out.println(type);
                    return true;
                }
            }
            return false;
        }
    }

    private class Solution4 {

        public boolean isNumber(String s) {
            NumberValidator numberValidator = new NumberValidator();
            return numberValidator.validate(s);
        }
    }
}
