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
import java.util.Objects;

public class Frame extends JFrame {

    private static final String BACKGROUND_COLOR = "background-color";
    private static final String FOREGROUND_COLOR = "foreground-color";
    private static final String ICON = "icon";
    private static final String ICON_DIM = "icon-dim";
    protected static JPanel window;
    public static JPanel centerPanel;
    public static JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    protected final static GridBagConstraints constraints = new GridBagConstraints();
    public final static Dictionary<String, JTextField> textFields = new Hashtable<>();
    public final static Dictionary<String, StyledButton> buttons = new Hashtable<>();
    public final static Dictionary<String, JLabel> labels = new Hashtable<>();

    private static String getTag(Element e, String tag) {
        if(e.getElementsByTagName(tag).item(0) == null)
            return null;
        return Objects.toString(e.getElementsByTagName(tag).item(0).getTextContent(), "");

    }

    private static String getAttribute(Element e, String attr) {
        if(e.getAttributes().getNamedItem(attr) == null)
            return null;
        return Objects.toString(e.getAttributes().getNamedItem(attr).getNodeValue(), "");
    }

    private static void setSize(Element e, Component c){
        String size = getAttribute(e, "size");
        if (size == null)
            return;
        c.setPreferredSize(new Dimension(
                Integer.parseInt(size.split("x")[0]),
                Integer.parseInt(size.split("x")[1])
        ));
    }

    private static void styleComponent(Element e, StyledButton c){
        Dictionary<String, String> attributes = new Hashtable<>();
        attributes.put(BACKGROUND_COLOR, "");
        attributes.put(FOREGROUND_COLOR, "");
        attributes.put(ICON, "");
        attributes.put(ICON_DIM, "");


        String style = getAttribute(e, "style");
        if (style == null)
            return;
        style = style.replaceAll(" ", "");
        String[] pairs = style.split(";");

        for(String pair: pairs){
            String[] split = pair.split(":");
            attributes.put(split[0], split[1]);
        }

        switch (attributes.get(BACKGROUND_COLOR)) {
            case "black" -> c.setBackground(Color.black);
            case "blue" -> c.setBackground(Color.blue);
            case "white" -> c.setBackground(Color.white);
            case "gray" -> c.setBackground(Color.gray);
            case "light-gray" -> c.setBackground(Color.lightGray);
            case "orange" -> c.setBackground(Color.orange);
            case "yellow" -> c.setBackground(Color.yellow);
            case "red" -> c.setBackground(Color.red);
            case "cyan" -> c.setBackground(Color.cyan);
            default -> System.out.println("Default color");
        }

        switch (attributes.get(FOREGROUND_COLOR)) {
            case "black" -> c.setForeground(Color.black);
            case "blue" -> c.setForeground(Color.blue);
            case "white" -> c.setForeground(Color.white);
            case "gray" -> c.setForeground(Color.gray);
            case "light-gray" -> c.setForeground(Color.lightGray);
            case "orange" -> c.setForeground(Color.orange);
            case "yellow" -> c.setForeground(Color.yellow);
            case "red" -> c.setForeground(Color.red);
            case "cyan" -> c.setForeground(Color.cyan);
            default -> System.out.println("Default color");
        }

        if(attributes.get(ICON) != ""){
            ImageIcon icon = new ImageIcon("src/com/frame/media/" + attributes.get(ICON));
            Image image = icon.getImage().getScaledInstance(
                    (attributes.get(ICON_DIM) != "" ? Integer.parseInt(attributes.get(ICON_DIM).split("x")[0]) : 25),
                    (attributes.get(ICON_DIM) != "" ? Integer.parseInt(attributes.get(ICON_DIM).split("x")[1]) : 25),
                    Image.SCALE_DEFAULT);
            icon.setImage(image);
            c.setIcon(icon);
        }
    }

