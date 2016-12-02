import jdk.internal.util.xml.impl.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by wpantoja on 01/12/2016.
 */
public class MaxPairwiseProductTest {

    public static class Pair<A, B> {
        public final A left;
        public final B right;

        private Pair(A a, B b) {
            super();
            left = a;
            right = b;
        }

        public static final <A, B> Pair<A, B> of(A a, B b) {
            return Optional.of(a).map(left -> Optional.of(b).map(right -> new Pair(left, right)).orElse(null)).orElse(null);
        }
    }

    public static class MyRandom extends Random {
        public MyRandom() {}
        public MyRandom(int seed) { super(seed); }

        public int nextNonNegative() {
            return next(Integer.SIZE - 1) % 100000;
        }
    }

    public static final <X, Y> Supplier<Optional<Pair<Y, Long>>> timer(Supplier<Y> supplier) {
        long start = System.nanoTime();
        Y y = supplier.get();
        return () -> Optional.of(Pair.of(supplier.get(), System.nanoTime() - start));
    }

    public static int[] test1() {
        int size = 10000;
        MyRandom generator = new MyRandom();
        return IntStream.generate(() -> generator.nextNonNegative()).limit(size).toArray();
    }


    public static int[] test2() {
        return new int[] {
                1, 2, 3, 4, 5, 6, 7, 8, 9
        };
    }

    public static int[] test3() {
        int size = 10000;
        return IntStream.iterate(1, i -> i + 1).limit(size).toArray();
    }


    public static int[] test4() {
        int size = 10000;
        List<Integer> integerList = IntStream.iterate(1, i -> i + 1).limit(size).boxed().collect(Collectors.toList());
        Collections.shuffle(integerList);
        return integerList.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String... args) {
        int[] input = test1();

        Optional<int[]> inputOptional = Optional.of(input);
        Supplier<Integer> solution = () -> inputOptional.map(MaxPairwiseProduct::getMaxPairwiseProduct).orElse(null);
        Supplier<Integer> solution_1 = () -> inputOptional.map(MaxPairwiseProduct_1::getMaxPairwiseProduct).orElse(null);
        Supplier<Integer> solution_2 = () -> inputOptional.map(MaxPairwiseProduct_2::getMaxPairwiseProduct).orElse(null);

        timer(solution).get().ifPresent(pair -> {
            System.out.println(String.format("Solution 1:\n\tresult = %s\n\ttime = %s ns\n", pair.left, pair.right));
        });

        timer(solution_1).get().ifPresent(pair -> {
            System.out.println(String.format("Solution 2:\n\tresult = %s\n\ttime = %s ns\n", pair.left, pair.right));
        });

        timer(solution_2).get().ifPresent(pair -> {
            System.out.println(String.format("Solution 3:\n\tresult = %s\n\ttime = %s ns\n", pair.left, pair.right));
        });
    }
}
