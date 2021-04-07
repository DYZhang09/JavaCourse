package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;


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
}
