package com.baizhi.dao;

import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;

import org.junit.Test;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/10/31.
 */
public class LuceneTest2 {
    @Test
    public void testCreateIndex() {
        //创建索引
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document document = new Document();
        document.add(new StringField("id", "5", Field.Store.YES));
        document.add(new StringField("title", "背影", Field.Store.YES));
        document.add(new StringField("author", "朱自清", Field.Store.YES));
        //Field.Store.NO    元数据区中不存储该属性
        document.add(new TextField("content", "你站在这里不要动，我去对面给你买几个橘子", Field.Store.YES));
        document.add(new StringField("date", "今天", Field.Store.YES));
        try {
            indexWriter.addDocument(document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }

    }

    @Test
    public void testIndexSearcher() {
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        Query query = new TermQuery(new Term("desc", "中国"));
        System.out.println("134567984");
        TopDocs topDocs = null;
        try {
            topDocs = indexSearcher.search(query, 100);
            System.out.println("12348965132");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        System.out.println(Arrays.toString(scoreDocs));
        for (int i = 0; i < scoreDocs.length; i++) {
            ScoreDoc scoreDoc = scoreDocs[i];
            int doc = scoreDoc.doc;
            Document document = null;
            try {
                document = indexSearcher.doc(doc);
            } catch (IOException e) {
                e.printStackTrace();
            }
            float score = scoreDoc.score;
            System.out.println("score=========" + score);
            String id = document.get("id");
            System.out.println("这是id=========" + id);
            String title = document.get("desc");
            System.out.println("这是title=========" + title);
            String content = document.get("location");
            System.out.println("这是content=========" + content);
            String author = document.get("status");
            System.out.println("这是author=========" + author);
            System.out.println("======================================");
            String date = document.get("price");
            System.out.println("date=========" + date);
        }

    }

    @Test
    public void delete() {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
//        indexWriter.deleteAll();
        try {
            indexWriter.deleteDocuments(new Term("id", "3"));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }

    }

    //先删除  再添加

    @Test
    public void update() {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document document = new Document();
        document.add(new StringField("id", "3", Field.Store.YES));
        document.add(new StringField("title", "背影3", Field.Store.YES));
        document.add(new StringField("author", "朱自清", Field.Store.YES));
        document.add(new TextField("content", "你站在这里不要动，我去对面给你买几个橘子", Field.Store.YES));
        try {
            indexWriter.updateDocument(new Term("id", "3"), document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }


    }


}
