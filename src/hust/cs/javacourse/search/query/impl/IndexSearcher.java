package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractPosting;
import hust.cs.javacourse.search.index.AbstractPostingList;
import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.AbstractIndexSearcher;
import hust.cs.javacourse.search.query.Sort;
import hust.cs.javacourse.search.util.Config;

import java.io.File;
import java.util.*;

public class IndexSearcher extends AbstractIndexSearcher {
    private List<AbstractHit> getIntersectionOfHitsList(List<AbstractHit> hits1, List<AbstractHit> hits2) {
        List<AbstractHit> intersection = new ArrayList<>();
        Object[] array1 = hits1.toArray();
        Object[] array2 = hits2.toArray();
        int i1 = 0, i2 = 0;
        while (i1 < array1.length && i2 < array2.length) {
            AbstractHit hit1 = (AbstractHit) array1[i1];
            AbstractHit hit2 = (AbstractHit) array2[i2];
            if (hit1.getDocId() == hit2.getDocId()) {
                intersection.add(hit1);
                i1++; i2++;
            }
            else if (hit1.getDocId() < hit2.getDocId()) i1++;
            else i2++;
        }
        return intersection;
    }

    private List<AbstractHit> getUnionOfHitsList(List<AbstractHit> hits1, List<AbstractHit> hits2) {
        List<AbstractHit> union = new ArrayList<>();
        Object[] array1 = hits1.toArray();
        Object[] array2 = hits2.toArray();
        int i1 = 0, i2 = 0;
        while (i1 < array1.length && i2 < array2.length) {
            AbstractHit hit1 = (AbstractHit) array1[i1];
            AbstractHit hit2 = (AbstractHit) array2[i2];
            if (hit1.getDocId() == hit2.getDocId()) {
                union.add(hit1);
                i1++; i2++;
            }
            else if (hit1.getDocId() < hit2.getDocId()) {
                i1++; union.add(hit1);
            }
            else {
                i2++; union.add(hit2);
            }
        }
        while (i1 < array1.length) union.add((AbstractHit)array1[i1++]);
        while (i2 <array2.length) union.add((AbstractHit)array2[i2++]);
        return union;
    }

    @Override
    public void open(String indexFile) {
        this.index.load(new File(indexFile));
    }

    @Override
    public AbstractHit[] search(AbstractTerm queryTerm, Sort sorter) {
        List<AbstractHit> hits = new ArrayList<>();
        AbstractPostingList postingList = this.index.search(queryTerm);
        if (postingList == null) return new AbstractHit[0];
        for (int i = 0; i < postingList.size(); ++i) {
            AbstractPosting posting = postingList.get(i);
            hits.add(new Hit(posting.getDocId(), this.index.getDocName(posting.getDocId()),
                    new TreeMap<>() {{
                        put(queryTerm, posting);
                    }}));
        }
        if (sorter != null)
            sorter.sort(hits);
        AbstractHit[] res = new AbstractHit[hits.size()];
        return hits.toArray(res);
    }

    @Override
    public AbstractHit[] search(AbstractTerm queryTerm1, AbstractTerm queryTerm2, Sort sorter, LogicalCombination combine) {
        List<AbstractHit> hits1 = Arrays.asList(this.search(queryTerm1, null));
        List<AbstractHit> hits2 = Arrays.asList(this.search(queryTerm2, null));
        List<AbstractHit> res = null;
        if (combine == LogicalCombination.OR)
            res = this.getUnionOfHitsList(hits1, hits2);
        else
            res = this.getIntersectionOfHitsList(hits1, hits2);
        if (sorter != null)
            sorter.sort(res);
        AbstractHit[] array = new AbstractHit[res.size()];
        return res.toArray(array);
    }

    public static void main(String[] args) {
        IndexSearcher searcher = new IndexSearcher();
        searcher.open(Config.INDEX_DIR + "index.idx");
        AbstractHit[] hits = searcher.search(new Term("bbb"), new Term("aaa"),
                new SimpleSorter(), LogicalCombination.ADN);
        for (var hit : hits)
            System.out.println(hit);
    }
}
