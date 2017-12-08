package Step3;

/*
 * ����ģʽ�ļ�demo����̬����
 */

public class proxyTest{
	public static void main(String[] args) {
		Subject sub=new ProxySubject();
		sub.request();
	}
}

abstract class Subject
{//�����ɫ��ͨ���ӿڻ������������ʵ��ɫʵ�ֵ�ҵ�񷽷���
	//��������������http������֧��httpЭ��
    abstract void request();
}

class RealSubject extends Subject
{//��ʵ��ɫ��ʵ�ֳ����ɫ��������ʵ��ɫ��Ҫʵ�ֵ�ҵ���߼����������ɫ���á�
	//�����ʵ��http����
       public RealSubject()
       {
       }
      
       public void request()
       {
              System.out.println("From real subject.");
       }
}

class ProxySubject extends Subject//�ؼ�����ļ̳�
{//�����ɫ��ʵ�ֳ����ɫ������ʵ��ɫ�Ĵ���ͨ����ʵ��ɫ��ҵ���߼�������ʵ�ֳ��󷽷��������Ը����Լ��Ĳ�����
	//���ͨ��������http�����������Ȼ���Զ�http���������κ���Ҫ���޸ġ�
    private RealSubject realSubject; //����ʵ��ɫ��Ϊ�����ɫ������
      
       public ProxySubject()
       {
       }
 
       public void request() //�÷�����װ����ʵ�����request����
       {//��ν�ġ����ơ�������������
        preRequest(); 
              if( realSubject == null )
        {
                     realSubject = new RealSubject();
              }
        realSubject.request(); //�˴�ִ����ʵ�����request����
        postRequest();
       }
 
    private void preRequest()
    {
        //something you want to do before requesting
    	System.out.println("Do something before requesting");
    }
 
    private void postRequest()
    {
        //something you want to do after requesting
    	System.out.println("Do something after requesting");
    }
}
