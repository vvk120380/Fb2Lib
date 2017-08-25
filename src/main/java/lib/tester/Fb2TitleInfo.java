package lib.tester;

import org.dom4j.Document;
import org.dom4j.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by md.sar.pc6 on 25.08.2017.
 */
public class Fb2TitleInfo {
    List<String>  genres = new ArrayList<String>();
    List<Fb2Author> authors  = new ArrayList<Fb2Author>();
    String   title  = null;
    String   annotation  = null;
    String   keywords  = null;
    String   date  = null;
    String   coverpage  = null;
    String   lang  = null;
    String   src_lang  = null;
    String   translator  = null;
    List<Fb2Sequence> sequences  = new ArrayList<Fb2Sequence>();

    public Fb2TitleInfo (Map uris, Document document, String sourcepath){

        Node node = null;
        List<Node> nodelist = null;

        //Получаем список жанров
        nodelist = Fb2Tools.getNodesByXPath(uris, document, sourcepath + "/pqr:genre");
        if(nodelist != null && nodelist.size() > 0)
            for (int i = 0; i < nodelist.size(); i++) {
              genres.add(nodelist.get(i).getStringValue());
            }


        //Получаем список авторов
        nodelist = Fb2Tools.getNodesByXPath(uris, document, sourcepath + "/pqr:author");
        if(nodelist != null && nodelist.size() > 0)
            for (int i = 0; i < nodelist.size(); i++){
                Fb2Author author = new Fb2Author();
                node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:author[" + i+1 +"]/pqr:first-name");
                if (node != null) author.setFirst_name(node.getStringValue());
                node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:author[" + i+1 +"]/pqr:middle-name");
                if (node != null) author.setMiddle_name(node.getStringValue());
                node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:author[" + i+1 +"]/pqr:last-name");
                if (node != null) author.setLast_name(node.getStringValue());
                authors.add(author);
            }


        node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:book-title");
        if (node != null) title = node.getStringValue();

        node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:annotation");
        if (node != null) annotation = node.getStringValue();

        node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:keywords");
        if (node != null) keywords = node.getStringValue();

        node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:date");
        if (node != null) date = node.getStringValue();

        node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:coverpage/pqr:image");
        if (node != null) coverpage = node.valueOf("@l:href");


        node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:lang");
        if (node != null) lang = node.getStringValue();

        node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:scr-lang");
        if (node != null) src_lang = node.getStringValue();

        node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:translator");
        if (node != null) translator = node.getStringValue();

        //Получаем список авторов
        nodelist = Fb2Tools.getNodesByXPath(uris, document, sourcepath + "/pqr:sequence");
        if(nodelist != null && nodelist.size() > 0)
            for (int i = 0; i < nodelist.size(); i++){
                Fb2Sequence sequence = new Fb2Sequence();
                node = Fb2Tools.getSingleNodeByXPath(uris, document, sourcepath + "/pqr:sequence[" + i+1 +"]");
                if (node != null) {
                    sequence.setName(node.valueOf("@name"));
                    if (node.valueOf("@number") != null && node.valueOf("@number").length() > 0)
                        sequence.setNumber(Integer.valueOf(node.valueOf("@number")));
                }
                sequences.add(sequence);
            }


    }

    public List<String> getGenres() {
        return genres;
    }

    public List<Fb2Author> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getAnnotation() {
        return annotation;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getDate() {
        return date;
    }

    public String getCoverpage() {
        return coverpage;
    }

    public String getLang() {
        return lang;
    }

    public String getSrc_lang() {
        return src_lang;
    }

    public String getTranslator() {
        return translator;
    }

    public List<Fb2Sequence> getSequences() {
        return sequences;
    }
}
