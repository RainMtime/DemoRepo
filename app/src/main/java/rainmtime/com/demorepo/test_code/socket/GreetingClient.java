



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by 雨时光 on 2019-06-06 11:51
 * Email: 746431278@qq.com
 */
public class GreetingClient {

    private static final String TAG = "GreetingClient";

    public static void main(String[] args){
        String serverName = args[0];

        int port = Integer.parseInt(args[1]);
        try {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());
            client.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
