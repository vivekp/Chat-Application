
import java.net.*;
import java.io.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(7777);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 7777.");
            System.exit(1);
        }

	System.out.println("Listening on port 7777 on "+InetAddress.getLocalHost());
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
				new InputStreamReader(
				clientSocket.getInputStream()));
     
       
        BufferedReader stdInp = new BufferedReader(new InputStreamReader(System.in));
	String inputLine;

	String outputLine;

//        System.out.print("me: ");
       // outputLine=stdInp.readLine();
        outputLine="hii...This is an automatic reply from server. Enjoy chatting...";
       
        out.println(outputLine);
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
	    
	    System.out.print("me: ");
	    outputLine=stdInp.readLine();
           
	    out.println(outputLine);
           
	    if (outputLine.equals("bye"))
                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

