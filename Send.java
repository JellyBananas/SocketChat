import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jellyBananas on 2016/8/27.
 */
public class Send extends Thread {
    Socket socket;
    Send(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            PrintWriter sockOut = new PrintWriter(socket.getOutputStream());
            while (true){
                Scanner scanner = new Scanner(System.in);
                String string = scanner.next();
                if (string != null){
                    Time time = new Time();
                    sockOut.println("Server "+time.getTime());
                    sockOut.println(string);
                    sockOut.flush();
                }
                if (string == "stop"){
                    break;
                }
            }
            sockOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
