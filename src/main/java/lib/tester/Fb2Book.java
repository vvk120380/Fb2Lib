package lib.tester;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by md.sar.pc6 on 23.08.2017.
 */
public class Fb2Book {

    Map uris = null;
    Document document = null;
    Fb2TitleInfo titleinfo = null;

    public Fb2Book(String filename) throws DocumentException {
        uris = new HashMap();
        uris.put("pqr", "http://www.gribuser.ru/xml/fictionbook/2.0");

        File xmlFile = new File(filename);
        SAXReader reader = new SAXReader();
        document = reader.read(xmlFile);
        titleinfo = new Fb2TitleInfo(uris,document,"/pqr:FictionBook/pqr:description/pqr:title-info");
    }

    public Fb2TitleInfo getTitleinfo() {
        return titleinfo;
    }

}
