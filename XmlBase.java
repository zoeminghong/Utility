package com.cheguo.credit.utils.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.cheguo.credit.utils.py.XmlParseUtil;

public class XmlBase {
	//转为xml形式
	public String asXml() throws Exception {
		JAXBContext context = JAXBContext.newInstance(this.getClass());
		Marshaller marshaller = context.createMarshaller();
		//格式format
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//文档格式类型
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		// 去掉xml头
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		StringWriter writer = new StringWriter();
		marshaller.marshal(this, writer);
		return writer.toString();
	}

	public static String asXml(Object obj) throws Exception {
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		// 去掉xml头
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
		StringWriter writer = new StringWriter();
		marshaller.marshal(obj, writer);
		return writer.toString();
	}

	/**
	 * 将xml文件注入相应的Bean中，获取返回的数据，再类型转换一下就可以了
	 * 
	 * @param cl
	 * @param xml
	 * @return
	 */
	public static Object xmlToBean(Class<?> cl, String xml) {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(cl);
			Unmarshaller unmar = jc.createUnmarshaller();
			return unmar.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取子元素集合，xml格式数据
	 * 
	 * @param elt
	 * @return
	 */
	public static Map<String, String> getElementText(Element elt) {
		Map<String, String> key = new HashMap<String, String>();
		for (@SuppressWarnings("rawtypes")
		Iterator iter = elt.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			key.put(e.getName(), e.asXML());
		}
		return key;
	}

	/**
	 * 获取子元素集合，element格式数据
	 * 
	 * @param elt
	 * @return
	 */
	public static Map<String, Element> getElement(Element elt) {
		Map<String, Element> key = new HashMap<String, Element>();
		for (@SuppressWarnings("rawtypes")
		Iterator iter = elt.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			key.put(e.getName(), e);
		}
		return key;
	}

	/**
	 * 获取根元素
	 * 
	 * @param xml
	 * @return
	 */
	public static Element getRootElement(String xml) {
		Document doc;
		try {
			doc = DocumentHelper.parseText(xml);
			Element rootElt = doc.getRootElement();
			return rootElt;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

}
