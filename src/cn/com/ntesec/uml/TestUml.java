package cn.com.ntesec.uml;

import java.lang.reflect.Method;

public class TestUml {

	public static void main(String[] args) {
		Method[] methods = TestUml.class.getMethods();
		for(Method method : methods){
			System.out.println(method.getName()+":"+method.getParameterTypes().length);
		}
	}

	
	public void test(String s1,String s2){
		
	}
}
