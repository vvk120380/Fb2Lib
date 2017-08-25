package lib.tester;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.dom4j.*;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) {

        Fb2Book fb2Book = null;

        String path = "c:\\WorkingStorage\\TestLab\\XmlFb2Reader\\testdata";
        ArrayList<String> flist = new ArrayList<String>();
        File f = new File(path);
        for (File s : f.listFiles()) {
            if (s.isFile() && s.getName().contains(".fb2")) {
                flist.add(s.getName());
            }
        }

        for (String file : flist) {

            System.out.println("Src file: " + file);

            String filename = path + "\\" + file;

            String zipfn = Fb2Tools.makeZip(path, file);
            System.out.println("Zip file: " + zipfn);

            String md5Hex = Fb2Tools.makeMD5(filename);
            System.out.println("     MD5: " + md5Hex);

            try {
                fb2Book = new Fb2Book(filename);

                System.out.println("Название: " + fb2Book.getTitleinfo().getTitle());
                System.out.println("Автор(ы): ");
                for (Fb2Author author : fb2Book.getTitleinfo().getAuthors()) {
                    System.out.println("          " + author.toString());
                }
                System.out.println("   Серия: ");
                for (Fb2Sequence sequence : fb2Book.getTitleinfo().getSequences()) {
                    System.out.println("          " + sequence.toString());
                }
                System.out.println("    Язык: " + fb2Book.getTitleinfo().getLang());
                System.out.println("   Жанры: ");
                for (String genre : fb2Book.getTitleinfo().getGenres()) {
                    System.out.println("          " + Fb2Tools.getGenreAlt(genre));
                }

            } catch (DocumentException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("--------------------------------------------------------------------------------");

            System.out.println();
        }
    }
}

//        try {
//            Document doc = parseXML("c:\\WorkingStorage\\TestLab\\XmlFb2Reader\\testXml.fb2");
//
//            //-------------------------------------------------------------------------------------
////            Element root = doc.getRootElement();
////            System.out.println(root.getQName().getName());
////            for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
////                Element element = it.next();
////                System.out.println("\t" + element.getQName().getName());
////                for (Iterator<Element> it1 = element.elementIterator(); it1.hasNext();) {
////                    Element element1 = it1.next();
////                    System.out.println("\t\t" + element1.getQName().getName());
////                }
////            }
////            System.out.println();
//            //-------------------------------------------------------------------------------------
//
//            //-------------------------------------------------------------------------------------
//            Map uris = new HashMap();
//            uris.put("pqr", "http://www.gribuser.ru/xml/fictionbook/2.0");
//
//            XPath xpath = doc.createXPath("/pqr:FictionBook/pqr:description/pqr:title-info/pqr:genre");
//            xpath.setNamespaceURIs(uris);
//            List<Node> nodes = xpath.selectNodes(doc);
//
//            for (Node node: nodes) {
//                System.out.println(node.getStringValue());
//            }
//            System.out.println();
//            //-------------------------------------------------------------------------------------
//
//        }
//        catch (DocumentException ex){
//            System.out.println( ex.getMessage());
//        }

//}

//    public static Document parseXML(String filename) throws DocumentException {
//        File xmlFile = new File(filename);
//        SAXReader reader = new SAXReader();
//        Document document = reader.read(xmlFile);
//        return document;
//    }
