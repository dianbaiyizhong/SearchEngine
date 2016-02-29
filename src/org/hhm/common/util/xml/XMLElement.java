package org.hhm.common.util.xml;

import java.io.File;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLElement {

	private String path;

	public XMLElement(String path) {
		this.path = path;
	}

	public Element get() {
		File file = new File(path);
		SAXReader reader = new SAXReader();
		Element root = null;

		try {
			Document doc = reader.read(file);
			root = doc.getRootElement();
			return root;
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Document set() {

		File file = new File(path);
		SAXReader reader = new SAXReader();
		Element root = null;

		try {
			Document doc = reader.read(file);
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return null;

	}

	

}
