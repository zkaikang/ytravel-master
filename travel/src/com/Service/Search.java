package com.Service;

import com.Dao.Dao;
import com.entity.Post;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by worith on 2018/7/10.
 */
public class Search extends Dao {
    public void createIndex(Post post) throws IOException {
        // 建立索引
        Directory dir = null;

        dir = FSDirectory.open(Paths.get("./indexDir"));

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = null;

        indexWriter = new IndexWriter(dir, indexWriterConfig);

        //把数据存成对象置入索引库

        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(post.getpNum()) , Field.Store.YES));
        doc.add(new TextField("title", post.getpTitle(), Field.Store.YES));
        doc.add(new TextField("content", post.getContent(), Field.Store.YES));
        //doc.add(new Field("date",dateToString(ques.getqDate(),DateTools.Resolution.DAY),Field.Store.YES));
        indexWriter.addDocument(doc);


        indexWriter.close();

    }


    /**
     * 带有高亮显示的分页查询
     *
     * @param queryString
     *            待查询的字符串
     * @param firstResult
     *            开始位置
     * @param maxResult
     *            页面记录最大数量
     * @return
     */
    public List<Post> searchWithHighLighter(String queryString) {
        try {
            // 1.queryString -->>Query
            String[] queryFields = new String[] { "title", "content" };
            Analyzer analyzer = new IKAnalyzer();
//            analyzer.setVersion(Version.LUCENE_6_1_0);
            QueryParser queryParser = new MultiFieldQueryParser(queryFields,  analyzer);
            Query query = queryParser.parse(queryString);
            // 2. 查询，得到topDocs
            DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get("./indexDir/")));
            IndexReader indexReader = directoryReader;
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            TopDocs topDocs = indexSearcher.search(query, 100);
            // 3.处理结果并返回
            int totalHits = topDocs.totalHits;
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            List<Post> posts = new ArrayList<Post>();
     /*       int upperBound = (firstResult + maxResult) < scoreDocs.length ? (firstResult + maxResult)
                    : scoreDocs.length;
            firstResult = (firstResult >= 0 ? firstResult : 0);
            // -------------------------------高亮操作
            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            Scorer scorer = new QueryScorer(query);
            Highlighter highLighter = new Highlighter(formatter, scorer);
            // 第二个参数默认为100，即指定显示的摘要的文字的大小
            Fragmenter fragmenter = new SimpleFragmenter(100);
            // ---------------------------------高亮配置结束
            highLighter.setTextFragmenter(fragmenter);
            for (int i = firstResult; i < upperBound; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];*/
            for(ScoreDoc scoreDoc : scoreDocs){
                Document doc = indexSearcher.doc(scoreDoc.doc);
                // 监测有没有目标词
                String text = doc.get("content");
                String text1=doc.get("title");
             /*   // 这里的操作和3.0版本的不一致
                String content = "";
                String title="";
                if (text != null) {
                    TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
                    content = highLighter.getBestFragment(tokenStream,text);
                }
                if (text1 != null) {
                    TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(text1));
                    title = highLighter.getBestFragment(tokenStream,text1);
                }*/
                Post p = new Post();
                p.setpNum(Integer.parseInt(doc.get("id")));
                p.setpTitle(text1);
                p.setContent(text);
                System.out.println(p.toString());
                posts.add(p);

            }
            indexReader.close();
             analyzer.close();
             return posts;

        } catch (Exception e) {
            throw new RuntimeException("QuesttionIndexDao-->> search方法出错！\n" + e);
        }
    }
}
