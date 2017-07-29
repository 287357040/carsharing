package com.carsharing.server.mapper;

import com.carsharing.server.entity.Comment;

import java.util.List;

/**
 * Created by hucl on 2017/7/12.
 */
public interface CommentMapper extends BaseDao<Comment> {

    public List<Comment> selectByDemandId(int demandId);
    public List<Comment> selectByRouteId(int routeId);

    public boolean insertService(Comment Comment);

    public boolean deleteService(int comment_id);
}
