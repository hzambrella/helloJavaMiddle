package hzmaven.hello;

public class Calculator {
	private static int result;

	public void add(int n) {
		result = result + n;
	}
	
	public String sayHello(){
		return "hello";
	}
	
	public void substract(int n) {
		//result = result - 1; // 错误：应该是"result = result - n"
		result=result-n;
	}

	// 还没实现
	public void multiply(int n) {
		//现在实现
		result=result*n;
	}

	public void divide(int n) {
		result = result / n;
	}

	public void square(int n) {
		result = n * n;
	}

	public void squareRoot(int n) {
		for (;;)
			;// 错误：无限循环
	}

	// 清除结果
	public void clear() {
		result = 0;
	}

	// 打开屏幕，显示"hello"，并报警
	public void switchOn() {
		result = 0;
		// 实现其它的计算器功能
	}

	// 显示"bye bye"，报警，并关闭屏幕
	public void switchOff() {
	}

	public int getResult() {
		return result;
	}
}
