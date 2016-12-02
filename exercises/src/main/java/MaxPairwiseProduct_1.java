import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MaxPairwiseProduct_1 {

    static int getMaxPairwiseProduct(int[] numbers) {
        int max[] = new int[2];

        max[0] = Math.min(numbers[0], numbers[1]);
        max[1] = Math.max(numbers[0], numbers[1]);
        for (int index = 2; index < numbers.length; index++) {
            int number = numbers[index];
            if (max[0] >= number) {
                continue;
            } else if (max[0] < number) {
                if (max[1] < number) {
                    max[0] = max[1];
                    max[1] = number;
                } else {
                    max[0] = number;
                }
            }
        }
        return max[0] * max[1];
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}