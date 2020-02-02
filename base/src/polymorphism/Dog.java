package polymorphism;

public class Dog extends Pet implements IDogTraining,IPlay {
	
	public Dog()
	{
		
	}
	
	public Dog(String name) 
	{		
		super(name);
	}

	//重写了父类的eat方法
	public void eat(String food)
	{
		System.out.println(super.getName()+"狗狗 吃 "+food);
	}
	
	//狗汪汪
	public void wangwang() {
		System.out.println( super.getName()+": 汪汪!!! " );
	}

	@Override
	public void bathe() {
		// TODO Auto-generated method stub
		System.out.println( super.getName()+": 水里洗澡澡!!! " );
	}

	@Override
	public void vaccination() {
		// TODO Auto-generated method stub
		System.out.println( super.getName()+": 打狂犬疫苗!!! " );
	}

	@Override
	public void birthControl() {
		// TODO Auto-generated method stub
		System.out.println( super.getName()+": 狗狗节育手术!!! " );
	}

	@Override
	public void guideBlind() {
		// TODO Auto-generated method stub
		System.out.println( super.getName()+": 导盲!!! " );
	}

	@Override
	public void antiDrug() {
		// TODO Auto-generated method stub
		System.out.println( super.getName()+": 缉毒!!! " );
	}

	@Override
	public void keepDoor() {
		// TODO Auto-generated method stub
		System.out.println( super.getName()+": 看门!!! " );
	}

	@Override
	public void actCute() {
		// TODO Auto-generated method stub
		System.out.println( super.getName()+": 狗狗卖萌!!! " );
	}

	@Override
	public void actRun() {
		// TODO Auto-generated method stub
		System.out.println( super.getName()+": 狗狗跳跳!!! " );
	}
	
}
