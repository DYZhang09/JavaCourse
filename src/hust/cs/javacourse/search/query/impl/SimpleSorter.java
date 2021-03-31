package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.Sort;

import java.util.Collections;
import java.util.List;

public class SimpleSorter implements Sort{
    @Override
    public void sort(List<AbstractHit> hits) {
        for (var hit : hits) {
            hit.setScore(this.score(hit));
        }
        hits.sort(null);
        Collections.reverse(hits);
    }

    @Override
    public double score(AbstractHit hit) {
        double res = 0;
        for (var posting : hit.getTermPostingMapping().values())
            res += posting.getFreq();
        return res;
    }
}
