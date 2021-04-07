package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTerm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <pre>
 * Term是Term对象的类.
 *      Term对象表示文本文档里的一个单词.
 *      实现了下面二个接口:
 *          Comparable：可比较大小（字典序）,为了加速检索过程，字典需要将单词进行排序.
 *          FileSerializable：可序列化到文件或从文件反序列化.
 *   </pre>
 */
public class Term extends AbstractTerm {
    /**
     * 默认构造函数
     */
    public Term() {
    }

    /**
     * 构造函数
     *
     * @param content Term的内容
     */
    public Term(String content) {
        super(content);
    }

    /**
     * 返回Term的字符串表示
     *
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return getContent();
    }

    /**
     * 返回Term的内容
     *
     * @return Term内容
     */
    @Override
    public String getContent() {
        return this.content;
    }

    /**
     * 设置Term的内容
     *
     * @param content：Term的内容
     */
    @Override
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 判断当前对象是否与另一个Term对象相等，当内容一致时认为两个对象相等
     *
     * @param other 另一个Term对象
     * @return 相等则返回 true
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != getClass()) return false;

        return this.content.equals(((Term) other).getContent());
    }

    /**
     * 判断当前对象与另一个Term对象的大小，根据内容比较
     *
     * @param other 另一个Term对象
     * @return 返回内容比较的结果
     */
    @Override
    public int compareTo(AbstractTerm other) {
        return this.content.compareTo(other.getContent());
    }

    /**
     * 以二进制形式写入到输出流
     *
     * @param out :输出流对象
     */
    @Override
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeObject(getContent());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * 以二进制形式从输入流中读取
     *
     * @param in ：输入流对象
     */
    @Override
    public void readObject(ObjectInputStream in) {
        try {
            setContent((String) in.readObject());
        } catch (ClassNotFoundException | IOException i) {
            i.printStackTrace();
        }
    }
}
