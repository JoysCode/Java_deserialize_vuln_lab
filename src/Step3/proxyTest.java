package Step3;

public class proxyTest{
	public static void main(String[] args) {
		Subject sub=new ProxySubject();
		sub.request();
	}
}


abstract class Subject
{//�����ɫ��ͨ���ӿڻ������������ʵ��ɫʵ�ֵ�ҵ�񷽷���
    abstract void request();
}

class RealSubject extends Subject
{//��ʵ��ɫ��ʵ�ֳ����ɫ��������ʵ��ɫ��Ҫʵ�ֵ�ҵ���߼����������ɫ���á�
       public RealSubject()
       {
       }
      
       public void request()
       {
              System.out.println("From real subject.");
       }
}

class ProxySubject extends Subject
{//�����ɫ��ʵ�ֳ����ɫ������ʵ��ɫ�Ĵ���ͨ����ʵ��ɫ��ҵ���߼�������ʵ�ֳ��󷽷��������Ը����Լ��Ĳ�����
    private RealSubject realSubject; //����ʵ��ɫ��Ϊ�����ɫ������
      
       public ProxySubject()
       {
       }
 
       public void request() //�÷�����װ����ʵ�����request����
       {
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
    }
 
    private void postRequest()
    {
        //something you want to do after requesting
    }
}
