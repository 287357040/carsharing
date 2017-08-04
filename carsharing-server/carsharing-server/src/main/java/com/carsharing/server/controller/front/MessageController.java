package com.carsharing.server.controller.front;

import com.carsharing.server.constant.SystemCode;
import com.carsharing.server.controller.AbstractController;
import com.carsharing.server.entity.User;
import com.carsharing.server.entity.UserMessage;
import com.carsharing.server.service.MessageService;
import com.carsharing.server.util.JsonResponse;
import com.carsharing.server.util.SessionUtil;
import org.apache.catalina.Session;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/front/message")
public class MessageController extends AbstractController {

    @Resource
    private MessageService messageService;

    @RequestMapping(method = {RequestMethod.GET},value = "getMessage")
    public JsonResponse<List<UserMessage>> getMessage(HttpServletRequest request){
        JsonResponse<List<UserMessage>> result = new JsonResponse<List<UserMessage>>(SystemCode.FAILURE);
        User user = SessionUtil.getUser(request);

        List<UserMessage> msgs = messageService.getMessage(user.getUserNo());

        result.setObj(msgs);
        result.setRes(SystemCode.SUCCESS);
        return result;
    }

    @RequestMapping(method = {RequestMethod.GET},value = "updateMessageState")
    public JsonResponse<List<UserMessage>> updateMessageState(UserMessage message,HttpServletRequest request){
        JsonResponse<List<UserMessage>> result = new JsonResponse<List<UserMessage>>(SystemCode.FAILURE);
        User user = SessionUtil.getUser(request);
        if(!user.getUserNo().equals(message.getUserNo())){
            result.setRes(SystemCode.NO_PRI);
            return  result;
        }
        try {
            messageService.updateByPrimaryKey(message);
        } catch (Exception e) {
            return result;
        }

        result.setRes(SystemCode.SUCCESS);
        return result;
    }
}
