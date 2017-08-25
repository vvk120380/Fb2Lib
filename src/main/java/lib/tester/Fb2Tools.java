package lib.tester;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by md.sar.pc6 on 25.08.2017.
 */
public class Fb2Tools {

    public static List<Node> getNodesByXPath(Map uris, Document document, String path) {
        XPath xpath = document.createXPath(path);
        xpath.setNamespaceURIs(uris);
        return xpath.selectNodes(document);
    }

    public static Node getSingleNodeByXPath(Map uris, Document document, String path) {
        XPath xpath = document.createXPath(path);
        xpath.setNamespaceURIs(uris);
        return xpath.selectSingleNode(document);
    }

    public static String getGenreAlt(String genre) throws DocumentException {

        Map gen_uris = new HashMap();
        gen_uris.put("pqr", "http://www.w3.org/2001/XMLSchema-instance");

        File xmlFile = new File("c:\\WorkingStorage\\TestLab\\XmlFb2Reader\\genere.xml");
        SAXReader reader = new SAXReader();
        Document genere_doc = reader.read(xmlFile);

        XPath xpath = genere_doc.createXPath("//subgenre[@value='" + genre + "']/genre-descr[@lang='ru']");
        xpath.setNamespaceURIs(gen_uris);
        Node node = xpath.selectSingleNode(genere_doc);

        return node != null ? node.valueOf("@title") + " (" + genre + ")" : "Не известный (" + genre + ")";
    }

    public static String makeZip(String path, String sourcefilename) {
        UUID id = UUID.randomUUID();
        String fn = id.toString().replaceAll("-", "");

        try {
            FileInputStream fis = new FileInputStream(path + "\\" + sourcefilename);
            FileOutputStream fout = new FileOutputStream(path + "\\" + fn + ".zip");
            ZipOutputStream zout = new ZipOutputStream(fout);
            ZipEntry ze = new ZipEntry(fn + ".fb2");
            zout.putNextEntry(ze);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
            zout.close();
        } catch (IOException ex) {
        }

        return fn + ".zip";
    }


    public static String makeMD5(String filename) {
        HashCode md5 = null;
        try {
            md5 = Files.hash(new File(filename), Hashing.md5());
        }
        catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
        return md5.toString();
    }
}

