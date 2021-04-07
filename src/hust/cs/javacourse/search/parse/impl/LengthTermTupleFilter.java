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

/**
 * <pre>
 *     LengthTermTupleFilter继承于AbstractTermTupleFilter抽象父类，读取一个AbstractTermTupleStream流对象，
 *     根据Term单词的长度过滤，保留长度在[lb, ub]的Term，其中lb与ub由util包中的Config.TERM_FILTER_MINLENGTH
 *     与Config.TERM_FILTER_MAXLENGTH指定
 * </pre>
 */
public class LengthTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     *
     * @param input 输入流, AbstractTermTuple子类对象
     */
    public LengthTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    /**
     * 返回过滤后的下一个三元组
     *
     * @return 三元组
     */
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

    /**
     * 简单测试
     *
     * @param args 命令行参数
     */
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
