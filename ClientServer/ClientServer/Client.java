package ClientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
public class Client {
    Socket s;
    BufferedReader in, keyin;
    PrintStream out;
    public Client() {
        try {
            s = new Socket("localhost", 2005);
            while (true) {
                keyin = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String msg = in.readLine();
                System.out.println("From Server..:" + msg);
                out = new PrintStream(s.getOutputStream(), true);
                System.out.println("Enter Message For   Server...:");
                String keyBoardMsg = keyin.readLine();
                out.println(keyBoardMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Client();
    }
}

















