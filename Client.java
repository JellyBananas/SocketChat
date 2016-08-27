import java.io.IOException;
import java.net.Socket;

/**
 * Created by jellyBananas on 2016/8/27.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8889);
            if (socket == null){
                System.out.println("Socket null, connect error!");
                System.exit(1);
            }
            else {
                System.out.println("Connect success!");
                System.out.println();
            }
            //开始收发数据
            Receive receive = new Receive(socket);
            receive.start();
            Send send = new Send(socket);
            send.start();
            //关闭
            //socket.close(); 防止程序直接关闭socket
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("Error");
        }
    }
}

