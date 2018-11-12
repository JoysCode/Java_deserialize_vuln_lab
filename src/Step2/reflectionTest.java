package Step2;

import java.lang.reflect.Method;

public class reflectionTest {
	
	public static void main(String[] args){
		try {

			//Class��ȡ��ķ���һ:ʵ�������getClass()����;
			User testObject = new User("zhangshan",19);
			Class Method1Class = testObject.getClass();
			
			//Class��ȡ��ķ�����:���.class(�ȫ/�������)����;�е�����python��getattr()��java��ÿ�����Ͷ���class ����.
			Class Method2Class = User.class;
			
			//Class����Ļ�ȡ������:����Class.forName(String className)��̬������,className��Ҫ�����ȫ�޶���(���).
			//���ַ���Ҳ��������⣬ͨ������(jar���е�����namespace)�Ϳ��Ե������еķ�����Ҳ�����������Ҫ��ʹ�ó���.
			//j2eeScan burp �����ʹ�������ַ�����ơ�
			String path = "Step2.User"; 
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
			
			//����User���еķ���
			
			for(Method method : methods){
			    if(method.getName().equals("getName")) {
			    	System.out.println("method = " + method.getName());
			    	
			    	Class[] parameterTypes = method.getParameterTypes();//��ȡ�����Ĳ���
			    	Class returnType = method.getReturnType();//��ȡ�����ķ�������
			    	try {
			    		User user = (User)Method3Class.newInstance();
			    		Object x = method.invoke(user);//user.getName();
			    		//Object x = method.invoke(new test(1), 666);
			    		//new�ؼ����ܵ����κι��췽��,newInstance()ֻ�ܵ����޲ι��췽����������ĳ������ǲ�Ӧ���л���ʹ��new�ؼ��ʵġ�
			    		System.out.println(x);
			    		
					} catch (Exception e) {
						e.printStackTrace();
					}
			    }
			}
			
			
			
			Method method = Method3Class.getMethod("setName",String.class);
			User user1 = (User)Method3Class.getConstructor(String.class,Integer.class).newInstance("lisi",19);
			//�����Զ��幹�����ķ���
			Object x = method.invoke(user1,"����");//��һ����������Ķ��󡣵ڶ������Ǻ����Ĳ���
			System.out.println(user1.getName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}

class User{
	private Integer age;
	private String name;
	
    public User() {}
    
    public User(String name,Integer age){ //���캯������ʼ��ʱִ��
    	this.age = age;
    	this.name = name;
    }
    

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}
	
    public void setName(String name) {
    	this.name = name;
    }
}