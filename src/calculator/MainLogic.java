package calculator;

import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public final class MainLogic  {
    protected static final String roman = "I?[XV]|V?II?I?";
    protected static final String arabic = "[1-9]|10";
    protected static final String operation = "[-+*/]";
    protected static final String other = "|";

    // данные заданы виде map
    protected static final Map<String, IntBinaryOperator> calculator = Map.of(
            "-", (x, y) -> x - y,
            "+", (x, y) -> x + y,
            "/", (x, y) -> x / y,
            "*", (x, y) -> x * y);

    public static void main(String[] args) {
         var scanner = new Scanner(System.in);
         var a = scanner.next(roman + other + arabic);
         var op = scanner.next(operation);
         var isRoman = a.matches(roman);
         var b = scanner.next(isRoman ? roman : arabic);

         ToIntFunction<String> toInt = isRoman ? MainLogic::romanToArabic : Integer::parseInt;
         IntFunction<String> toString = isRoman ? MainLogic::arabicToRoman : Integer::toString;

         int result = calculator.get(op).applyAsInt(toInt.applyAsInt(a), toInt.applyAsInt(b));

        System.out.println(toString.apply(result));
    }

    static int romanToArabic(final String number) {
        return 1 + List.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X").indexOf(number);
    }


    static String arabicToRoman(final int number) {
        return String.join("",
                number == 100 ? "C" : "",
                new String[]{" ","X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}[number % 100 / 10],
                new String[]{" ","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}[number % 10]
        );
    }
}
