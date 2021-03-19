package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.AbstractTermTuple;

public class TermTuple extends AbstractTermTuple {
    public TermTuple() {

    }

    public TermTuple(AbstractTerm term, int curPos) {
        this.term = term;
        this.curPos = curPos;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != getClass()) return false;

        TermTuple o = (TermTuple) other;
        return this.term.equals(o.term) && this.curPos == o.curPos;
    }

    @Override
    public String toString() {
        return "" + "<term: " + this.term + ", freq: " + this.freq + ", curPos: " + this.curPos + ">";
    }
}
