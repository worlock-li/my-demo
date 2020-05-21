package com.stady.blog.service;

import com.stady.blog.pojo.Comment;

import java.util.List;

/**
 * @author li
 */
public interface CommentService {

    /**
     * 根据blog的id来获取当前博客的所有评论
     * @param blogId
     * @return
     */
    List<Comment> listCommentsByBlogId(Long blogId);

    /**
     * 保存评论
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);
}
