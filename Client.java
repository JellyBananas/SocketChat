import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by jellyBananas on 2016/8/27.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8889);
            if (socket == null){
                System.out.println("socket null, connect error!");
                System.exit(1);
            }
            else {
                System.out.println("connect success!");
            }
            PrintWriter sockOut = new PrintWriter(socket.getOutputStream());
            //开始收发数据
            Shou1 shou1 = new Shou1(socket);
            shou1.start();
            while (true){
                Scanner scanner = new Scanner(System.in);
                String string = scanner.next();
                if (string != null){
                    Time time = new Time();
                    sockOut.println("Client "+time.getTime());
                    sockOut.println(string);
                    sockOut.flush();
                }
                if (string == "stop")
                    break;
            }
            //关闭
            sockOut.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("Error");
        }
    }
}

class Shou1 extends Thread{
    Socket s;
    Shou1(Socket s){
        this.s = s;
    }
    @Override
    public void run() {
        try{
            BufferedReader sockIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while (true){
                String s = sockIn.readLine();
                System.out.println(s);
                if (s == "stop")
                    break;
            }
            sockIn.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
