package kdp.pop;

public class Producer {
	private static int ID = 0;
	String[] productNames;
	Remote rem;
	int id;

	public Producer(String[] productNames, String host, int port) {
		id = ID++;
		this.productNames = productNames.clone();
		rem = new Remote(host, port, id);
	}

	public void work() {
		Goods g;
		for (int i = 0; i < productNames.length; i++) {
			g = new GoodsImpl();
			g.setName(productNames[i]);
			g.setContent("Written by producer " + id);
			System.out.println("Producer: " + id + " is sending file with name " + productNames[i]);
			rem.put(productNames[i], g);
			try {
				Thread.sleep(10000 + (long) Math.random() * 5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		String[] productNames = new String[args.length - 2];
		for (int i = 0; i < productNames.length; i++)
			productNames[i] = args[i + 2];
		Producer p = new Producer(productNames, host, port);
		p.work();
	}
}
