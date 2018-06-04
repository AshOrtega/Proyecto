package prueba;

import java.io.IOException;
import java.util.ArrayList;


import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.TransformerException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modelo.ConexionXML;
import modelo.PersonaDTO;



public class PersonaDAOImpXML{
	
	private ArrayList<PersonaDTO> listaPersonas = new ArrayList<>();
	private String[] nombreColumnas;
	private Object[][] datos;
	
	
	public PersonaDAOImpXML (String fichero) {
		listarTodasPersonas(fichero);
	}
	
	public String[] getColumnas() {
		return nombreColumnas;
	}
	
	public Object[][] getContenido() {
		return datos;
	}
	
	public ArrayList<PersonaDTO> listarTodasPersonas(String fichero) {
		// TODO Auto-generated method stub
		ArrayList<PersonaDTO> listaPersonas = new ArrayList<>();
		
		//clases necesarias para validar y leer el documento xml
		try {
			Document doc = ConexionXML.leerFichero(fichero);
			

			//obtiene todos los nodos con la etiqueta persona
			NodeList nodoPersonas = doc.getElementsByTagName("record");
			//por cadsa nodo que obtuvo se obtendr� los datos y se guardan en un objeto tipo persona
			for (int i = 0; i < nodoPersonas.getLength(); i++) {
				Node nodo = nodoPersonas.item(i);
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element)nodo;
					PersonaDTO persona = new PersonaDTO();
					persona.setCodigo(Integer.parseInt(ConexionXML.obtenerNodoValor("id", elemento)));
					persona.setNombre(ConexionXML.obtenerNodoValor("first_name", elemento));
					persona.setApellido(ConexionXML.obtenerNodoValor("last_name", elemento));
					persona.setEdad(Integer.parseInt(ConexionXML.obtenerNodoValor("Age", elemento)));
					
					listaPersonas.add(persona);
					

				}
				
				
				}
			int i = 0;
			datos = new Object[listaPersonas.size()][4];

