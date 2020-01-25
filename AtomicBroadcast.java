package kdp.pop;

import java.util.*;
import java.util.concurrent.locks.*;

public class AtomicBroadcast implements AB{
    private static int N=5;
	int size;
    HashMap<Integer,Integer> consumers=new HashMap<>();
    int tail;
    int numC;
    int head;
    int[] cnt=new int[N];
    {
    	for(int i=0;i<N;i++)cnt[i]=0;
    }
    Goods[] niz=new Goods[N];
    synchronized public void addConsumer(int id) {
    	numC++;
    	consumers.put(id,head);
    }
	@Override
	synchronized public Goods getGoods(int id) {
		try {
			if(!consumers.containsKey(id)) {
				addConsumer(id);
			}
			int h=consumers.get(id);
			while(niz[h]==null)wait();
			Goods g=niz[h];
			h=(h+1)%N;
			consumers.replace(id, h);
			cnt[h]++;
			if (cnt[h]==numC) {
				size--;head++;cnt[h]=0;
				notifyAll();
			}
			return g;
		}
		catch(Exception e) { }
		return null;
		  
	}

	@Override
	synchronized public void setGoods(Goods goods) {
		try {
			while(size==N)
				wait();
			niz[tail]=goods;
			tail=(tail+1)%N;
		    size++;	
		    notifyAll();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int getSize(String name) {
		return N;
	}

	
      
}
