package hust.cs.javacourse.search.query.impl;

import hust.cs.javacourse.search.query.AbstractHit;
import hust.cs.javacourse.search.query.Sort;

import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *     对命中结果按照其score从高到低排序的排序器，实现Sort接口，打分策略是
 *     根据Term的出现频率之和打分
 * </pre>
 */
public class SimpleSorter implements Sort{
    /**
     * 对命中结果根据score进行排序
     * @param hits ：命中结果集合
     */
    @Override
    public void sort(List<AbstractHit> hits) {
        for (var hit : hits) {
            hit.setScore(this.score(hit));
        }
        hits.sort(null);
        Collections.reverse(hits);
    }

    /**
     * 给命中文档打分，根据其Term-Posting map里面Term出现词频的总数打分
     * @param hit ：命中文档
     * @return score
     */
    @Override
    public double score(AbstractHit hit) {
        double res = 0;
        for (var posting : hit.getTermPostingMapping().values())
            res += posting.getFreq();
        return res;
    }
}
