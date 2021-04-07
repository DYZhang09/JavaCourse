package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;

import java.io.*;
import java.util.List;

/**
 * <pre>
 * Posting是Posting对象的类.
 *      Posting对象代表倒排索引里每一项， 一个Posting对象包括:
 *          包含单词的文档id.
 *          单词在文档里出现的次数.
 *          单词在文档里出现的位置列表（位置下标不是以字符为编号，而是以单词为单位进行编号.
 *      实现了下面二个接口:
 *          Comparable：可比较大小（按照docId大小排序）,
 *          FileSerializable：可序列化到文件或从文件反序列化
 *  </pre>
 */
public class Posting extends AbstractPosting {
    /**
     * 默认构造函数
     */
    public Posting() {

    }

    /**
     * 构造函数
     *
     * @param docId     文档id
     * @param freq      对应Term的频率
     * @param positions Term出现的位置的链表(以单词为单位)
     */
    public Posting(int docId, int freq, List<Integer> positions) {
        super(docId, freq, positions);
    }

    /**
     * 获得文档id
     *
     * @return 文档id
     */
    @Override
    public int getDocId() {
        return this.docId;
    }

    /**
     * 设置文档id
     *
     * @param docId：包含单词的文档id
     */
    @Override
    public void setDocId(int docId) {
        this.docId = docId;
    }

    /**
     * 获得单词的词频
     *
     * @return 单词词频
     */
    @Override
    public int getFreq() {
        return this.freq;
    }

    /**
     * 设置单词词频
     *
     * @param freq:单词在文档里出现的次数
     */
    @Override
    public void setFreq(int freq) {
        this.freq = freq;
    }

    /**
     * 获得单词在文档中出现的位置
     *
     * @return 出现位置的链表(以单词为单位)
     */
    @Override
    public List<Integer> getPositions() {
        return this.positions;
    }

    /**
     * 设置单词在文档中出现的位置
     *
     * @param positions：单词在文档里出现的位置列表
     */
    @Override
    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    /**
     * 判断当前对象是否与另一个对象相等，只有当两个对象对应的docId，单词词频以及出现位置
     * 完全一致的时候才认为两个对象相等
     *
     * @param object 待判断的对象
     * @return 如果相等则返回 true
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (object.getClass() != getClass()) return false;

        Posting other = (Posting) object;
        return this.docId == other.docId && this.freq == other.freq
                && this.positions.size() == other.positions.size()
                && this.positions.containsAll(other.positions);
    }

    /**
     * 返回当前对象的字符串表示
     *
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return "" + "<docId: " + this.docId
                + ", freq: " + this.freq
                + ", positions: " + this.positions.toString()
                + ">";
    }

    /**
     * 比较当前对象与另一个对象的大小，按照docId比较
     *
     * @param o： 另一个Posting对象
     * @return 当前文档id与另一个对象文档id的差值
     */
    @Override
    public int compareTo(AbstractPosting o) {
        return this.docId - o.getDocId();
    }

    /**
     * 将当前对象的单词出现位置按从小到大排序
     */
    @Override
    public void sort() {
        this.positions.sort(null);
    }

    /**
     * 以二进制形式写入到输出流中
     *
     * @param outputStream 输出流
     */
    @Override
    public void writeObject(ObjectOutputStream outputStream) {
        try {
            outputStream.writeObject(getDocId());
            outputStream.writeObject(getFreq());
            outputStream.writeObject(getPositions());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以二进制形式从输入流中读取
     *
     * @param inputStream 输入流
     */
    @Override
    @SuppressWarnings("unchecked")
    public void readObject(ObjectInputStream inputStream) {
        try {
            setDocId((int) inputStream.readObject());
            setFreq((int) inputStream.readObject());
            setPositions((List<Integer>) inputStream.readObject());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
