package kdp.pop;

public class Consumer {
	private static int ID = 0;
	String[] productNames;
	Remote rem;
	int id;

	public Consumer(String[] productNames, String host, int port) {
		id = ID++;
		this.productNames = productNames.clone();
		rem = new Remote(host, port, id);
	}

	

	public void work() {
		Goods g;
		System.out.println("Consumer " + id + " is starting reading from files:");
		for (int i = 0; i < productNames.length; i++)
			System.out.println(productNames[i]);
		for (int i = 0; i < productNames.length; i++) {
			g = rem.get(productNames[i]);
			String content = g.getContent(); // do something
			System.out.println("Consumer: " + id + " is reading file with name " + g.getName());
			System.out.println(content);
			try {
				Thread.sleep(5000 + (long) Math.random() * 5000);
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
		Consumer c = new Consumer(productNames, host, port);
		Consumer c1 = new Consumer(productNames, host, port);
		c.work();c1.work();
	}
}

