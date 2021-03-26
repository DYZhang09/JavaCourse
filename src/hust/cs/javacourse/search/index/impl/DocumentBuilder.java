package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;

import java.io.*;

public class DocumentBuilder extends AbstractDocumentBuilder{
    @Override
    public AbstractDocument build(int docId, String docPath, AbstractTermTupleStream termTupleStream) {
        Document document = new Document();
        document.setDocId(docId);
        document.setDocPath(docPath);
        while (true) {
            AbstractTermTuple termTuple = termTupleStream.next();
            if (termTuple == null) return document;
            document.addTuple(termTuple);
        }
    }

    @Override
    public AbstractDocument build(int docId, String docPath, File file) {
        Document document = new Document();
        document.setDocId(docId);
        document.setDocPath(docPath);

        // TODO: initialize the tuples of document
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
