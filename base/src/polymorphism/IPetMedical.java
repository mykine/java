package polymorphism;

/***
 * 接口是规范规约，多继承(实现)
 * 方法都是public abstract的抽象方法
 * 属性都是静态的
 * */
public interface IPetMedical {

	//沐浴
	void bathe();
	
	//打疫苗
	void vaccination();
	
	//节育
	void birthControl();
	
}
