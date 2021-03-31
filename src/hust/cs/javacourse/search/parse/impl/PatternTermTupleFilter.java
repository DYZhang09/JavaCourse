package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class PatternTermTupleFilter extends AbstractTermTupleFilter {
    public PatternTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    public AbstractTermTuple next() {
        if (this.input == null) return null;
        AbstractTermTuple tuple = new TermTuple();
        do {
            tuple = this.input.next();
            if (tuple == null) return null;
        } while (!Pattern.matches(Config.TERM_FILTER_PATTERN, tuple.term.getContent()));
        return tuple;
    }

    public static void main(String[] args) {
        try {
            File file = new File(Config.DOC_DIR + "stop.txt");
            TermTupleScanner scanner = new TermTupleScanner(new BufferedReader(new FileReader(file)));
            PatternTermTupleFilter filter = new PatternTermTupleFilter(scanner);
            while (true) {
                AbstractTermTuple tuple = filter.next();
                if (tuple == null) return;
                System.out.println(tuple);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
