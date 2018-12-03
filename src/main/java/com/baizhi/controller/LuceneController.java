
package com.baizhi.controller;
import com.baizhi.entity.Product;
import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdwhwn on 2018/10/31.
 */
@Controller
@RequestMapping("/lucene")
public class LuceneController {
    //    查询
    @RequestMapping("/query")
    @ResponseBody
    public List<Product> query(String text) throws ParseException {
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        String[] strs ={"location","desc","status"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, strs, new IKAnalyzer());
        //只能根据某一列查询   高级查询
        Query query = multiFieldQueryParser.parse(text);
        List<Product> products = new ArrayList<>();
        try {
            TopDocs topDocs = indexSearcher.search( query, 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                int doc = scoreDocs[i].doc;
                Document document = indexSearcher.doc(doc);
                Product product = getProFromDoc(document);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(products);
        return products;
    }

//  删除
    public void delete(String id) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term("id", id));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }


//  修改
    public void update(Product product) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document document = getDocFormPro(product);
        try {
            indexWriter.updateDocument(new Term("id", product.getId()), document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }



//  保存  添加
    @RequestMapping("/save")
    @ResponseBody
    public void save(Product product) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFormPro=getDocFormPro(product);
        System.out.println(docFormPro);
        try {
            indexWriter.addDocument(docFormPro);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }


    }

//    通过doc获取pro
    public Product getProFromDoc(Document document) {
        Product product = new Product();
        product.setLocation(document.get("location"));
        product.setStatus(document.get("status"));
        product.setPrice(document.get("price"));
        product.setDesc(document.get("desc"));
        return product;
    }

    //通过pro获取doc
    public Document getDocFormPro(Product product) {
        Document document = new Document();
        document.add(new TextField("desc", product.getDesc(), Field.Store.YES));
        document.add(new TextField("price", product.getPrice(), Field.Store.YES));
        document.add(new TextField("status", product.getStatus(), Field.Store.YES));
        document.add(new TextField("location", product.getLocation(), Field.Store.YES));
        return document;
    }
}
