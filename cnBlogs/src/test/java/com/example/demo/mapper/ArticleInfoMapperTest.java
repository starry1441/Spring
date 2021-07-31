package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -07 -21
 * Time: 12:22
 */

@SpringBootTest
@Transactional
class ArticleInfoMapperTest {

    @Resource
    private ArticleInfoMapper articleInfoMapper;

    @Test
    void getAll() {
        List<ArticleInfo> list = articleInfoMapper.getAll();
        list.forEach(System.out::println);
    }

    @Test
    void getArticleInfo() {
        List<ArticleInfo> list = articleInfoMapper.getArticleInfo("java",null,0);
        list.forEach(System.out::println);
    }

    @Test
    void getArticleInfo2() {
        List<ArticleInfo> list = articleInfoMapper.getArticleInfo2("你好",null,0);
        list.forEach(System.out::println);
    }

    @Test
    void addArticle() {
        articleInfoMapper.addArticle("你好","个人博客项目",1,0,0);
    }

    @Test
    void getArticleInfo3() {
        List<ArticleInfo> list = articleInfoMapper.getArticleInfo3("你好","个人博客项目",0);
        list.forEach(System.out::println);
    }

    @Test
    void upArticle() {
        int result = articleInfoMapper.upArticle(3,"您好",null);
        System.out.println(result);
    }

    @Test
    void delArticleByIds() {
        int result = articleInfoMapper.delArticleByIds(new int[]{1,2,3});
        System.out.println(result);
    }
}