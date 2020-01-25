package kdp.pop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Server {
    public static void main(String[] args) {
    	HashMap<String,AtomicBroadcast> buffers=new HashMap<>();
    	try(ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));)
		{
			System.out.println(String.format("Server started on port %s", args[0]));
			while(true) {
				Socket client = server.accept();
				
				new WorkingThread(client, buffers).start();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
