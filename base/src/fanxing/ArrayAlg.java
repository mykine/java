package fanxing;

/**
 * 实现比较功能的静态类
 * */
public class ArrayAlg {
	
	/***
	 * 获取数组中最大最小信息对象的泛型方法
	 * 
	 * */
	public static  <T extends Comparable<T>> Pair<T> minmax(T[] arr)
	{
		if( null==arr || 0 == arr.length)
		{
			return null;
		}
		T min = arr[0];
		T max = arr[0];
		for(int i=1;i<arr.length;i++) {
			if(min.compareTo(arr[i])>0) {
				min = arr[i];
			}
			if(max.compareTo(arr[i])<0) {
				max = arr[i];
			}
		}
		return new Pair<T>(min,max);
	}
	
}
