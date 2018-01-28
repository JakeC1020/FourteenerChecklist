package io.jakec.fourteenerchecklist.fourteeners;

import android.content.Context;
import android.support.annotation.Nullable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlFourteenerDaoImpl implements FourteenerDao {
    private final String xmlSource = "peaks.xml";
    private final String xmlNodeName = "peak";

    private Context myContext;

    public XmlFourteenerDaoImpl(Context myContext) {
        this.myContext = myContext;
    }

    @Override
    public List<Fourteener> getAllFourteeners() {
        try {
            NodeList peakNodeList = getPeakNodeList();
            return createFourteenerListFromNodeList(peakNodeList);

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Fourteener> getFourteenersByRange(String range) {
        List<Fourteener> fourteenersInRange = new ArrayList<>();

        List<Fourteener> fourteenerList = getAllFourteeners();
        for (Fourteener currentFourteener: fourteenerList) {
            if (currentFourteener.getRange() == range) {
                fourteenersInRange.add(currentFourteener);
            }
        }

        return fourteenersInRange;
    }

    @Override
    public Fourteener getFourteenerById(int id) {
        List<Fourteener> fourteenerList = getAllFourteeners();
        for (Fourteener currentFourteener: fourteenerList) {
            if (currentFourteener.getId() == id) {
                return currentFourteener;
            }
        }

        return null;
    }

    private NodeList getPeakNodeList() throws Exception {
        InputStream inputStream = this.myContext.getAssets().open(this.xmlSource);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        document.getDocumentElement().normalize();

        return document.getElementsByTagName(xmlNodeName);
    }

    private List<Fourteener> createFourteenerListFromNodeList(NodeList nodeList) {
        List<Fourteener> fourteenerList = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                Element currentElement = (Element) currentNode;
                Fourteener currentFourteener = getFourteenerFromElementNode(currentElement);

                if (currentFourteener != null) {
                    fourteenerList.add(currentFourteener);
                }
            }
        }

        return fourteenerList;
    }

    @Nullable
    private Fourteener getFourteenerFromElementNode(Element currentElement) {
        try {
            int id = Integer.parseInt(currentElement.getElementsByTagName("id").item(0).getTextContent());
            String name = currentElement.getElementsByTagName("name").item(0).getTextContent();
            String range = currentElement.getElementsByTagName("range").item(0).getTextContent();
            int elevation = Integer.parseInt(currentElement.getElementsByTagName("elevation").item(0).getTextContent());

            return new Fourteener(id, name, range, elevation);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }
}
