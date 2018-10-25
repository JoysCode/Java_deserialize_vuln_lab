package Step2;

import java.lang.reflect.Method;

public class reflectionTest {
	
	public static void main(String[] args){
		try {

			//Class��ȡ��ķ���һ:ʵ�������getClass()����;
			test testObject = new test(111);
			Class Method1Class = testObject.getClass();
			
			//Class��ȡ��ķ�����:���.class(�ȫ/�������)����;�е�����python��getattr()��java��ÿ�����Ͷ���class ����.
			Class Method2Class = test.class;
			
			//Class����Ļ�ȡ������:����Class.forName(String className)��̬������,className��Ҫ�����ȫ�޶���(���).
			//���ַ���Ҳ��������⣬ͨ������(jar���е�����namespace)�Ϳ��Ե������еķ�����Ҳ�����������Ҫ��ʹ�ó���.
			//j2eeScan burp �����ʹ�������ַ�����ơ�
			String path = "Step2.test"; 
			Class Method3Class = Class.forName(path);
			
			Method[] methods = Method3Class.getMethods();
			//Method[] methods = Method2Class.getMethods();
			//Method[] methods = Method3Class.getMethods();
			
			//ͨ�����class���Ի�ȡ��Ӧ��Class��Ķ���ͨ�����Class��Ķ����ȡtest���еķ�������

			/* String name = Method3Class.getName();
			 * int modifiers = Method3Class.getModifiers();
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
			
			
			
			Method method = Method3Class.getMethod("int2string", Integer.class);
			Object x = method.invoke(new test(2), 666);//��һ����������Ķ��󡣵ڶ������Ǻ����Ĳ���
			System.out.println(x);
		} catch (Exception e1) {
			e1.printStackTrace();
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