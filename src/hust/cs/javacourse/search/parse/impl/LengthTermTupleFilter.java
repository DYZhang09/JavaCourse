package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleScanner;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;
import javafx.scene.paint.Stop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LengthTermTupleFilter extends AbstractTermTupleFilter {
    public LengthTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    public AbstractTermTuple next() {
        if (this.input == null) return null;
        AbstractTermTuple tuple = new TermTuple();
        do {
            tuple = this.input.next();
            if (tuple == null) return null;
        } while (tuple.term.getContent().length() < Config.TERM_FILTER_MINLENGTH |
                tuple.term.getContent().length() > Config.TERM_FILTER_MAXLENGTH);
        return tuple;
    }

    public static void main(String[] args) {
        try {
            File file = new File(Config.DOC_DIR + "stop.txt");
            TermTupleScanner scanner = new TermTupleScanner(new BufferedReader(new FileReader(file)));
            LengthTermTupleFilter filter = new LengthTermTupleFilter(new StopWordTermTupleFilter(scanner));
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
