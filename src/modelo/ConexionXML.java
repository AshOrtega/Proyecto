package modelo;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ConexionXML {
	
	//M�todo para obtener el valor de un nodo
	public static String obtenerNodoValor (String strTag, Element persona) {
		Node nValor = (Node)persona.getElementsByTagName(strTag).item(0).getFirstChild();
		return nValor.getNodeValue();
	}
	
	public static Document leerFichero(String fichero) throws ParserConfigurationException, SAXException, IOException {
		//prepara el archivo para obtener los datos
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File(fichero));
		
		doc.getDocumentElement().normalize();
		return doc;
	}
	
	public static void guardarFichero(Document doc) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("db/personas.xml"));
		transformer.transform(source, result);
	}
	
}
