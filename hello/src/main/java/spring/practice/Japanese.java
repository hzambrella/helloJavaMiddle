package spring.practice;

/**日本人
 * 
 * @author 50448
 *
 */
public class Japanese implements Person {
	//砍柴速度
	private int speed;
	private Axe axe;

	public void setSpeed(int speed){
		this.speed=speed;
	}
	
	public void setAxe(Axe axe){
		this.axe=axe;
	}
	
	public int useAxe() {
		return axe.chop()+this.speed;
	}

}
