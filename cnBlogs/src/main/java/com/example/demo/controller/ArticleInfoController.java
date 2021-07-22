package com.example.demo.controller;

import com.example.demo.config.APPFinal;
import com.example.demo.mapper.ArticleInfoMapper;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -07 -21
 * Time: 23:47
 */

@RestController
@RequestMapping("/art")
public class ArticleInfoController {

    @Resource
    private ArticleInfoMapper articleInfoMapper;

    //根据文章id删除文章
    @RequestMapping("/del")
    public int delById(@RequestParam int id) {
        return articleInfoMapper.delById(id);
    }

    //查询文章详情
    @RequestMapping("/detail")
    public ArticleInfo detail(@RequestParam int id) {
        return articleInfoMapper.detail(id);
    }

    //添加文章
    @RequestMapping("/add")
    public ArticleInfo add(@RequestParam String title,
                           @RequestParam String content,
                           HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        //登录的用户信息
        Object u = null;
        if (session == null ||
                (u = session.getAttribute(APPFinal.USERINFO_SESSIONKEY)) == null) {
            return null;
        }
        User user = (User) u;
        ArticleInfo articleinfo = new ArticleInfo();
        articleinfo.setTitle(title);
        articleinfo.setContent(content);
        articleinfo.setUid(user.getId());
        articleInfoMapper.add(articleinfo);
        return articleinfo;
    }

    //修改文章
    @RequestMapping("/update")
    public ArticleInfo update(ArticleInfo articleInfo) {
        articleInfoMapper.update(articleInfo);
        return articleInfo;
    }

}
