package kdp.pop;

public class GoodsImpl implements Goods {

	String name;
	String content;
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
         this.name=name;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content=content;
	}

}
