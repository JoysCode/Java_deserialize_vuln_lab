package Step2;

import java.lang.reflect.Method;

public class reflectionTest {
	
	public static void main(String[] args){

		Method[] methods = test.class.getMethods();
		//��ȡ��ķ��������е�����python��getattr()��java��ÿ�����Ͷ���class ����
		//ͨ�����class���Ի�ȡ��Ӧ��Class��Ķ���ͨ�����Class��Ķ����ȡtest���еķ�������

		/* String name = test.class.getName();
		 * int modifiers = test.class.getModifiers();
		 * .....���кܶ෽��
		 * Ҳ����˵������һ������Ŀ��Է��ʵ����࣬���Ƕ��ܹ�ͨ��������Щ������֪���������еķ��������ԣ�
		 * ֪�������ķ��������ԣ��Ϳ��Ե�����Щ���������ԡ�
		 */
		
		//����test���еķ���
		for(Method method : methods){
		    if(method.getName().equals("int2string")) {
		    	System.out.println("method = " + method.getName());
		    	
		    	Class[] parameterTypes = method.getParameterTypes();//��ȡ�����Ĳ���
		    	Class returnType = method.getReturnType();//��ȡ�����ķ�������
		    	try {
		    		//method.invoke(test.class.newInstance(), 666);
		    		Object x = method.invoke(new test(1), 666);
		    		System.out.println(x);
		    		// new�ؼ����ܵ����κι��췽���� newInstance()ֻ�ܵ����޲ι��췽����
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}

		try {
			Method method = test.class.getMethod("int2string", Integer.class);
			Object x = method.invoke(new test(2), 666);//��һ����������Ķ��󡣵ڶ������Ǻ����Ĳ���
			System.out.println(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class test{
	private Integer n;  
    
    public test(Integer n){ //���캯������ʼ��ʱִ��
    	this.n = n;
    }
    public String int2string(Integer n) {
    	System.out.println("here");
    	return Integer.toString(n);
    }
}