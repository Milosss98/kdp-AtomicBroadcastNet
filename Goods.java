package kdp.pop;

import java.io.Serializable;

public interface Goods extends Serializable{
	public String getName();

	public void setName(String name);

	public String getContent();

	public void setContent(String content);
}
