package hust.cs.javacourse.search.run;

import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractPostingList;
import hust.cs.javacourse.search.index.impl.Index;
import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.AbstractIndexSearcher;
import hust.cs.javacourse.search.query.Sort;
import hust.cs.javacourse.search.query.impl.IndexSearcher;
import hust.cs.javacourse.search.query.impl.SimpleSorter;
import hust.cs.javacourse.search.util.Config;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

/**
 * 测试搜索
 */
public class TestSearchIndex {
    /**
     *  搜索程序入口
     * @param args ：命令行参数
     */
    public static void main(String[] args){
        AbstractIndexSearcher searcher = new IndexSearcher();
        searcher.open(Config.INDEX_DIR + "index.dat");
        AbstractHit[] hits = searcher.search(new Term("coronavirus"), new SimpleSorter());
        for (var hit : hits) {
            System.out.print(hit);
            System.out.println();
        }
    }
}
