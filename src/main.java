import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by 1 on 28.09.2017.
 */
public class main {
    public static void main(String[] args) throws Exception {
        System.out.println("CatchTheStirka 1.0.1  Last update 28.10.17 ");
        System.out.println("To start enter number of days to monitor");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int days = 0;
        boolean success = false;
        while(!success) {
            try {
                success = true;
                days = Integer.parseInt(in.readLine());
            } catch (java.lang.NumberFormatException e) {
                success = false;
            }
        }
        System.out.println("Catching stirka from now");
        UrlReader urlReader = new UrlReader("http://7ka.mipt.ru/schedule");
        urlReader.readWrite(days);




    }
}
