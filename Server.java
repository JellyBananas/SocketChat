import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Listening on port: "+serverSocket.getLocalPort());
            System.out.println();
            //开始收发数据
            Send send = new Send(socket);
            send.start();
            Receive receive = new Receive(socket);
            receive.start();
            //关闭
//            socket.close();
//            serverSocket.close(); //不注释掉，运行到此处直接关闭了socket 程序结束
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("Error");
        }
    }
}
