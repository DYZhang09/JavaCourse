package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractIndexBuilder;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.FileUtil;

import java.io.File;
import java.util.List;

/**
 * <pre>
 * IndexBuilder是索引构造器的类, 完成索引构造的工作
 * </pre>
 */
public class IndexBuilder extends AbstractIndexBuilder {
    /**
     * 构造函数
     *
     * @param documentBuilder AbstractDocumentBuilder子类的对象
     */
    public IndexBuilder(AbstractDocumentBuilder documentBuilder) {
        super(documentBuilder);
    }

    /**
     * 为指定目录下的所有文本文件建立倒排索引
     *
     * @param rootDirectory ：指定目录
     * @return 建立完毕的倒排索引
     */
    @Override
    public AbstractIndex buildIndex(String rootDirectory) {
        AbstractIndex index = new Index();
        List<String> files = FileUtil.list(rootDirectory, ".txt");
        for (var file : files) {
            index.addDocument(this.docBuilder.build(this.docId++, file, new File(file)));
        }
        index.save(new File(Config.INDEX_DIR + "index.idx"));
        return index;
    }

    /**
     * 简单测试
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        IndexBuilder builder = new IndexBuilder(new DocumentBuilder());
        AbstractIndex index = builder.buildIndex(Config.DOC_DIR);
        System.out.println(index);

        AbstractIndex index1 = new Index();
        index1.load(new File(Config.INDEX_DIR + "index.idx"));
        System.out.println(index1);
    }
}
