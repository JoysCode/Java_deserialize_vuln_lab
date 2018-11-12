package Step3;

/*
 * ����ģʽ�ļ�demo����̬����
 * 
 * �����ʹ�ó�����ĳ����Ա��ְ��˾������һ����Ŀ������Դ�뷢��ĳЩ�ط�������ǿ��������ĳЩ����ִ��ǰӦ�ô�ӡ��־����
 * �����ֱ����ԭʼ����Ļ�����ֱ���޸����׳������������ǣ��Լ�ʵ��һ���࣬��ԭʼ��ʵ����ͬ�Ľӿڣ����߼̳���ͬ���ࣩ��
 * ͨ���ڷ����������ϳ���ķ�����ʵ���Լ��ķ������Ӷ�ʵ����ǿ������Ŀ�ġ�
 */

public class proxyTest{
	public static void main(String[] args) {
		//Subject sub = new RealSubject();//�����еþɴ��룬�ϳ���Աд�ġ�
		Subject sub = new ProxySubject();//����ְ�ĳ���Ա���Լ�ʵ����ProxySubject�࣬Ȼ��ĳ�����䡣����ǿ�ϳ���Ĵ��롣
		sub.request();
	}
}

abstract class Subject//Ҳ�����ǽӿ�interface
{//�����ɫ��ͨ���ӿڻ������������ʵ��ɫʵ�ֵ�ҵ�񷽷���
	//��������������http������֧��httpЭ��
    abstract void request();
}

//�ϳ���Աд�Ĵ��룬ʵ������Ҫ����Ҫ���ܡ�
class RealSubject extends Subject
{//��ʵ��ɫ��ʵ�ֳ����ɫ��������ʵ��ɫ��Ҫʵ�ֵ�ҵ���߼����������ɫ���á�
	//�����ʵ��http����
       public RealSubject()//Ĭ�Ϲ��췽��
       {
       }
       
       @Override
       public void request()
       {
              System.out.println("From real subject.");
       }
}

//����ְ����Աʵ�ֵ��࣬Ŀ������ǿ�ϳ���Ա�Ĵ��롣
class ProxySubject extends Subject//�ؼ�����ļ̳С�
{//�����ɫ��ʵ�ֳ����ɫ������ʵ��ɫ�Ĵ���ͨ����ʵ��ɫ��ҵ���߼�������ʵ�ֳ��󷽷��������Ը����Լ��Ĳ�����
	//���ͨ��������http�����������Ȼ���Զ�http���������κ���Ҫ���޸ġ�
    private RealSubject realSubject; //����ʵ��ɫ��Ϊ�����ɫ������
      
       public ProxySubject()
       {
       }
       
       @Override
       public void request() //�÷�����װ����ʵ�����request�������ϳ���Ա�ķ�����
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
        //������ǰ��ĳЩ���������ӡ��־���޸�������ȵ�
    	System.out.println("Do something before requesting: print log,change request");
    }
 
    private void postRequest()
    {
        //���������ĳЩ������ӡ��־
    	System.out.println("Do something after requesting: print log");
    }
}
