package com.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class CreateXml{

	public static void main(String[] args) {
		creatxml();

	}
	public static void creatxml(){
		DocumentBuilderFactory fbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = fbf.newDocumentBuilder();
			Document document=db.newDocument();
			Element conditions= document.createElement("conditions");
			Element item=document.createElement("item");
			Element name=document.createElement("name");
			//设置值
			name.setTextContent("ssdasda");
			item.appendChild(name);
			conditions.appendChild(item);
			document.appendChild(conditions);
			TransformerFactory tff=TransformerFactory.newInstance();
			Transformer tf=tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.ENCODING, "GBK");
			//输出方式，StreamResult有很多中方式，当前是以一个文件类型，还可以字符流类型
			tf.transform(new DOMSource(document), new StreamResult("../conditions.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
