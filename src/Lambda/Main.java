package Lambda;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Supplier<String> helloSupplier = LambdaUtil.helloSupplier();
        System.out.println("helloSupplier : " + helloSupplier.get());//Hello
        System.out.println("------------------------");
        Predicate<String> isEmptyPredicate = LambdaUtil.isEmptyPredicate();

        System.out.println("isEmptyPredicate : " + isEmptyPredicate.test("java"));//false
        System.out.println("isEmptyPredicate : " + isEmptyPredicate.test(""));//true
        System.out.println("------------------------");


        BiFunction<String, Integer, String> stringMultiplier = LambdaUtil.stringMultiplier();
        System.out.println("stringMultiplier : " + stringMultiplier.apply("Hi", 3));//HiHiHi
        System.out.println("------------------------");


        Function<BigDecimal, String> toDollarStringFunction = LambdaUtil.toDollarStringFunction();
        String tenDollarStr = toDollarStringFunction.apply(BigDecimal.TEN.setScale(2));
        System.out.println("toDollarStringFunction : " + tenDollarStr);//$10.00
        System.out.println("------------------------");


        Predicate<String> lengthInRangePredicate = LambdaUtil.lengthInRangePredicate(4, 8);
        System.out.println("lengthInRangePredicate : " + lengthInRangePredicate.test("Hello world"));//false
        System.out.println("------------------------");


        IntSupplier randomIntSupplier = LambdaUtil.randomIntSupplier();
        int firstValue = randomIntSupplier.getAsInt();
        int secondValue = randomIntSupplier.getAsInt();
        System.out.println("randomIntSupplier : " + firstValue + " , " + secondValue + " : " + (firstValue == secondValue));//false
        System.out.println("------------------------");


        IntUnaryOperator boundedRandomIntSupplier = LambdaUtil.boundedRandomIntSupplier();
        int randomIntLessThan1000 = boundedRandomIntSupplier.applyAsInt(1000);
        System.out.println("boundedRandomIntSupplier : " + randomIntLessThan1000 + " : " + (randomIntLessThan1000 < 1000));//true
        System.out.println("------------------------");


        IntUnaryOperator squareOperation = LambdaUtil.intSquareOperation();
        System.out.println("intSquareOperation : " + squareOperation.applyAsInt(4));//16
        System.out.println("------------------------");


        LongBinaryOperator sumOperation = LambdaUtil.longSumOperation();
        System.out.println("longSumOperation : " + sumOperation.applyAsLong(5, -10));//-5
        System.out.println("------------------------");

        ToIntFunction<String> stringToIntConverter = LambdaUtil.stringToIntConverter();
        int num = stringToIntConverter.applyAsInt("234");
        System.out.println("stringToIntConverter : " + num);//234
        System.out.println("------------------------");

        Supplier<IntUnaryOperator> fiveMultiplyFunctionSupplier = LambdaUtil.nMultiplyFunctionSupplier(5);
        IntUnaryOperator multiplyByFiveOperation = fiveMultiplyFunctionSupplier.get();
        int result = multiplyByFiveOperation.applyAsInt(11);
        System.out.println("nMultiplyFunctionSupplier : " + result);//11 * 5 => 55
        System.out.println("------------------------");


        Supplier<Supplier<Supplier<String>>> wellDoneSupplier = LambdaUtil.trickyWellDoneSupplier();
        System.out.println("trickyWellDoneSupplier : " + wellDoneSupplier.get().get().get());//WELL DONE!
        System.out.println("------------------------");


        UnaryOperator<Function<String, String>> composeWithTrimFunction = LambdaUtil.composeWithTrimFunction();
        Function<String, String> toLowerWithTrim = composeWithTrimFunction.apply(String::toLowerCase);
        System.out.println("composeWithTrimFunction : " + toLowerWithTrim.apply("  Hey "));//hey
        System.out.println("------------------------");

        //extra points
        BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> intFunctionToConditionalIntFunction
                = LambdaUtil.functionToConditionalFunction();
        IntUnaryOperator abs = intFunctionToConditionalIntFunction.apply(a -> -a, a -> a < 0);
        System.out.println("functionToConditionalFunction : " + abs.applyAsInt(-5));//5
        System.out.println("------------------------");


        BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader = LambdaUtil.functionLoader();
        Map<String, IntUnaryOperator> functionMap = new HashMap<>();
        functionMap.put("increment", x -> x + 1);
        functionMap.put("square", x -> x * x);

        IntUnaryOperator incrementFunction = functionLoader.apply(functionMap, "increment");
        IntUnaryOperator squareFunction = functionLoader.apply(functionMap, "square");
        IntUnaryOperator identityFunction = functionLoader.apply(functionMap, "none");

        System.out.println(incrementFunction.applyAsInt(4));//5
        System.out.println(squareFunction.applyAsInt(3));//9
        System.out.println(identityFunction.applyAsInt(10));//10

    }

}
