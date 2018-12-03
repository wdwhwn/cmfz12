package com.baizhi.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/10/31.
 */
public class LuceneUtil {
    static Directory directory;
    static Version version;
    static Analyzer analyzer;
    static IndexWriterConfig indexWriterConfig;


    static {
        try {
            directory = FSDirectory.open(new File("d://index"));
            version = Version.LUCENE_44;
            analyzer = new IKAnalyzer();
            indexWriterConfig = new IndexWriterConfig(version, analyzer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static IndexWriter getIndexWriter() {
        IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(directory, indexWriterConfig);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    public static IndexSearcher getIndexSearcher() {
        IndexSearcher indexSearcher = null;
        try {
            IndexReader indexReader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(indexReader);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexSearcher;
    }


    public static void commit(IndexWriter indexWriter){
        try {
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(IndexWriter indexWriter){
        try {
            indexWriter.rollback();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
