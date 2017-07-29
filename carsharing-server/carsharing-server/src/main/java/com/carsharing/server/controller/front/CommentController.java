package com.carsharing.server.controller.front;

import com.carsharing.server.constant.SystemCode;
import com.carsharing.server.controller.AbstractController;
import com.carsharing.server.entity.Comment;
import com.carsharing.server.service.CommentService;
import com.carsharing.server.util.JsonResponse;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hucl on 2017/7/19.
 */
@RestController
@RequestMapping("/front/comment")
public class CommentController extends AbstractController {
    private Logger lo = Logger.getLogger(CommentController.class);
    @Resource
    private CommentService commentService;
    /**
     * 新增评论
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},value = "/addComment")
    public JsonResponse<String> addComment(Comment comment, HttpServletRequest request){
        JsonResponse<String>  result = new JsonResponse<String>( SystemCode.FAILURE);
        try {
                commentService.insertService(comment);
            result.setRes(SystemCode.SUCCESS);
        }
        catch (Exception e)
        {
            lo.error("增加评论操作失败", e);
            logError(request, "[增加评论操作失败]", e);
        }
        return result;
    }

    /**
     * 删除评论，只能删除登录用户自己的评论
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},value = "/delComment")
    public JsonResponse<String> delComment(int demandId, HttpServletRequest request){

        JsonResponse<String> result = new JsonResponse<String>(SystemCode.FAILURE);

        try {
            commentService.deleteService(demandId);
            result.setRes(SystemCode.SUCCESS);
        }
        catch (Exception e)
        {
            lo.error("删除评论失败", e);
            logError(request, "[删除评论失败]", e);
        }
        return result;
    }


    /**
     * 查看评论，通过routeId
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},value = "/getCommentByRouteId")
    public JsonResponse<List<Comment>> getCommentByRouteId(int routeId, HttpServletRequest request){
        JsonResponse<List<Comment>> result = new JsonResponse<List<Comment>>(SystemCode.FAILURE);

        try {
          List<Comment> commentList=  commentService.selectByRouteId(routeId);
            result.setRes(SystemCode.SUCCESS);
            result.setObj(commentList);
        }
        catch (Exception e)
        {
            lo.error("查看评论失败", e);
            logError(request, "[查看评论失败]", e);
        }
        return result;
    }


    /**
     * 查看评论，通过routeId
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},value = "/getCommentByDemandId")
    public JsonResponse<List<Comment>> getCommentByDemandId(int demandId, HttpServletRequest request){
        JsonResponse<List<Comment>> result = new JsonResponse<List<Comment>>(SystemCode.FAILURE);

        try {
            List<Comment> commentList=  commentService.selectByDemandId(demandId);
            result.setRes(SystemCode.SUCCESS);
            result.setObj(commentList);
        }
        catch (Exception e)
        {
            lo.error("查看评论失败", e);
            logError(request, "[查看评论失败]", e);
        }
        return result;
    }
}
