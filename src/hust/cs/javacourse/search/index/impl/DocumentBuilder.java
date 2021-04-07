package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.parse.impl.LengthTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.PatternTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.StopWordTermTupleFilter;
import hust.cs.javacourse.search.parse.impl.TermTupleScanner;

import java.io.*;

/**
 * <pre>
 * DocumentBuilder是Document构造器的类，继承于AbstractDocumentBuilder抽象父类.
 *      Document构造器的功能是由解析文本文档得到的TermTupleStream，产生Document对象.
 * </pre>
 */
public class DocumentBuilder extends AbstractDocumentBuilder {
    /**
     * 默认构造函数
     */
    public DocumentBuilder() {
    }

    /**
     * 为指定文档构建一个Document对象
     *
     * @param docId           : 文档id
     * @param docPath         : 文档绝对路径
     * @param termTupleStream : 文档对应的TermTupleStream
     * @return Document对象
     */
    @Override
    public AbstractDocument build(int docId, String docPath, AbstractTermTupleStream termTupleStream) {
        AbstractDocument document = new Document();
        document.setDocId(docId);
        document.setDocPath(docPath);
        while (true) {
            AbstractTermTuple termTuple = termTupleStream.next();
            if (termTuple == null) return document;
            document.addTuple(termTuple);
        }
    }

    /**
     * 为指定的文档构建Document对象，会启用Term过滤
     *
     * @param docId   : 文档id
     * @param docPath : 文档绝对路径
     * @param file    : 文档对应File对象
     * @return Document对象
     */
    @Override
    public AbstractDocument build(int docId, String docPath, File file) {
        AbstractDocument document = new Document();
        document.setDocId(docId);
        document.setDocPath(docPath);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            AbstractTermTupleStream termTupleStream = new PatternTermTupleFilter(
                    new StopWordTermTupleFilter(
                            new LengthTermTupleFilter(
                                    new TermTupleScanner(bufferedReader))));
            document = this.build(docId, docPath, termTupleStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
