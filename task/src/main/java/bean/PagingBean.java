package bean;

import java.io.Serializable;

public class PagingBean implements Serializable{
    private static final long serialVersionUID = 1L;

	
	private int limit;
	private int offset;
	
	public PagingBean() {}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
