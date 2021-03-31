package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.parse.impl.LengthTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.PatternTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.StopWordTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.TermTupleScanner;

import java.io.*;

public class DocumentBuilder extends AbstractDocumentBuilder {
    @Override
    public AbstractDocument build(int docId, String docPath, AbstractTermTupleStream termTupleStream) {
        AbstractDocument document = new Document();
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
        AbstractDocument document = new Document();
        document.setDocId(docId);
        document.setDocPath(docPath);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            AbstractTermTupleStream termTupleStream = new PatternTermTupleFilter(
                    new StopWordTermTupleFilter(
                            new LengthTermTupleFilter(
                                    new TermTupleScanner(bufferedReader))));
            document = this.build(docId, docPath, termTupleStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
