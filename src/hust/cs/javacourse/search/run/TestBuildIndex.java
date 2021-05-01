package hust.cs.javacourse.search.run;

import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.impl.DocumentBuilder;
import hust.cs.javacourse.search.index.impl.Index;
import hust.cs.javacourse.search.index.impl.IndexBuilder;
import hust.cs.javacourse.search.util.Config;

import java.io.*;

/**
 * 测试索引构建
 */
public class TestBuildIndex {
    /**
     *  索引构建程序入口
     * @param args : 命令行参数
     */
    public static void main(String[] args){
        IndexBuilder builder = new IndexBuilder(new DocumentBuilder());
        AbstractIndex index = builder.buildIndex(Config.DOC_DIR);
        System.out.print(index);
        try {
            index.writeObject(new ObjectOutputStream(new FileOutputStream(new File(Config.INDEX_DIR + "index.dat"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AbstractIndex index2 = new Index();
        try {
            index2.readObject(new ObjectInputStream(new FileInputStream(new File(Config.INDEX_DIR + "index.dat"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.print(index2);
    }
}
