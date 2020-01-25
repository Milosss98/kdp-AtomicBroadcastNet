package kdp.pop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Remote {
	private String host;
	private int port;
	private int id;
	private AB ab;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	public Remote(String host, int port, int id) {
		this.host = host;
		this.port = port;
		this.id = id;
		try {
			socket = new Socket(host, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public void finalize() {
    	try {
			out.close();
			in.close();socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void put(String string, Goods g) {
           try {
			out.writeObject("put");
			out.writeObject(string);
			out.writeObject(g);
			out.flush();
			
			in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
           
	}

	public Goods get(String string) {
		try {
		out.writeObject("get");
		out.writeObject(string);
		out.writeObject(id);
		
		Goods g=(Goods)in.readObject();
		return g;
	}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}


