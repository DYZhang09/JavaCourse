package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.query.AbstractHit;

import java.util.Map;

public class Hit extends AbstractHit {
    public Hit() {

    }

    public Hit(int docId, String docPath) {
        super(docId, docPath);
    }

    public Hit(int docId, String docPath, Map<AbstractTerm, AbstractPosting> postingMap) {
        super(docId, docPath, postingMap);
    }

    @Override
    public int getDocId() {
        return this.docId;
    }

    @Override
    public String getDocPath() {
        return this.docPath;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public double getScore() {
        return this.score;
    }

    @Override
    public Map<AbstractTerm, AbstractPosting> getTermPostingMapping() {
        return this.termPostingMapping;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "DocID: " + this.docId + ", Score: " + this.score +
                "\nDocPath: " + this.docPath + "\nContent: \n" + this.content + "\n";
    }

    @Override
    public int compareTo(AbstractHit other) {
        return Double.compare(this.score, other.getScore());
    }
}
