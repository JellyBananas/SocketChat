import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by jellyBananas on 2016/8/27.
 */
public class Receive extends Thread{
    Socket socket;
    Receive(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader sockIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String s = sockIn.readLine();
                if (s =="stop")
                    break;
                if (s != null){
                    System.out.println(s);
                }
            }
            sockIn.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
