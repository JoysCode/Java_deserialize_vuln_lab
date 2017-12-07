package Step3;

abstract public class proxyTestSubject
{//�����ɫ��ͨ���ӿڻ������������ʵ��ɫʵ�ֵ�ҵ�񷽷���
    abstract public void request();
}

class RealSubject extends proxyTestSubject
{//��ʵ��ɫ��ʵ�ֳ����ɫ��������ʵ��ɫ��Ҫʵ�ֵ�ҵ���߼����������ɫ���á�
       public RealSubject()
       {
       }
      
       public void request()
       {
              System.out.println("From real subject.");
       }
}

class ProxySubject extends proxyTestSubject
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
�ͻ��˵��ã�
Subject sub=new ProxySubject();
Sub.request();