package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractTermTuple;

import java.util.List;

public class Document extends AbstractDocument {
    public Document() {
    }

    public Document(int docId, String docPath) {
        this.docId = docId;
        this.docPath = docPath;
    }

    public Document(int docId, String docPath, List<AbstractTermTuple> tuples) {
        this.docId = docId;
        this.docPath = docPath;
        this.tuples = tuples;
    }

    @Override
    public int getDocId() {
        return this.docId;
    }

    @Override
    public void setDocId(int docId) {
        this.docId = docId;
    }

    @Override
    public String getDocPath() {
        return this.docPath;
    }

    @Override
    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    @Override
    public List<AbstractTermTuple> getTuples() {
        return this.tuples;
    }

    @Override
    public void addTuple(AbstractTermTuple tuple) {
        if (!contains(tuple))
            this.tuples.add(tuple);
    }

    @Override
    public boolean contains(AbstractTermTuple tuple) {
        return this.tuples.contains(tuple);
    }

    @Override
    public AbstractTermTuple getTuple(int index) {
        return this.tuples.get(index);
    }

    @Override
    public int getTupleSize() {
        return this.tuples.size();
    }

    @Override
    public String toString() {
        return "DocID: " + this.docId
                + ", DocPath: " + this.docPath
                + ", tuples: " + this.tuples.toString();
    }
}
