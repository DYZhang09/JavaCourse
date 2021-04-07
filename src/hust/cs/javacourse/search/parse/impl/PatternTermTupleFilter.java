package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

import java.util.regex.Pattern;

/**
 * <pre>
 *     PatternTermTupleFilter继承于AbstractTermTupleFilter抽象父类，读取一个AbstractTermTupleStream流对象，
 *     根据正则表达式过滤Term，保留不符合正则表达式的Term，正则表达式由util包里面的Config.TERM_FILTER_PATTERN指定
 * </pre>
 */
public class PatternTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * 构造函数
     *
     * @param input 输入流, AbstractTermTupleStream子类对象
     */
    public PatternTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
    }

    /**
     * 返回下一个经过过滤后的三元组
     *
     * @return 三元组
     */
    public AbstractTermTuple next() {
        if (this.input == null) return null;
        AbstractTermTuple tuple = new TermTuple();
        do {
            tuple = this.input.next();
            if (tuple == null) return null;
        } while (!Pattern.matches(Config.TERM_FILTER_PATTERN, tuple.term.getContent()));
        return tuple;
    }
}
