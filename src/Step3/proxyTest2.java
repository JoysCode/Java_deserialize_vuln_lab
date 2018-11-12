package Step3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * ��̬����ļ�demo����̬���������˷������
 * ÿһ����̬�����඼����һ����֮������invocation handler�������ĵ�������invocation handler��invoke()��������ɵġ�
 * ��л����ɡ�����ϲ2λʦ����ָ��
 */

public class proxyTest2{
	public static void main(String[] args) {
		DynamicSubject sub=new RealDynamicSubject();//֮ǰ����sub��������RealDynamicSubject�����ԣ�����Ϊʲô�أ�
		Handler handler = new Handler(sub);
		
		//newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
		//CLassLoader loader:ָ����̬��������������,��������ɺ�Ĵ��������������
		//Class<?> interfaces:ָ����̬��������Ҫʵ�ֵ����нӿڣ���Ҫ����ǿ�Ľӿ��б����ݣ�
		//InvocationHandler h: ָ���붯̬����������� InvocationHandler���󣬾������ǿ�߼�
		
		DynamicSubject sub2 = (DynamicSubject)Proxy.newProxyInstance(DynamicSubject.class.getClassLoader(), new Class[]{DynamicSubject.class}, handler); 

		DynamicSubject sub3 = (DynamicSubject)Proxy.newProxyInstance(DynamicSubject.class.getClassLoader(), sub.getClass().getInterfaces(), handler);
		
		DynamicSubject sub4 = (DynamicSubject)Proxy.newProxyInstance(DynamicSubject.class.getClassLoader(), RealDynamicSubject.class.getInterfaces(), handler);
		
		//������ĵ��÷�����֪�����ԶԲ�ͬ�Ķ���ʹ����ͬ��ģʽʵ����ʵ��������������Ծ�̬��������ơ�
		
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
	private Object obj; //������Ķ���Ҳ�����ϳ���Աʵ�ֵĶ��󣩣����ܶ�����ʲô���ͣ�֮ǰ������RealDynamicSubject����Ӧ����ô��
    /**
     * ���е����̿��ƶ���invoke������
     * proxy��������
     * method�����ڵ��õķ�����������Ƶ��ú��������룡
     * args�������÷����Ĳ����б�������Ƶ��ú��������룡
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//�ӿڱ���ʵ�ֵķ�����Ҳ���߼�����
    	System.out.println("Do something before requesting: print log");
    	Object xxx = method.invoke(this.obj, args);//ͨ��������Ƶ����ϳ���Ա�Ķ�����롣
        System.out.println("Do something after requesting: print log");
        return xxx;
    }
    public Handler(Object obj) {
    	//���캯��������ʵ��ɫ��ʵ�����ݽ���,�������handler��Ŀ�ľ�����ǿ��������˵��Ҫ��������ʵ����Ҫ�Ĺ��ܡ�
        this.obj = obj;
    }
}
