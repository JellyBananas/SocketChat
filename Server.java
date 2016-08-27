import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by jellyBananas on 2016/8/27.
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8889);
            Socket socket = new Socket();
            socket = serverSocket.accept();
            if (socket == null){
                System.out.println("socket null");
                System.exit(1);
            }
            else{
                System.out.println("accept connection from: "+socket.getInetAddress().getHostAddress());
            }
            BufferedReader sockIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Listening on port: "+serverSocket.getLocalPort());
            //开始收发数据
            Fa fa = new Fa(socket);
            fa.start();
            while(true){
                String s = sockIn.readLine();
                if (s =="stop")
                    break;
                if (s != null){
                    System.out.println(s);
                }
            }
            //关闭
            sockIn.close();
            socket.close();
            serverSocket.close();
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("Error");
        }
    }
}
class Fa extends Thread{
    Socket socket;
    Fa(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            PrintWriter sockOut = new PrintWriter(socket.getOutputStream());
            while (true){
                Scanner scanner = new Scanner(System.in);
                String string = scanner.next();
                Time time = new Time();
                sockOut.println("Server "+time.getTime());
                sockOut.println(string);
                sockOut.flush();
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