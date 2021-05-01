package hust.cs.javacourse.search.run;

import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.AbstractIndexSearcher;
import hust.cs.javacourse.search.query.impl.IndexSearcher;
import hust.cs.javacourse.search.query.impl.SimpleSorter;
import hust.cs.javacourse.search.util.Config;

/**
 * 测试搜索
 */
public class TestSearchIndex {
    /**
     * 搜索程序入口
     *
     * @param args ：命令行参数
     */
    public static void main(String[] args) {
        AbstractIndexSearcher searcher = new IndexSearcher();
        searcher.open(Config.INDEX_DIR + "index.dat");
        AbstractHit[] hits = new AbstractHit[0];
        if (args.length == 1)
            hits = searcher.search(new Term(args[0]), new SimpleSorter());
        else if (args.length == 3)
            if (args[2].equals("OR"))
                hits = searcher.search(new Term(args[0]), new Term(args[1]),
                        new SimpleSorter(), IndexSearcher.LogicalCombination.OR);
            else if (args[2].equals("AND"))
                hits = searcher.search(new Term(args[0]), new Term(args[1]),
                        new SimpleSorter(), IndexSearcher.LogicalCombination.AND);
            else {
                System.out.println("Logical combination only supports <AND/OR>");
                return;
            }
        else {
            System.out.println("Usage:");
            System.out.println("TestSearchIndex <Word1> [<Word2> <LogicalCombination>]");
            return;
        }
        for (var hit : hits) {
            System.out.print(hit);
            System.out.println();
        }
    }
}
