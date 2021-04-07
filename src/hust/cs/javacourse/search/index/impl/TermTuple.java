package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.AbstractTermTuple;

/**
 * <pre>
 * TermTuple是TermTuple对象的类.
 *      一个TermTuple对象为三元组(单词，出现频率，出现的当前位置).
 *      当解析一个文档时，每解析到一个单词，应该产生一个三元组，其中freq始终为1(因为单词出现了一次).
 * </pre>
 */
public class TermTuple extends AbstractTermTuple {
    /**
     * 默认构造函数
     */
    public TermTuple() {

    }

    /**
     * 构造函数
     *
     * @param term   Term对象
     * @param curPos 当前在文档中的位置(以单词为单位)
     */
    public TermTuple(AbstractTerm term, int curPos) {
        this.term = term;
        this.curPos = curPos;
    }

    /**
     * 判断当前三元组是否与另一个三元组相等，当包含的term以及出现的位置完全一致时认为相等
     *
     * @param other 待比较的三元组
     * @return 如果相等则返回true
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != getClass()) return false;

        TermTuple o = (TermTuple) other;
        return this.term.equals(o.term) && this.curPos == o.curPos;
    }

    /**
     * 返回三元组的字符串表示
     *
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return "" + "<term: " + this.term + ", freq: " + this.freq + ", curPos: " + this.curPos + ">";
    }
}
