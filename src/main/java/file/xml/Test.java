package file.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author hq7733
 * @date 2023/2/1
 */
public class Test {

    public static Document load(String filename) {
        Document doc = null;
        try {
            SAXReader reader = new SAXReader();
            doc = reader.read(new File(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void main(String[] args) {
        Document document = load("xml-test.xml");
        // 获取根节点
        read(document);
        System.out.println(15);
    }

    public static void read(Document doc) {
        //获取doc对象的字节点
        Element element = doc.getRootElement();
        //迭代器
        Iterator<?> iterator = element.elementIterator();
        ArrayList<Object> bookslist = new ArrayList<Object>();
        while (iterator.hasNext()) {
            //获取字节点
            Element element1 = (Element) iterator.next();

            Iterator<?> iterator1 = element1.elementIterator();
            while (iterator1.hasNext()) {
                Element element2 = (Element) iterator1.next();
                bookslist.add(element2);
            }

        }
        //遍历
        for (Object ob : bookslist) {
            System.out.println(ob);
        }
    }
}
