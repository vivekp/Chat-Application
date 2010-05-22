
import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {

        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
	
	String otherId="other";
	String myId="other";
        String server=null;

        try {

	  if(args.length==3) {
            server=args[0];
            kkSocket = new Socket(server, 4444);
	    otherId=args[2];
	    myId=args[1];
	  }
	  else {
		  System.err.println("Invalid syntax: To be used like this: \n java ChatClient [host] [my-name] [host-name]");
		  System.exit(1);
	  }

            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: "+server);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+server);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

	System.out.println("Waiting for response from server....");

        while ((fromServer = in.readLine()) != null) {
            System.out.println(otherId+": "+ fromServer);
            if (fromServer.equals("bye"))
                break;
	
	    System.out.print("me: ");    
            fromUser = stdIn.readLine();
	    if (fromUser != null) {
              //  System.out.println("Client: " + fromUser);
                out.println(myId+": "+fromUser);
	    }
        }

        out.close();
        in.close();
        stdIn.close();
        kkSocket.close();
    }
}

