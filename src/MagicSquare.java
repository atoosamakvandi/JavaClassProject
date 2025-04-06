import java.util.Scanner;

public class MagicSquare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("یک عدد فرد برای مرتبه مربع وارد کنید: ");
        int n = scanner.nextInt();

        if (n % 2 == 0) {
            System.out.println("عدد وارد شده باید فرد باشد!");
            return;
        }

        int[][] magicSquare = new int[n][n];

        int number = 1;
        int row = 0;
        int col = n / 2;

        while (number <= n * n) {
            magicSquare[row][col] = number;

            int nextRow = (row - 1 + n) % n;
            int nextCol = (col - 1 + n) % n;

            if (magicSquare[nextRow][nextCol] != 0) {
                row = (row + 1) % n;
            } else {
                row = nextRow;
                col = nextCol;
            }

            number++;
        }

        System.out.println("مربع جادویی:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", magicSquare[i][j]);
            }
            System.out.println();
        }
    }
}