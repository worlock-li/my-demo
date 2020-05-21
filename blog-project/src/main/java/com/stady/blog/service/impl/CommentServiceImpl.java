package com.stady.blog.service.impl;

import com.stady.blog.dao.CommentRepository;
import com.stady.blog.pojo.Comment;
import com.stady.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author li
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentsByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return eachComment(comments);
    }

    @Override
    public Comment saveComment(Comment comment) {
        // 首先获取comment对象里面的parentComment的id，
        Long parentId = comment.getParentComment().getId();
        // 如果没有父级评论， 前端初始化是-1
        if (parentId != -1) {
            // 有父级评论， 将 通过id来获取到的父级评论 set到当前的comment中， 来保证子父级关系
            comment.setParentComment(commentRepository.getOne(parentId));
        } else {
            // 如果父级评论的id为-1， 则这是一条顶级评论， 将其父级comment设为 null
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    /**
     * 循环每个顶级的p评论节点
     * 主要作用就是将查询出来的所有comment都copy到一个新的集合当中
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            c.setReplayComments(comment.getReplyComments());
            commentsView.add(c);
        }
        // 合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments
     */
    private void combineChildren(List<Comment> comments) {
        // 首先循环所有的comment， 这是第一级
        for (Comment comment : comments) {
            // 拿到其子回复集合， 这是第二级
            List<Comment> replys1 = comment.getReplyComments();
            // 循环子回复集合
            for (Comment reply1 : replys1) {
                // 循环迭代， 找出子集， 存放到tempReplys中
                recursively(reply1);
            }
            comment.setReplayComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
    }

    /**
     * 临时存放区
     */
    private List<Comment> tempReplys = new ArrayList<>();

    private void recursively(Comment comment) {
        tempReplys.add(comment);
        if(comment.getReplyComments().size() > 0){
            List<Comment> replayComment = comment.getReplyComments();
            for (Comment replay : replayComment) {
                recursively(replay);
            }
        }
    }

}
