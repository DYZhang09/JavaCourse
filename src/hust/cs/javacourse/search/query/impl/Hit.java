package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.query.AbstractHit;

import java.util.Map;

/**
 * <pre>
 * Hit是一个搜索命中结果的类. 实现了Comparable接口，用于命中结果的排序.
 * </pre>
 */
public class Hit extends AbstractHit {
    /**
     * 默认构造函数
     */
    public Hit() {

    }

    /**
     * 构造函数
     * @param docId 文档id
     * @param docPath 文档路径
     */
    public Hit(int docId, String docPath) {
        super(docId, docPath);
    }

    /**
     * 构造函数
     * @param docId 文档id
     * @param docPath 文档路径
     * @param postingMap Term-Posting的map映射
     */
    public Hit(int docId, String docPath, Map<AbstractTerm, AbstractPosting> postingMap) {
        super(docId, docPath, postingMap);
    }

    /**
     * 获得文档id
     * @return 文档id
     */
    @Override
    public int getDocId() {
        return this.docId;
    }

    /**
     * 获得文档路径
     * @return 文档路径
     */
    @Override
    public String getDocPath() {
        return this.docPath;
    }

    /**
     * 获得文档内容
     * @return 文档内容
     */
    @Override
    public String getContent() {
        return this.content;
    }

    /**
     * 获得Hit的得分
     * @return 得分
     */
    @Override
    public double getScore() {
        return this.score;
    }

    /**
     * 获得Term-Posting的map映射
     * @return Term-Posting的map映射
     */
    @Override
    public Map<AbstractTerm, AbstractPosting> getTermPostingMapping() {
        return this.termPostingMapping;
    }

    /**
     * 设置内容
     * @param content ：文档内容
     */
    @Override
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 设置得分
     * @param score ：文档得分
     */
    @Override
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * 返回字符串表示
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return "DocID: " + this.docId + ", Score: " + this.score +
                "\nDocPath: " + this.docPath + "\nContent: \n" + this.content + "\n";
    }

    /**
     * 比较与另一个AbstractHit子类对象的大小，根据得分比较
     * @param other 待比较的对象
     * @return 本对象的score与待比较对象score的差值
     */
    @Override
    public int compareTo(AbstractHit other) {
        return (int)(this.score - other.getScore());
    }
}