    private static void styleComponent(Element e, JLabel c){
        Dictionary<String, String> attributes = new Hashtable<>();
        attributes.put(BACKGROUND_COLOR, "");
        attributes.put(FOREGROUND_COLOR, "");
        attributes.put(ICON, "");
        attributes.put(ICON_DIM, "");

        String style = getAttribute(e, "style");

        if(style == null)
            return;

        style = style.replaceAll(" ", "");
        String[] pairs = style.split(";");

        for(String pair: pairs){
            String[] split = pair.split(":");
            attributes.put(split[0], split[1]);
        }

        switch (attributes.get(BACKGROUND_COLOR)) {
            case "black" -> {
                c.setBackground(Color.black);
                c.setOpaque(true);
            }
            case "blue" -> {
                c.setBackground(Color.blue);
                c.setOpaque(true);
            }
            case "white" -> {
                c.setBackground(Color.white);
                c.setOpaque(true);
            }
            case "gray" -> {
                c.setBackground(Color.gray);
                c.setOpaque(true);
            }
            case "light-gray" -> {
                c.setBackground(Color.lightGray);
                c.setOpaque(true);
            }
            case "orange" -> {
                c.setBackground(Color.orange);
                c.setOpaque(true);
            }
            case "yellow" -> {
                c.setBackground(Color.yellow);
                c.setOpaque(true);
            }
            case "red" -> {
                c.setBackground(Color.red);
                c.setOpaque(true);
            }
            case "cyan" -> {
                c.setBackground(Color.cyan);
                c.setOpaque(true);
            }
            default -> System.out.println("Default color");
        }

        switch (attributes.get(FOREGROUND_COLOR)) {
            case "black" -> c.setForeground(Color.black);
            case "blue" -> c.setForeground(Color.blue);
            case "white" -> c.setForeground(Color.white);
            case "gray" -> c.setForeground(Color.gray);
            case "light-gray" -> c.setForeground(Color.lightGray);
            case "orange" -> c.setForeground(Color.orange);
            case "yellow" -> c.setForeground(Color.yellow);
            case "red" -> c.setForeground(Color.red);
            case "cyan" -> c.setForeground(Color.cyan);
            default -> System.out.println("Default color");
        }

        if(attributes.get(ICON) != ""){
            ImageIcon icon = new ImageIcon("src/com/frame/media/" + attributes.get(ICON));
            Image image = icon.getImage().getScaledInstance(
                    (attributes.get(ICON_DIM) != "" ? Integer.parseInt(attributes.get(ICON_DIM).split("x")[0]) : 25),
                    (attributes.get(ICON_DIM) != "" ? Integer.parseInt(attributes.get(ICON_DIM).split("x")[1]) : 25),
                    Image.SCALE_DEFAULT);
            icon.setImage(image);
            c.setIcon(icon);
        }

    }

