package fanxing;

/**
 * 最大最小信息的泛型类
 * */
public class Pair<T> {
	private T min;
	private T max;
	
	public Pair() {		
		this.min = null;
		this.max = null;
	}
	
	public Pair(T min, T max) {		
		this.min = min;
		this.max = max;
	}

	public T getMin() {
		return min;
	}

	public void setMin(T min) {
		this.min = min;
	}

	public T getMax() {
		return max;
	}

	public void setMax(T max) {
		this.max = max;
	}
	
	
	
}
