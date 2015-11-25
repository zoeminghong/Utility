package com.cheguo.credit.utils.py;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.util.StringUtils;

import com.cheguo.credit.utils.xml.XmlBase;

public class XmlParseUtil {

	/**
	 * 定位到xml文档的xpath级别
	 * 调用格式getXmlNode(DocumentHelper.parseText(xml),"//sss/item/age")
	 * @param doc
	 * @param xpath
	 * @return
	 */
	public static Node getXmlNode(Document doc, String xpath) {
		if (doc != null) {
			if (doc.selectSingleNode(xpath) != null) {
				return doc.selectSingleNode(xpath);
			}
		}
		return null;
	}

	/**
	 * 返回替换好的 定位到xml文档的xpath级别
	 *  调用格式getXmlNode(Dxml,"//sss/item/age")
	 * @param doc
	 * @param xpath
	 * @return
	 */
	public static Object getXmlNode(String xml, String xpath, Class<?> cls, String... jss) {
		Document doc;
		String strXml = null;
		try {
			doc = DocumentHelper.parseText(xml);
			if (doc != null) {
				if (doc.selectSingleNode(xpath) != null) {
					strXml = doc.selectSingleNode(xpath).asXML();
				}
			}
			if (jss != null && jss.length >= 2 && StringUtils.hasLength(strXml)) {
				return XmlBase.xmlToBean(cls, strXml.replace(jss[0], jss[1]));
			} else if (StringUtils.hasLength(strXml)) {
				return XmlBase.xmlToBean(cls, strXml);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取多个相同节点列表数据
	 * @param xml xml文件
	 * @param xpath 定位路径
	 * @param list 返回的列表对象
	 * @param cls	转换Bean类
	 * @param jss 替换参数，第一个是old，第二个是new
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void getXmlNode(String xml, String xpath,List list, Class<?> cls, String... jss) {
		Document doc;
		String strXml = null;
		try {
			doc = DocumentHelper.parseText(xml);
			if (doc != null) {				
				if (doc.selectNodes(xpath) != null) {
					List<Node> lst= doc.selectNodes(xpath);
					for(Node l:lst){
						strXml=l.asXML();
						if (jss != null && jss.length >= 2 && StringUtils.hasLength(strXml)) {
							list.add(XmlBase.xmlToBean(cls, strXml.replace(jss[0], jss[1])));
						}else{
							list.add(XmlBase.xmlToBean(cls, strXml));
						}
					}
				}

			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 定位到xml同一级别的结点
	 * 
	 * @param doc
	 * @param xpath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Node> getXmlNodeList(Document doc, String xpath) {
		if (doc != null) {
			if (doc.selectNodes(xpath) != null) {
				return doc.selectNodes(xpath);
			}
		}
		return null;
	}
}
