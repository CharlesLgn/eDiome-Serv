package com.ircserv.utils;

import com.ircserv.metier.Server;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;

public class XMLDataFinder {

  private static String pathUser = "user.xml";
  private static String pathServ = "serv.xml";

  private static NodeList XPathFinder(String url, String expression) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(url);
    XPathFactory xPathfactory = XPathFactory.newInstance();
    XPath xpath = xPathfactory.newXPath();
    XPathExpression expr = xpath.compile(expression);
    return (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
  }

  public static ArrayList<Server> getServByUser(int id) {
    ArrayList<Server> servers = new ArrayList<>();
    try {
      NodeList str = XPathFinder(pathUser, "//user[@id='"+id+"']/servers/serv");
      for (int i = 0; i < str.getLength() ; i++){
        Server serv = getServ(Integer.parseInt(str.item(i).getTextContent()));
        servers.add(serv);
      }
    } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
      e.printStackTrace();
    }
    return servers;
  }

  private static Server getServ(int id) {
    try {
      NodeList str = XPathFinder(pathServ, "//server[@id='"+id+"']/nom");
      String nom = str.item(0).getTextContent();
      return new Server(id, nom);
    } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static int getUserId(String user, String psw){
    try {
      NodeList str = XPathFinder(pathUser, "//user[pseudo='"+user+"' and mdp='"+psw+"']");

      int id;
      id = Integer.parseInt(str.item(0).getAttributes().getNamedItem("id").getTextContent());
      return id;
    } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public static String getUserName(int id){
    try {
      NodeList str = XPathFinder(pathUser, "//user[@id='"+id+"']/pseudo");
      return str.item(0).getTextContent();
    } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static int getNbUser(){
    try {
      NodeList str = XPathFinder(pathUser, "//user[last()]");
      String nb = str.item(0).getAttributes().getNamedItem("id").getTextContent();
      return Integer.parseInt(nb);
    } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public static int addUser(String name, String psw) {
    int id = getNbUser()+1;
    try {

      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse("user.xml");
      Element root = document.getDocumentElement();

      // server elements
      Element newUser = document.createElement("user");
      newUser.setAttribute("id", ""+id);

      Element pseudo = document.createElement("name");
      pseudo.appendChild(document.createTextNode(name));
      newUser.appendChild(pseudo);

      Element mdp = document.createElement("mdp");
      mdp.appendChild(document.createTextNode(psw));
      newUser.appendChild(mdp);

      Element servers = document.createElement("servers");
      newUser.appendChild(servers);

      Element serv = document.createElement("serv");
      serv.appendChild(document.createTextNode("0"));
      servers.appendChild(serv);


      root.appendChild(newUser);

      DOMSource source = new DOMSource(document);

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      StreamResult result = new StreamResult("user.xml");
      transformer.transform(source, result);
    } catch (TransformerException | ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
    }
    return id;
  }
}
