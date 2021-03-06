package spring.practice;

import org.springframework.stereotype.Component;

/**铁斧头
 * 
 * @author 50448
 *
 */

public class SteelAxe implements Axe {
	
	private String name;
	private int speed;
	
	public SteelAxe(){
		this.name=this.getClass().getName();
	}
	
	public void setSpeed(int speed){
		this.speed=speed;
	}
	
	public String getName() {
		return this.name;
	}

	public int chop() {
		return this.speed;
	}

}
