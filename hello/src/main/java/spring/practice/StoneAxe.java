package spring.practice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**石头斧头
 * 
 * @author 50448
 *
 */

@Component("stoneAxe")
public class StoneAxe implements Axe {
	
	private String name;
	@Value(value="2")
	private int speed;
	
	public StoneAxe(){
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
