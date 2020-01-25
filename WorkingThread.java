package kdp.pop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class WorkingThread extends Thread{
    
	Socket client;
	HashMap<String,AtomicBroadcast> buffers;
	
	public WorkingThread(Socket client, HashMap<String,AtomicBroadcast> buffers) {
	      this.client=client;
	      this.buffers=buffers;
	}
	public void run() {
		try(ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(client.getInputStream())){
			String name;
			AtomicBroadcast ab;
			Goods g;
			while(!client.isInputShutdown()) {
				String op = (String) in.readObject();
				switch (op == null ? "null" : op.toLowerCase()) {
				case "put":
					name=(String)in.readObject();
					synchronized(buffers) {
						if (!buffers.containsKey(name)) {
							buffers.put(name, new AtomicBroadcast());
						}
					}
				    ab=buffers.get(name);
					g=(Goods)in.readObject();
					ab.setGoods(g);
					out.writeObject("OK");
					break;
				case "get":
					name=(String)in.readObject();
					int id=(int)in.readObject();
					synchronized(buffers) {
						if (!buffers.containsKey(name)) {
							buffers.put(name, new AtomicBroadcast());
						}
					}
					ab=buffers.get(name);
					g=ab.getGoods(id);
					out.writeObject(g);
					break;
				default: break;
			    }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
