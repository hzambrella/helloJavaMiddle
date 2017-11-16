package proxy;

/**被代理类
 * 
 * @author 50448
 *
 */
public class TargetBusiness implements TargetInterface{

	/** 业务1接口定义
	 * 
	 */
	public void business() {
		System.out.println("doing  Business");
		
	}
	/**业务2
	 * 
	 */
	public void business2() {
		System.out.println("doing  Business2");
	}
	
	 /** 业务3
	  * 
	  */
	public void business3() {
		System.out.println("doing  Business3");
	}
	
	public void business4(){
		System.out.println("doing  Business4");
	}

}
