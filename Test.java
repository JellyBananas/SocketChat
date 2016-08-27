import java.util.Scanner;

/**
 * Created by jellyBananas on 2016/8/27.
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String string = scanner.next();
            if (string == "exit"){
                break;
            }
        }
        System.out.println("Over");

    }
}
