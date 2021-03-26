package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Posting extends AbstractPosting {
    public Posting() {

    }

    public Posting(int docId, int freq, List<Integer> positions) {
        this.docId = docId;
        this.freq = freq;
        this.positions = positions;
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
    public int getFreq() {
        return this.freq;
    }

    @Override
    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Override
    public List<Integer> getPositions() {
        return this.positions;
    }

    @Override
    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (object.getClass() != getClass()) return false;

        Posting other = (Posting) object;
        return this.docId == other.docId && this.freq == other.freq
                && this.positions.equals(other.positions);
    }

    @Override
    public String toString() {
        return "" + "<docId: " + this.docId
                + ", freq: " + this.freq
                + ", positions: " + this.positions.toString()
                + ">";
    }

    @Override
    public int compareTo(AbstractPosting o) {
        return this.docId - o.getDocId();
    }

    @Override
    public void sort() {
        this.positions.sort(null);
    }

    @Override
    public void writeObject(ObjectOutputStream outputStream) {
        try {
            outputStream.writeObject(getDocId());
            outputStream.writeObject(getFreq());
            outputStream.writeObject(getPositions());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readObject(ObjectInputStream inputStream) {
        try {
            setDocId(inputStream.readInt());
            setFreq(inputStream.readInt());
            setPositions((List<Integer>)inputStream.readObject());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
