package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleScanner;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.StringSplitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <pre>
 *     TermTupleScanner继承于AbstractTermTupleScanner抽象父类，是一个AbstractTermTupleStream流对象，
 *     它利用java.io.BufferedReader去读取文本文件得到一个个三元组TermTuple.
 * </pre>
 */
public class TermTupleScanner extends AbstractTermTupleScanner{
    private int curPos = 0;
    private Queue<TermTuple> queue = new LinkedList<>();

    /**
     * 默认构造函数
     */
    public TermTupleScanner() {

    }

    /**
     * 构造函数
     * @param bufferedReader 输入流，bufferedReader对象
     */
    public TermTupleScanner(BufferedReader bufferedReader) {
        super(bufferedReader);
    }

    /**
     * 返回下一个三元组
     * @return 三元组
     */
    public AbstractTermTuple next() {
        if (this.input != null) {
            if (this.queue.isEmpty()) {
                String line = "";
                try {
                    do {
                        line = this.input.readLine();
                        if (line == null)
                            return this.queue.isEmpty() ? null : this.queue.poll();
                    } while (line.trim().length() == 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StringSplitter splitter = new StringSplitter();
                splitter.setSplitRegex(Config.STRING_SPLITTER_REGEX);
                List<String> words = splitter.splitByRegex(line);
                for (var word : words)
                    if (Config.IGNORE_CASE)
                        this.queue.add(new TermTuple(new Term(word.toLowerCase()), this.curPos++));
                    else
                        this.queue.add(new TermTuple(new Term(word), this.curPos++));
            }
            return this.queue.poll();
        }
        return null;
    }

    public static void main(String[] args) {
        File file = new File(Config.DOC_DIR + "stop.txt");
        try {
            TermTupleScanner scanner = new TermTupleScanner(new BufferedReader(new FileReader(file)));
            while (true) {
                AbstractTermTuple termTuple = scanner.next();
                if (termTuple == null) return;
                System.out.println(termTuple.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