    private static void styleComponent(Element e, JPanel c){
        Dictionary<String, String> attributes = new Hashtable<>();
        attributes.put(BACKGROUND_COLOR, "");
        attributes.put(FOREGROUND_COLOR, "");
        attributes.put(ICON, "");

        String style = getAttribute(e, "style");

        if(style == null)
            return;

        style = style.replaceAll(" ", "");
        String[] pairs = style.split(";");

        for(String pair: pairs){
            String[] split = pair.split(":");
            attributes.put(split[0], split[1]);
        }

        switch (attributes.get(BACKGROUND_COLOR)) {
            case "black" -> c.setBackground(Color.black);
            case "blue" -> c.setBackground(Color.blue);
            case "white" -> c.setBackground(Color.white);
            case "gray" -> c.setBackground(Color.gray);
            case "light-gray" -> c.setBackground(Color.lightGray);
            case "orange" -> c.setBackground(Color.orange);
            case "yellow" -> c.setBackground(Color.yellow);
            case "red" -> c.setBackground(Color.red);
            case "cyan" -> c.setBackground(Color.cyan);
            default -> System.out.println("Default color");
        }

        switch (attributes.get(FOREGROUND_COLOR)) {
            case "black" -> c.setForeground(Color.black);
            case "blue" -> c.setForeground(Color.blue);
            case "white" -> c.setForeground(Color.white);
            case "gray" -> c.setForeground(Color.gray);
            case "light-gray" -> c.setForeground(Color.lightGray);
            case "orange" -> c.setForeground(Color.orange);
            case "yellow" -> c.setForeground(Color.yellow);
            case "red" -> c.setForeground(Color.red);
            case "cyan" -> c.setForeground(Color.cyan);
            default -> System.out.println("Default color");
        }

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

    public static JPanel XMLreader(String filePath, String[] values) {
        int i = 0;
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
                case "Grid":
                    String[] dim = getAttribute(doc.getDocumentElement(), "dimensions").split(":");
                    panel.setLayout(new GridLayout(
                            Integer.parseInt(dim[0]),
                            Integer.parseInt(dim[1])
                    ));
                    LAYOUT = GRIDLAYOUT;
                    break;
                case "GridBag":
                    panel.setLayout(new GridBagLayout());
                    LAYOUT = GRIDBAGLAYOUT;
                    break;
                case "Border":
                    panel.setLayout(new BorderLayout());
                    LAYOUT = BORDERLAYOUT;
                    break;
                default:
                    panel.setLayout(new FlowLayout());
                    LAYOUT = FLOWLAYOUT;
            }

            styleComponent(doc.getDocumentElement(), panel);

            NodeList nodeList = doc.getElementsByTagName("object");

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    switch (getAttribute(element, "class")) {
                        case "button":
                            StyledButton button;
                            if(getAttribute(element, "type") == "dynamic"){
                                button = new StyledButton(values[i++]);
                            } else {
                                button = new StyledButton(getTag(element, "text"));
                            }
                            styleComponent(element, button);
                            setSize(element, button);
                            buttons.put(getAttribute(element, "id"), button);

                            if (LAYOUT == GRIDBAGLAYOUT) {
                                setConstraints(element);
                                panel.add(button, constraints);
                            } else if (LAYOUT == GRIDBAGLAYOUT) {
                                panel.add(button, getTag(element, "position"));
                            } else if (LAYOUT == GRIDLAYOUT) {
                                panel.add(button);
                            } else {
                                panel.add(button);
                            }
                            break;

                        case "label":
                            JLabel label;
                            if(Objects.equals(getAttribute(element, "type"), "dynamic")){
                                label = new JLabel(values[i++]);
                            } else {
                                label = new JLabel(getTag(element, "text"));
                            }
                            styleComponent(element, label);
                            setSize(element, label);
                            labels.put(getAttribute(element, "id"), label);

                            if (LAYOUT == GRIDBAGLAYOUT) {
                                setConstraints(element);
                                panel.add(label, constraints);
                            } else if (LAYOUT == GRIDBAGLAYOUT) {
                                panel.add(label, getTag(element, "position"));
                            } else if (LAYOUT == GRIDLAYOUT) {
                                panel.add(label);
                            } else {
                                panel.add(label);
                            }
                            break;

                        case "textField":
                            JTextField textField = new JTextField(getTag(element, "text"));
                            setSize(element, textField);
                            textFields.put(getAttribute(element, "id"), textField);

                            if (LAYOUT == GRIDBAGLAYOUT) {
                                setConstraints(element);
                                panel.add(textField, constraints);
                            } else if (LAYOUT == GRIDBAGLAYOUT) {
                                panel.add(textField, getTag(element, "position"));
                            } else if (LAYOUT == GRIDLAYOUT) {
                                panel.add(textField);
                            } else {
                                panel.add(textField);
                            }
                            break;

                        case "panel":
                            JPanel extra = XMLreader("src/com/frame/pages/components/" + getTag(element, "href") + ".xml", null);;

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

                            break;

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
        window.setPreferredSize(new Dimension(1920, 1080));

        centerPanel = XMLreader(
                Pages.HOME,
                null);

        window.add(XMLreader(
                Pages.MENU,
                null),
                BorderLayout.WEST
        );

        window.add(centerPanel,
                BorderLayout.CENTER
        );

        this.add(window);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("LinkedOut");

        ImageIcon icon = new ImageIcon("src/com/frame/media/LinkedOut.png");
        Image image = icon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        this.setIconImage(image);


        this.pack();
        this.setLocationRelativeTo(null);
        this.setMinimumSize(this.getMinimumSize());
        this.setVisible(true);

        Actioner.menu();
        Actioner.home();
    }
}
