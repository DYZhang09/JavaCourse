package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * <pre>
 * PostingList是PostingList对象的类.
 *      PostingList对象包含了一个单词的Posting列表.
 *      实现了下面接口:
 *          FileSerializable：可序列化到文件或从文件反序列化.
 * </pre>
 */
public class PostingList extends AbstractPostingList {
    /**
     * 默认构造函数
     */
    public PostingList() {
    }

    /**
     * 添加一个Posting对象
     *
     * @param posting：Posting对象
     */
    @Override
    public void add(AbstractPosting posting) {
        if (posting == null) return;
        if (!this.list.contains(posting))
            this.list.add(posting);
    }

    /**
     * 返回PostingList的字符串表示
     *
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return "PostingList: " + this.list.toString();
    }

    /**
     * 加入Posting列表中的所有Posting对象
     *
     * @param postings：Posting列表
     */
    @Override
    public void add(List<AbstractPosting> postings) {
        for (var posting : postings)
            this.add(posting);
    }

    /**
     * 返回下标对应的Posting对象
     *
     * @param index ：下标
     * @return Posting对象
     */
    @Override
    public AbstractPosting get(int index) {
        return this.list.get(index);
    }

    /**
     * 返回指定Posting对象的下标
     *
     * @param posting：指定的Posting对象
     * @return Posting对象的下标
     */
    @Override
    public int indexOf(AbstractPosting posting) {
        return this.list.indexOf(posting);
    }

    /**
     * 根据文档id返回对应的Posting对象的下标
     *
     * @param docId ：文档id
     * @return 文档id对应Posting对象的下标
     */
    @Override
    public int indexOf(int docId) {
        int idx = 0;
        for (AbstractPosting posting : this.list) {
            if (posting.getDocId() == docId)
                return idx;
            idx++;
        }
        return -1;
    }

    /**
     * 判断Posting对象是否包含在此PostingList中
     *
     * @param posting： 指定的Posting对象
     * @return 如果包含则返回 true
     */
    @Override
    public boolean contains(AbstractPosting posting) {
        return this.list.contains(posting);
    }

    /**
     * 从PostingList中删除指定的Posting对象
     *
     * @param posting ：指定的Posting对象
     */
    @Override
    public void remove(AbstractPosting posting) {
        this.list.remove(posting);
    }

    /**
     * 从PostingList中删除指定的下标的Posting对象
     *
     * @param index：指定的下标
     */
    @Override
    public void remove(int index) {
        this.list.remove(index);
    }

    /**
     * PostingList的长度
     *
     * @return 长度
     */
    @Override
    public int size() {
        return this.list.size();
    }

    /**
     * 清空PostingList
     */
    @Override
    public void clear() {
        this.list.clear();
    }

    /**
     * 判断PostingList是否为空
     *
     * @return 如果是空则返回true
     */
    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * 将PostingList排序
     */
    @Override
    public void sort() {
        this.list.sort(null);
    }

    /**
     * 以二进制形式写入到输出流中
     *
     * @param outputStream 输出流
     */
    @Override
    public void writeObject(ObjectOutputStream outputStream) {
        try {
            outputStream.writeObject(this.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从二进制形式从输入流中读取
     *
     * @param inputStream 输入流
     */
    @Override
    public void readObject(ObjectInputStream inputStream) {
        try {
            this.list = (List<AbstractPosting>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
