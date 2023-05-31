package com.frame;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;

public class Frame  extends JFrame {

    JPanel window;
    GridBagConstraints constraints = new GridBagConstraints();

    private static String getTag(Element e, String tag){
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }

    private static String getAttribute(Element e, String attr){
        return e.getAttributes().getNamedItem(attr).getNodeValue();
    }
    private static JPanel XMLreader(String filePath){
        JPanel panel = new JPanel();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filePath));
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            switch(getAttribute(doc.getDocumentElement(), "layout")){
                case "GridBag":
                    panel.setLayout(new GridBagLayout());
                    break;
                case "Border":
                    panel.setLayout(new BorderLayout());
                    break;
                default:
                    panel.setLayout(new FlowLayout());
            }

            NodeList nodeList = doc.getElementsByTagName("object");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
//                    System.out.println("button text: " + element.getElementsByTagName("text").item(0).getTextContent());
//                    System.out.println(element.getAttributes().getNamedItem("class").getNodeValue());
                    switch (getAttribute(element, "class")) {
                        case "button":
                            JButton button = new JButton(getTag(element, "text"));
                            panel.add(button);
                            break;
                        case "label":
                            JLabel label = new JLabel(getTag(element, "text"));
                            panel.add(label);
                            break;
                        case "textField":
                            JTextField textField = new JTextField(getTag(element, "text"));
                            panel.add(textField);
                            break;
                        default:
                            System.out.println("default");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return panel;
    }
    public Frame() {
        window = new JPanel(new BorderLayout());

        window.add(XMLreader("C:\\Users\\Emanuele\\IdeaProjects\\Linkedout\\src\\com\\frame\\pages\\MasterXML.xml"));
        this.add(window);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("LinkedOut");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setMinimumSize(this.getMinimumSize());
        this.setVisible(true);
    }
}
