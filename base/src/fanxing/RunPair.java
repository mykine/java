package fanxing;

public class RunPair {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] names = {"James","Anny","Lucy","Bobby"};
		Pair<String> result = ArrayAlg.minmax(names);
		System.out.println("min="+result.getMin()+",max="+result.getMax());
		
		Integer[] nums = {7,2,1,9,6};
		Pair<Integer> res = ArrayAlg.minmax(nums);
		System.out.println("min="+res.getMin()+",max="+res.getMax());
	}

}
