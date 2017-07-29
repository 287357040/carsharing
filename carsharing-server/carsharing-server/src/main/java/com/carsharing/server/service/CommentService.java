package com.carsharing.server.service;

import com.carsharing.server.entity.Comment;
import com.carsharing.server.entity.Driver;

import java.util.List;

/**
 * Created by hucl on 2017/7/12.
 */
public interface CommentService extends BaseService<Comment> {

    public void insertService(Comment Comment);
    public void deleteService(int comment_id);

    public List<Comment> selectByDemandId(int demandId);
    public List<Comment> selectByRouteId(int rountId);
}
