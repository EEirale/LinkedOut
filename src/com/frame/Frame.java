package com.frame;

import com.utils.StyledButton;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

public class Frame extends JFrame {

    protected static JPanel window;
    protected final static GridBagConstraints constraints = new GridBagConstraints();
    public static Dictionary<String, JTextField> textFields = new Hashtable<>();
    public final static Dictionary<String, StyledButton> buttons = new Hashtable<>();


    private static String getTag(Element e, String tag) {
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }

    private static String getAttribute(Element e, String attr) {
        return e.getAttributes().getNamedItem(attr).getNodeValue();
    }

    private static void setConstraints(Element e) {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = Integer.parseInt(getTag(e, "gridx"));
        constraints.gridy = Integer.parseInt(getTag(e, "gridy"));
        String[] values = getTag(e, "insets").split(",");
        constraints.insets = new Insets(
                Integer.parseInt(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2]),
                Integer.parseInt(values[3])
        );

    }

    private static JPanel XMLreader(String filePath) {
        JPanel panel = new JPanel();

        Integer LAYOUT;
        final Integer FLOWLAYOUT = 0;
        final Integer GRIDBAGLAYOUT = 1;
        final Integer BORDERLAYOUT = 2;
        final Integer GRIDLAYOUT = 3;

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filePath));
            doc.getDocumentElement().normalize();

            switch (getAttribute(doc.getDocumentElement(), "layout")) {
                case "GridBag":
                    panel.setLayout(new GridBagLayout());
                    LAYOUT = GRIDBAGLAYOUT;
                    break;
                case "Border":
                    panel.setLayout(new BorderLayout());
                    LAYOUT = BORDERLAYOUT;
                    break;
                case "Grid":
                    String[] dim = getAttribute(doc.getDocumentElement(), "dimensions").split(":");
                    panel.setLayout(new GridLayout(
                            Integer.parseInt(dim[0]),
                            Integer.parseInt(dim[1])
                    ));
                    LAYOUT = GRIDLAYOUT;
                default:
                    panel.setLayout(new FlowLayout());
                    LAYOUT = FLOWLAYOUT;
            }

            NodeList nodeList = doc.getElementsByTagName("object");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    switch (getAttribute(element, "class")) {
                        case "button":
                            StyledButton button = new StyledButton(getTag(element, "text"), Color.BLUE, Color.WHITE);
                            buttons.put(getAttribute(element, "id"), button);

                            if (LAYOUT == GRIDBAGLAYOUT) {
                                setConstraints(element);
                                panel.add(button, constraints);
                            } else if (LAYOUT == GRIDBAGLAYOUT) {
                                panel.add(button, getTag(element, "position"));
                            } else if (LAYOUT == GRIDLAYOUT) {
                                panel.add(button, Integer.parseInt(getTag(element, "gridx")), Integer.parseInt(getTag(element, "gridy")));
                            } else {
                                panel.add(button);
                            }
                            break;

                        case "label":
                            JLabel label = new JLabel(getTag(element, "text"));

                            if (LAYOUT == GRIDBAGLAYOUT) {
                                setConstraints(element);
                                panel.add(label, constraints);
                            } else if (LAYOUT == GRIDBAGLAYOUT) {
                                panel.add(label, getTag(element, "position"));
                            } else if (LAYOUT == GRIDLAYOUT) {
                                panel.add(label, Integer.parseInt(getTag(element, "gridx")), Integer.parseInt(getTag(element, "gridy")));
                            } else {
                                panel.add(label);
                            }
                            break;

                        case "textField":
                            JTextField textField = new JTextField(getTag(element, "text"));
                            textFields.put(getAttribute(element, "id"), textField);

                            if (LAYOUT == GRIDBAGLAYOUT) {
                                setConstraints(element);
                                panel.add(textField, constraints);
                            } else if (LAYOUT == GRIDBAGLAYOUT) {
                                panel.add(textField, getTag(element, "position"));
                            } else if (LAYOUT == GRIDLAYOUT) {
                                panel.add(textField, Integer.parseInt(getTag(element, "gridx")), Integer.parseInt(getTag(element, "gridy")));
                            } else {
                                panel.add(textField);
                            }
                            break;

                        case "panel":
                            JPanel extra = XMLreader("src/com/frame/pages/" + getTag(element, "href") + ".xml");
                            if (LAYOUT == GRIDBAGLAYOUT) {
                                setConstraints(element);
                                panel.add(extra, constraints);
                            } else if (LAYOUT == GRIDBAGLAYOUT) {
                                panel.add(extra, getTag(element, "position"));
                            } else if (LAYOUT == GRIDLAYOUT) {
                                panel.add(extra, Integer.parseInt(getTag(element, "gridx")), Integer.parseInt(getTag(element, "gridy")));
                            } else {
                                panel.add(extra);
                            }

                        default:
                            System.out.println("default " + getAttribute(element, "id"));
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

        window.add(XMLreader("src/com/frame/pages/MasterXML.xml"));
        this.add(window);
        Actioner.setActionListeners();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("LinkedOut");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setMinimumSize(this.getMinimumSize());
        this.setVisible(true);
    }
}
