package Step1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class deserTest2 implements Serializable {  
	
    /**
	 * ����һ���򵥵Ŀɱ����л����࣬����ʵ������Ķ�����ǿ��Ա����л��ġ�
	 * Ȼ����дreadObject������ʵ�ֵ���������
	 */
	private static final long serialVersionUID = 1L;
	
	private int n;
    
    public deserTest2(int n){ //���캯������ʼ��ʱִ��
        this.n=n;
    }
    //��дreadObject�����������˵���������ִ�д��������
    private void readObject(java.io.ObjectInputStream in) throws IOException,ClassNotFoundException{
    	in.defaultReadObject();//����ԭʼ��readOject����
    	Runtime.getRuntime().exec("calc.exe");
    	System.out.println("test");
    }
    
    public static void main(String[] args) {
    	//deserTest2 x = new deserTest2(5);//ʵ��һ������
    	//operation2.ser(x);//���л�
    	operation2.deser();//�����л�
    }
}

class operation2 {
	public static void ser(Object obj) {
		//���л�������д����
		try{
	        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.obj"));
	        //ObjectOutputStream�ܰ�Object�����Byte��
	        oos.writeObject(obj);//���л��ؼ�����
	        oos.flush();  //������ 
	        oos.close(); //�ر���
	    } catch (FileNotFoundException e) 
	    {        
	        e.printStackTrace();
	    } catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	public static void deser() {
		//�����л���������ȡ����
		try {
			File file = new File("object.obj");
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(file));
			Object x = ois.readObject();//�����л��Ĺؼ�����
			System.out.print(x);
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
