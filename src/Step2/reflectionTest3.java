package Step2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
/*
 * ����setAccessible����������ͨ����������Ϊtrue--setAccessible(true) ������private���Ժͺ�����
 * ���ҿ�����߳����ִ��Ч�ʣ���Ϊ�����˰�ȫ��顣
 */
public class reflectionTest3 {
	
	public static void main(String[] args){
		try {
			String path = "Step2.User3"; 
			Class clazz = Class.forName(path);
			
			//Method method = clazz.getMethod("setName",String.class);
			//getMethodֻ�ܻ�ȡpublic�ķ�����private�ķ�����Ҫʹ��getDeclaredMethod����ȡ����������setAccessible(true)�ſ��Ե��÷��ʡ�
			//��������Ҳ��һ����
			Method method = clazz.getDeclaredMethod("setName", String.class);
			method.setAccessible(true);
			
			//Constructor strut = clazz.getConstructor(String.class,Integer.class);
			//getConstructorֻ�ܻ�ȡpublic�Ĺ��췽��
			Constructor strut = clazz.getDeclaredConstructor(String.class,Integer.class);
			strut.setAccessible(true);
			User3 user = (User3)strut.newInstance("bit4",19);
			//�����Զ��幹�����ķ���
			Object x = method.invoke(user,"����");//��һ����������Ķ��󡣵ڶ������Ǻ����Ĳ���
			System.out.println(user.getName());
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

class User3{
	
	private Integer age;
	private String name;
	
	private User3() {}
    
	private User3(String name,Integer age){ //���캯������ʼ��ʱִ��
    	this.age = age;
    	this.name = name;
    }
    

	private Integer getAge() {
		return age;
	}

	private void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}
	
	private void setName(String name) {
    	this.name = name;
    }
}