package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTerm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Term extends AbstractTerm {
    public Term() {
    }

    public Term(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != getClass()) return false;

        return this.content.equals(((Term) other).getContent());
    }

    @Override
    public int compareTo(AbstractTerm other) {
        return this.content.compareTo(other.getContent());
    }

    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeObject(this);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Override
    public void readObject(ObjectInputStream in) {
        try {
            in.readObject();
        } catch (ClassNotFoundException | IOException i) {
            i.printStackTrace();
        }
    }
}
