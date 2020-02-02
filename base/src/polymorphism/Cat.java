package polymorphism;

public class Cat extends Pet implements IPlay {

	public Cat(String name)
	{
		super(name);
	}
	
	@Override
	public void eat(String food) {
		// TODO Auto-generated method stub		
		System.out.println( " 喵喵~ " + super.getName() + " 吃 " + food );
	}
	
	public void miaomiao() {
		System.out.println(  super.getName() + ": 喵喵~ " );
	}

	@Override
	public void bathe() {
		// TODO Auto-generated method stub
		System.out.println(  super.getName() + ": 沙粒洗澡澡~ " );
	}

	@Override
	public void vaccination() {
		// TODO Auto-generated method stub
		System.out.println(  super.getName() + ": 打喵喵疫苗~ " );
	}

	@Override
	public void birthControl() {
		// TODO Auto-generated method stub
		System.out.println(  super.getName() + ": 喵喵节育~ " );
	}

	@Override
	public void actCute() {
		// TODO Auto-generated method stub
		System.out.println(  super.getName() + ": 喵喵卖萌~ " );
	}

	@Override
	public void actRun() {
		// TODO Auto-generated method stub
		System.out.println(  super.getName() + ": 喵喵跑跑跳~ " );
	}

}
