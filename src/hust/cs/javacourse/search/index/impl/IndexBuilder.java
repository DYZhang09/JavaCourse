package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractIndexBuilder;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.FileUtil;

import java.io.File;
import java.util.List;

public class IndexBuilder extends AbstractIndexBuilder{
    public IndexBuilder(AbstractDocumentBuilder documentBuilder) {
        super(documentBuilder);
    }

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

    public static void main(String[] args) {
        IndexBuilder builder = new IndexBuilder(new DocumentBuilder());
        AbstractIndex index = builder.buildIndex(Config.DOC_DIR);
        System.out.println(index);

        AbstractIndex index1 = new Index();
        index1.load(new File(Config.INDEX_DIR + "index.idx"));
        System.out.println(index1);
    }
}
