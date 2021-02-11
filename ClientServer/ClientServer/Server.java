package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Server {
	    ServerSocket ss;
	    Socket s = new Socket();
	    ExecutorService pool = null;
	    public Server() {
	        pool = Executors.newFixedThreadPool(5);
	        try {
	            ss = new ServerSocket(2005);
	            System.out.println("server ready for clients...");
	            while (true) {
	                s = ss.accept();
	                Listener ob = new Listener(s, this);
	                pool.execute(ob);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public static void main(String[] args) {
	        new Server();
	    }
	}
	class Listener implements Runnable {
	    Socket client = null;
	    Server server = null;
	    PrintStream out;
	    BufferedReader in;
	    Scanner keyin;
	    static int conncount=0;
	    public Listener(Socket cli, Server ser) {
	        this.server = ser;
	        this.client = cli;
	        conncount+=1;
	        try {
	            this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
	            this.out = new PrintStream(this.client.getOutputStream());
	            out.println("connected to server");
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	    public void run() {
	        // TODO Auto-generated method stub
	        while (true) {
	            String msg;
	            try {
	                msg = in.readLine();
	                System.out.println("Client "+this.conncount +" : " + msg);
	                keyin = new Scanner(System.in);
	                System.out.println("Enter message for client "+this.conncount);
	                String keyBoardMsg = keyin.nextLine();
	                out.println(keyBoardMsg);
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
	}