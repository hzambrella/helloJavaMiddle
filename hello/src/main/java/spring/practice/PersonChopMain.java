package spring.practice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class PersonChopMain {
	public static void main(String[] args){
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resource/spring.xml");
		Chinese chinese=(Chinese) ctx.getBean("Chinese");
		System.out.println("speed of chinese when chop: "+chinese.useAxe());
	}
}
