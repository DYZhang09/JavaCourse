package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractTermTuple;

import java.util.List;

/**
 * <pre>
 *     Document是文档对象的类，继承于AbstractDocument抽象父类.
 *          文档对象是解析一个文本文件得到结果，文档对象里面包含：
 *              文档id.
 *              文档的绝对路径.
 *              文档包含的三元组对象列表，一个三元组对象是抽象类AbstractTermTuple的子类实例
 * </pre>
 */
public class Document extends AbstractDocument {
    /**
     * 默认构造函数
     */
    public Document() {
    }

    /**
     * 构造函数
     *
     * @param docId   文档对应的docID
     * @param docPath 文档对应的地址
     */
    public Document(int docId, String docPath) {
        super(docId, docPath);
    }

    /**
     * 构造函数
     *
     * @param docId   文档对应docId
     * @param docPath 文档对应路径
     * @param tuples  文档所包含的三元组的链表
     */
    public Document(int docId, String docPath, List<AbstractTermTuple> tuples) {
        super(docId, docPath, tuples);
    }

    /**
     * 获得文档docID
     *
     * @return docID
     */
    @Override
    public int getDocId() {
        return this.docId;
    }

    /**
     * 设置docID
     *
     * @param docId：文档id
     */
    @Override
    public void setDocId(int docId) {
        this.docId = docId;
    }

    /**
     * 获得文档绝对路径
     *
     * @return 文档绝对路径
     */
    @Override
    public String getDocPath() {
        return this.docPath;
    }

    /**
     * 设置文档绝对路径
     *
     * @param docPath：文档绝对路径
     */
    @Override
    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    /**
     * 获得文档三元组链表
     *
     * @return 文档三元组链表
     */
    @Override
    public List<AbstractTermTuple> getTuples() {
        return this.tuples;
    }

    /**
     * 添加三元组
     *
     * @param tuple ：要添加的三元组
     */
    @Override
    public void addTuple(AbstractTermTuple tuple) {
        if (!contains(tuple))
            this.tuples.add(tuple);
    }

    /**
     * 判断文档是否包含三元组
     *
     * @param tuple ： 指定的三元组
     * @return 如果包含则返回 true
     */
    @Override
    public boolean contains(AbstractTermTuple tuple) {
        return this.tuples.contains(tuple);
    }

    /**
     * 根据下标获得对应的三元组
     *
     * @param index：指定下标位置
     * @return 三元组
     */
    @Override
    public AbstractTermTuple getTuple(int index) {
        return this.tuples.get(index);
    }

    /**
     * 获得文档三元组的个数
     *
     * @return 三元组的个数
     */
    @Override
    public int getTupleSize() {
        return this.tuples.size();
    }

    /**
     * 获得文档的字符串表示
     *
     * @return 文档的字符串表示
     */
    @Override
    public String toString() {
        return "DocID: " + this.docId
                + ", DocPath: " + this.docPath
                + ", tuples: " + this.tuples.toString();
    }
}
