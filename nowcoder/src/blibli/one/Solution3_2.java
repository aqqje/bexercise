package blibli.one;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description
 */
public class Solution3_2 {

    private static final String EOF = "END";

    enum Operation {
        INVALID(' '),
        PLUS('+'),
        SUBTRACTION('-'),
        MULTIPICATION('*'),
        DIVISION('/');
        private char value;

        Operation(char value) {
            this.value = value;
        }

        public static Operation getOperationByValue(char value) {
            Operation[] operations = Operation.values();
            for (Operation operation : operations) {
                if (operation.value == value) {
                    return operation;
                }
            }
            return INVALID;
        }
    }

    public static void main(String[] args) {
        //处理输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (EOF.equals(line)) {
                break;
            }
            /**
             * res保存结果
             * tmp保存乘除运算结果，遇到+-清除
             * num为数字
             * flag1 上次加减运算
             * flag2 上次乘除运算
             */
            int res = 0;
            int tmp = -1;
            int num = -1;
            Operation flag1 = Operation.PLUS;
            Operation flag2 = Operation.INVALID;
            for (int i = 0; i < line.length(); ++i) {
                //获取操作数
                Operation operation = Operation.getOperationByValue(line.charAt(i));
                //如果是数字
                switch (operation) {
                    case INVALID: {
                        //数字
                        if (num == -1) {
                            num = line.charAt(i) - 48;
                        } else {
                            num = num * 10 + line.charAt(i) - 48;
                        }
                        break;
                    }
                    case PLUS:
                    case SUBTRACTION: {
                        //操作数是加法或减法
                        /**
                         * 先判断flag2是否有效
                         * 有效
                         *      则先将num*tmp或者替换
                         *      根据flag1 将res +- tmp
                         * 无效
                         *      根据flag1 将res +- num
                         */
                        switch (flag2) {
                            //乘法
                            case MULTIPICATION: {
                                if (-1 == tmp) {
                                    tmp = num;
                                } else {
                                    tmp = tmp * num;
                                }
                                switch (flag1) {
                                    case PLUS: {
                                        res += tmp;
                                        break;
                                    }
                                    case SUBTRACTION: {
                                        res -= tmp;
                                        break;
                                    }
                                }
                                break;
                            }
                            //除法
                            case DIVISION: {
                                if (-1 == tmp) {
                                    tmp = num;
                                } else {
                                    tmp = tmp / num;
                                }
                                switch (flag1) {
                                    case PLUS: {
                                        res += tmp;
                                        break;
                                    }
                                    case SUBTRACTION: {
                                        res -= tmp;
                                        break;
                                    }
                                }
                                break;
                            }
                            case INVALID: {
                                switch (flag1) {
                                    case PLUS: {
                                        res += num;
                                        break;
                                    }
                                    case SUBTRACTION: {
                                        res -= num;
                                        break;
                                    }
                                }
                            }
                        }
                        flag1 = operation;
                        flag2 = Operation.INVALID;
                        tmp = -1;
                        num = -1;
                        break;
                    }
                    case MULTIPICATION:
                    case DIVISION: {
                        //操作数是乘法或减法
                        /**
                         * tmp 为 tmp * / num 或替换
                         */
                        switch (flag2){
                            case MULTIPICATION:{
                                if (-1 == tmp){
                                    tmp = num;
                                }else {
                                    tmp *= num;
                                }
                                break;
                            }
                            case DIVISION:{
                                if (-1 == tmp){
                                    tmp = num;
                                }else {
                                    tmp /= num;
                                }
                                break;
                            }
                            case INVALID:{
                                tmp = num;
                                break;
                            }
                        }
                        num = -1;
                        flag2 = operation;
                        break;
                    }
                    default:
                }
            }
            //处理最后的数字
            switch (flag2){
                case MULTIPICATION:{
                    //上一次是乘法
                    tmp*=num;
                    switch (flag1){
                        case PLUS:{
                            res += tmp;
                            break;
                        }
                        case SUBTRACTION:{
                            res -= tmp;
                            break;
                        }
                    }
                    break;
                }
                case DIVISION:{
                    //上一次是除法
                    tmp/=num;
                    switch (flag1){
                        case PLUS:{
                            res += tmp;
                            break;
                        }
                        case SUBTRACTION:{
                            res -= tmp;
                            break;
                        }
                    }
                    break;
                }
                case INVALID:{
                    switch (flag1){
                        case PLUS:{
                            res += num;
                            break;
                        }
                        case SUBTRACTION:{
                            res -= num;
                            break;
                        }
                    }
                }
            }
            System.out.println(res);
        }

    }
}
