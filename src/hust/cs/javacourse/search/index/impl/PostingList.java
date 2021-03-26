package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class PostingList extends AbstractPostingList {
    @Override
    public void add(AbstractPosting posting) {
        this.list.add(posting);
    }

    @Override
    public String toString() {
        return "PostingList: " + this.list.toString();
    }

    @Override
    public void add(List<AbstractPosting> postings) {
        this.list.addAll(postings);
    }

    @Override
    public AbstractPosting get(int index) {
        return this.list.get(index);
    }

    @Override
    public int indexOf(AbstractPosting posting) {
        return this.list.indexOf(posting);
    }

    @Override
    public int indexOf(int docId) {
        int idx = 0;
        for (AbstractPosting posting : this.list) {
            if (posting.getDocId() == docId)
                return idx;
            idx++;
        }
        return -1;
    }

    @Override
    public boolean contains(AbstractPosting posting) {
        return this.list.contains(posting);
    }

    @Override
    public void remove(AbstractPosting posting) {
        this.list.remove(posting);
    }

    @Override
    public void remove(int index) {
        this.list.remove(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public void sort() {
        this.list.sort(null);
    }

    @Override
    public void writeObject(ObjectOutputStream outputStream) {
        try {
            outputStream.writeObject(this.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream inputStream) {
        try {
            this.list = (List<AbstractPosting>)inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