			for (PersonaDTO persona : listaPersonas) {
				datos[i][0] = (Object) persona.getCodigo();
				datos[i][1] = (Object) persona.getNombre();
				datos[i][2] = (Object) persona.getApellido();
				datos[i][3] = (Object) persona.getEdad();
				i++;
			}
			obtenerCabeceraBaseDatos(fichero);
		} catch (ParserConfigurationException | SAXException |IOException e) {
			e.printStackTrace();
		}
		
		return listaPersonas;
	}
	public void obtenerCabeceraBaseDatos(String fichero) {
		Document doc;
		try {
			doc = ConexionXML.leerFichero(fichero);
			NodeList nodoPersonas = doc.getElementsByTagName("dataset");
			Node nodo = nodoPersonas.item(0);
			Element elemento = (Element)nodo;
			nombreColumnas = new String[elemento.getElementsByTagName("record").item(0).getChildNodes().getLength()];
			for (int i = 0; i < elemento.getElementsByTagName("record").item(0).getChildNodes().getLength(); i++) {
				nombreColumnas[i] = elemento.getElementsByTagName("record").item(0).getChildNodes().item(i).getNodeName();
				
			}
			
		} catch ( IOException | ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean insertarPersona(PersonaDTO persona, String fichero, ArrayList<PersonaDTO> listaPersonas) {
		
		// TODO Auto-generated method stub
		int listaOriginal = listaPersonas.size();
		try {
			Document doc = ConexionXML.leerFichero(fichero);

		
		//Obtenemos el nodo padre (dataset)
		Node nodoRaiz = doc.getDocumentElement();
		
		//Y a continuaci�n creamos la nueva persona junto a las etiquetas hijas (nombre, apellido, edad)
		Element nuevaPersona =doc.createElement("record");
		Element nuevoCodigo = doc.createElement("id");
		nuevoCodigo.setTextContent("" + (listaPersonas.size() + 1));
		nuevaPersona.appendChild(nuevoCodigo);

		Element nuevoNombre = doc.createElement("first_name");
		nuevoNombre.setTextContent(persona.getNombre());
		nuevaPersona.appendChild(nuevoNombre);

		Element nuevoApellido = doc.createElement("last_name");
		nuevoApellido.setTextContent(persona.getApellido());
		nuevaPersona.appendChild(nuevoApellido);

		Element nuevaEdad = doc.createElement("Age");
		nuevaEdad.setTextContent("" + persona.getEdad());
		nuevaPersona.appendChild(nuevaEdad);
		
		//Relacionamos todas la etiquetas hijas con la etiqueta padre (record)
		nodoRaiz.appendChild(nuevaPersona);
		
		ConexionXML.guardarFichero(doc);
		listaPersonas = listarTodasPersonas(fichero);
		
		} catch (ParserConfigurationException | SAXException |IOException | TransformerException e) {
			e.printStackTrace();
		}
		return listaOriginal != listaPersonas.size();	
		}
	
	public boolean borrarPersona(String nombre, String apellido, String fichero) {
		// TODO Auto-generated method stub
		int listaOriginal = listaPersonas.size();
		try {
			Document doc = ConexionXML.leerFichero(fichero);
			NodeList nodoPersonas = doc.getElementsByTagName("record");
			
			//Recorremos la lista
			for (int i = 0; i < nodoPersonas.getLength(); i++) {
				Element persona = (Element)nodoPersonas.item(i);
				
				//Comprobamos si el elemento coincide
				if (persona.getElementsByTagName("first_name").item(0).getTextContent().equals(nombre) 
						&& persona.getElementsByTagName("last_name").item(0).getTextContent().equals(apellido)) {
					
					//Si coincide lo eliminamos
					persona.getParentNode().removeChild(persona);
				}
			}
			//Y guardamos
			ConexionXML.guardarFichero(doc);
			
			//Actualizamos la lista de personas
			listaPersonas = listarTodasPersonas(fichero);
		} catch (ParserConfigurationException | SAXException |IOException | TransformerException e) {
			e.printStackTrace();
		}
			return listaOriginal != listaPersonas.size();
			
				}

	public boolean actualizarNombrePersona(int codigo, String nombre, String fichero) {
		// TODO Auto-generated method stub
		String nombreOriginal = "a";
		String nuevoNombre = "a";
		try {
			Document doc = ConexionXML.leerFichero(fichero);
			
			NodeList nodoPersonas = doc.getElementsByTagName("record");
			
			for (int i = 0; i < nodoPersonas.getLength(); i++) {
				Element persona = (Element)nodoPersonas.item(i);
				
				if (persona.getElementsByTagName("id").item(0).getTextContent().equals(String.valueOf(codigo))) {
					nombreOriginal = persona.getElementsByTagName("first_name").item(0).getTextContent();
					persona.getElementsByTagName("first_name").item(0).setTextContent(nombre);
					nuevoNombre = persona.getElementsByTagName("first_name").item(0).getTextContent();
				}
			}
			ConexionXML.guardarFichero(doc);
		} catch( ParserConfigurationException | SAXException | IOException | TransformerException e) {
			
		}
		
		
		return !nombreOriginal.equals(nuevoNombre);	
	}
	
	public boolean actualizarApellidoPersona(int codigo, String apellido, String fichero) {
		// TODO Auto-generated method stub
		String apellidoOriginal = "a";
		String nuevoApellido = "a";
		try {
			Document doc = ConexionXML.leerFichero(fichero);
			
			NodeList nodoPersonas = doc.getElementsByTagName("record");
			
			for (int i = 0; i < nodoPersonas.getLength(); i++) {
				Element persona = (Element)nodoPersonas.item(i);
				
				if (persona.getElementsByTagName("id").item(0).getTextContent().equals(String.valueOf(codigo))) {
					apellidoOriginal = persona.getElementsByTagName("last_name").item(0).getTextContent();
					persona.getElementsByTagName("last_name").item(0).setTextContent(apellido);
					nuevoApellido = persona.getElementsByTagName("last_name").item(0).getTextContent();
				}
			}
			ConexionXML.guardarFichero(doc);
		} catch( ParserConfigurationException | SAXException | IOException | TransformerException e) {
			
		}
		
		
		return !apellidoOriginal.equals(nuevoApellido);	
	}
	
	
	public boolean actualizarEdadPersona(int codigo, int edad, String fichero) {
		// TODO Auto-generated method stub
		String edadOriginal = "a";
		String nuevaEdad = "a";
		try {
			Document doc = ConexionXML.leerFichero(fichero);
			
			NodeList nodoPersonas = doc.getElementsByTagName("record");
			
			for (int i = 0; i < nodoPersonas.getLength(); i++) {
				Element persona = (Element)nodoPersonas.item(i);
				
				if (persona.getElementsByTagName("id").item(0).getTextContent().equals(String.valueOf(codigo))) {
					edadOriginal = persona.getElementsByTagName("Age").item(0).getTextContent();
					persona.getElementsByTagName("Age").item(0).setTextContent(String.valueOf(edad));
					nuevaEdad = persona.getElementsByTagName("Age").item(0).getTextContent();
				}
			}
			ConexionXML.guardarFichero(doc);
		} catch( ParserConfigurationException | SAXException | IOException | TransformerException e) {
			
		}
		
		
		return !edadOriginal.equals(nuevaEdad);	
	}


	

}
