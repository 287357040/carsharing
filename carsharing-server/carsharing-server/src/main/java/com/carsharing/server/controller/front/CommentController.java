package com.carsharing.server.controller.front;

import com.carsharing.server.util.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hucl on 2017/7/19.
 */
@RestController
@RequestMapping("/front/comment")
public class CommentController {

    /**
     * 新增评论
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},value = "/addComment")
    public JsonResponse<String> addComment(){

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

    /**
     * 删除评论，只能删除登录用户自己的评论
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},value = "/delComment")
    public JsonResponse<String> delComment(){

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }
}
