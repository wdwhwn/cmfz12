package com.baizhi.dao;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Administrator on 2018/10/31.
 */
public class TestAnalyzer {
    String text="中国河南科技学案";
    @Test
    public void testStandardAnalyzer() throws IOException {
      test(new StandardAnalyzer(Version.LUCENE_44),text);
        System.out.println(StandardAnalyzer.STOP_WORDS_SET);
    }
    //中日韩分词器 二分分词器
     @Test
    public void testCJKAnalyzer() throws IOException {
        test(new CJKAnalyzer(Version.LUCENE_44),text);
     }

     //smartCN
     @Test
     public void testSmartChineseAnalyzer() throws IOException {
         test(new SmartChineseAnalyzer(Version.LUCENE_44),text);
         //刘德华
         //马德华
     }
     //庖丁解牛  自定义 停用词  关键词
    @Test
    public void testIKAnalyzer() throws IOException {
      test(new IKAnalyzer(),text);
    }




    public static void  test(Analyzer analyzer, String text) throws IOException {

        System.out.println("当前分词器:--->"+analyzer.getClass().getName());

        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));

        tokenStream.addAttribute(CharTermAttribute.class);

        tokenStream.reset();
        while(tokenStream.incrementToken()){
            CharTermAttribute attribute = tokenStream.getAttribute(CharTermAttribute.class);
            System.out.println(attribute.toString());
        }

        tokenStream.end();
    }






}
