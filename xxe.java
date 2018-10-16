import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException; 
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

   @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map parseXmlToList2(String strXML) throws Exception {
       Map data = new HashMap<>();
       DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
       String FEATURE = null;
       try {

          FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
          documentBuilderFactory.setFeature(FEATURE, true);

          FEATURE = "http://xml.org/sax/features/external-general-entities";
          documentBuilderFactory.setFeature(FEATURE, false);

          FEATURE = "http://xml.org/sax/features/external-parameter-entities";
          documentBuilderFactory.setFeature(FEATURE, false);

          FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
          documentBuilderFactory.setFeature(FEATURE, false);

          documentBuilderFactory.setXIncludeAware(false);
          documentBuilderFactory.setExpandEntityReferences(false);

       } catch (ParserConfigurationException e) {
    	   System.out.println("ParserConfigurationException was thrown. The feature '" +
                FEATURE + "' is probably not supported by your XML processor.");

       }
       DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
       InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
       org.w3c.dom.Document doc = documentBuilder.parse(stream);
       doc.getDocumentElement().normalize();
       NodeList nodeList = doc.getDocumentElement().getChildNodes();
       for (int idx=0; idx<nodeList.getLength(); ++idx) {
          Node node = nodeList.item(idx);
          if (node.getNodeType() == Node.ELEMENT_NODE) {
             org.w3c.dom.Element element = (org.w3c.dom.Element) node;
             data.put(element.getNodeName(), element.getTextContent());
          }
       }
       try {
          stream.close();
       }
       catch (Exception ex) {

       }
       return data;
    }
