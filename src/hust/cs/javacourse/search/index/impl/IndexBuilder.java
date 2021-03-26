package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractIndexBuilder;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;

public class IndexBuilder extends AbstractIndexBuilder{
    public IndexBuilder(AbstractDocumentBuilder documentBuilder) {
        super(documentBuilder);
    }

    @Override
    public AbstractIndex buildIndex(String rootDirectory) {
        Index index = new Index();
        // TODO: parse files
        return index;
    }
}
