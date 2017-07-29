package com.carsharing.server.service.impl;

import com.carsharing.server.entity.Comment;
import com.carsharing.server.entity.Driver;
import com.carsharing.server.mapper.BaseDao;
import com.carsharing.server.mapper.CommentMapper;
import com.carsharing.server.mapper.DriverMapper;
import com.carsharing.server.service.CommentService;
import com.carsharing.server.service.DriverService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hucl on 2017/7/12.
 */
@Transactional
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    private static Logger logger = Logger.getLogger(CommentServiceImpl.class);
    @Resource
    private CommentMapper commentMapper;

    @Override
    protected BaseDao<Comment> getMapper() {
        return commentMapper;
    }

    @Override
    public void insertService(Comment comment) {
        commentMapper.insertService(comment);
    }

    @Override
    public void deleteService(int comment_id) {commentMapper.deleteService(comment_id);
    }

    @Override
    public List<Comment> selectByDemandId(int demandId) {
        return commentMapper.selectByDemandId(demandId);
    }

    @Override
    public List<Comment> selectByRouteId(int routeId) {
        return commentMapper.selectByRouteId(routeId);
    }

    public CommentMapper CommentMapper() {
        return commentMapper;
    }

    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

}
