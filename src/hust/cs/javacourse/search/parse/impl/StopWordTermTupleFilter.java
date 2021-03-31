package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.parse.AbstractTermTupleScanner;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.StopWords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class StopWordTermTupleFilter extends AbstractTermTupleFilter{
    public StopWordTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    public AbstractTermTuple next() {
        if (this.input == null) return null;
        AbstractTermTuple termTuple = new TermTuple();
        do {
            termTuple = this.input.next();
            if (termTuple == null) return null;
        } while (Arrays.binarySearch(StopWords.STOP_WORDS, termTuple.term.getContent()) >= 0);
        return termTuple;
    }

public static void main(String[] args) {
        try {
            File file = new File(Config.DOC_DIR + "stop.txt");
            TermTupleScanner scanner = new TermTupleScanner(new BufferedReader(new FileReader(file)));
            StopWordTermTupleFilter filter = new StopWordTermTupleFilter(scanner);
            while (true) {
                AbstractTermTuple tuple = filter.next();
                if (tuple == null) return;
                System.out.println(tuple.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
