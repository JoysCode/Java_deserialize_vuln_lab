package Step3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * ����ģʽ�ļ�demo����̬������̬���������˷������
 * ÿһ����̬�����඼����һ����֮������invocation handler�������ĵ�������invocation handler��invoke()��������ɵġ�
 * ��л����ɡ�����ϲ2Ϊʦ����ָ��
 */

public class proxyTest2{
	public static void main(String[] args) {
		DynamicSubject sub=new RealDynamicSubject();//֮ǰ����sub��������RealDynamicSubject�����ԣ�����Ϊʲô�أ�
		Handler handler = new Handler(sub);
		DynamicSubject sub2 = (DynamicSubject)Proxy.newProxyInstance(DynamicSubject.class.getClassLoader(), new Class[]{DynamicSubject.class}, handler); 
		//CLassLoader loader:ָ����̬��������������
		//Class<?> interfaces:ָ����̬��������Ҫʵ�ֵ����нӿ�
		//InvocationHandler h: ָ���붯̬����������� InvocationHandler����
		DynamicSubject sub3 = (DynamicSubject)Proxy.newProxyInstance(DynamicSubject.class.getClassLoader(), sub.getClass().getInterfaces(), handler);
		
		DynamicSubject sub4 = (DynamicSubject)Proxy.newProxyInstance(DynamicSubject.class.getClassLoader(), RealDynamicSubject.class.getInterfaces(), handler);
		
		System.out.println("sub.getClass() = "+sub.getClass());
		System.out.println("DynamicSubject.class = " +DynamicSubject.class);
		System.out.println(new Class[]{DynamicSubject.class});
		System.out.println(RealDynamicSubject.class.getInterfaces());

		sub2.request();
		sub3.request();
		sub4.request();
	}
}

interface DynamicSubject
{//�����ɫ��ͨ���ӿڻ������������ʵ��ɫʵ�ֵ�ҵ�񷽷���ע��:��̬����ֻ���ǽӿڣ����������ת�ɸ������»ᱨ��
	//��������������http������֧��httpЭ��
    abstract void request();
}

class RealDynamicSubject implements DynamicSubject
{//��ʵ��ɫ��ʵ�ֳ����ɫ��������ʵ��ɫ��Ҫʵ�ֵ�ҵ���߼���������handler������á�
	//�����ʵ��http����
       public RealDynamicSubject()
       {
       }
      
       public void request()
       {
              System.out.println("From real subject.");
       }
}
 
/**
 * ������
 */
class Handler implements InvocationHandler{
	private Object obj; //������Ķ��󣬲��ܶ�����ʲô���ͣ�֮ǰ������RealDynamicSubject����Ӧ����ô��
    /**
     * ���е����̿��ƶ���invoke������
     * proxy��������
     * method�����ڵ��õķ���
     * args�������Ĳ���
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//�ӿڱ���ʵ�ֵķ�����Ҳ���߼�����
    	System.out.println("Do something before requesting");
    	Object xxx = method.invoke(this.obj, args);
        System.out.println("Do something after requesting");
        return xxx;
    }
    public Handler(Object obj) {
    	//���캯��������ʵ��ɫ��ʵ�����ݽ���,�������handler��Ŀ�ľ��Ǵ�����
        this.obj = obj;
    }
}
