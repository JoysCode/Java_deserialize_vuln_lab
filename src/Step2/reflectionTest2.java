package Step2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

/*
 * ���˷��䷽���Ļ������ٽ��step1��ʵ��һ�����ڷ��䷽���ĵ���������
 */

public class reflectionTest2 implements Serializable{
	private Integer n;  
    
    public reflectionTest2(Integer n){ //���캯������ʼ��ʱִ��
    	this.n = n;
    }
    public String int2string(Integer n) {
    	System.out.println("here");
    	return Integer.toString(n);
    }
    private void readObject(java.io.ObjectInputStream in) throws IOException,ClassNotFoundException{
    	in.defaultReadObject();//����ԭʼ��readOject����
    	
    	try {//ͨ�����䷽��ִ�����
    	Method method= java.lang.Runtime.class.getMethod("exec", String.class);
    	Object result = method.invoke(Runtime.getRuntime(), "calc.exe");    
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	}
	
	public static void main(String[] args){
		//reflectionTest2 x= new reflectionTest2(2);
		//operation.ser(x);
		operation.deser();
	}
}



class operation {
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