/**
 * Created by lmz on 2017/7/11.
 */
const IP = 'http://47.94.194.81:8080/carsharing';
//const IP = 'http://192.168.153.157:8080';

export const API_ROOT = (process.env.NODE_ENV === 'production') ? IP : IP;

//用户手机验证码登录
export const loginByCodeUrl = API_ROOT + '/front/user/loginByCode';

//用户账户密码登录
export const loginByAcctUrl = API_ROOT + '/front/user/loginByAcct';

//用户登出
export const logoutUrl = API_ROOT + '/front/user/logout';

//更新当前登录用户信息
export const updateUserInfoUrl = API_ROOT + '/front/user/updateUserInfo';

//更新当前登录用户头像
export const updateUserIconUrl = API_ROOT + '/front/user/updateUserIcon';

// 乘客退出司机路线
export const exitRouteUrl = API_ROOT + '/front/Route/exitRoute';
// 乘客加入司机拼车路线
export const joinRouteUrl = API_ROOT + '/front/Route/joinRoute';

//修改密码
export const changePasswordUrl = API_ROOT + '/front/user/changePassword';

//当前登录用户设置地址
export const addNewAddressUrl = API_ROOT + '/front/useraddress/addNewAddress';

//更新登录用户设置地址
export const updateAddressUrl = API_ROOT + '/front/useraddress/updateAddress';

//查询登录用户地址
export const queryAddressUrl = API_ROOT + '/front/useraddress/queryAddress';

//注册为司机
export const regToDriverUrl = API_ROOT + '/front/driver/regToDriver';

//邀请乘客加入路线
export const inviteToRouteUrl = API_ROOT + '/front/route/inviteToRoute';

//更新司机车辆信息
export const updateDriverInfoUrl = API_ROOT + '/front/driver/updateDriverInfo';
//获取司机信息
export const getDriverByNoUrl = API_ROOT+'/front/driver/getDriverByNo';

//员工发布乘车需求
export const publishRideDemandUrl = API_ROOT + '/front/demand/publishRideDemand';

//员工取消乘车需求
export const deleteRideDemandUrl = API_ROOT + '/front/demand/deleteRideDemand';

//员工更新乘车需求
export const updateRideDemandUrl = API_ROOT + '/front/demand/updateRideDemand';

//获取当前登录用户乘车需求
export const getRideDemandsUrl = API_ROOT + '/front/demand/getRideDemands';

//根据路由Id来获取需求
export const getRideDemandsByRouteIdUrl = API_ROOT + '/front/demand/getRideDemandsByRouteId';

//根据路由信息来找到匹配的需求
export const matchRideDemandsByRouteUrl = API_ROOT + '/front/demand/matchRideDemandsByRoute';

//司机发布拼车路线
export const publishNewRouteUrl = API_ROOT + '/front/route/publishNewRoute';

//司机取消自己的拼车路线
export const deleteRouteUrl = API_ROOT + '/front/route/deleteRoute';

//司机开始拼车订单
export const startRouteUrl = API_ROOT + '/front/route/startRoute';

//司机结束拼车订单
export const finishRouteUrl = API_ROOT + '/front/route/finishRoute';

// 根据状态获取订单
export const getRideRoutesUrl = API_ROOT + '/front/route/getRideRoutes';

//根据需求信息来找到匹配拼车路线
export const matchRideRoutesByDemandUrl = API_ROOT + '/front/route/matchRideRoutesByDemand';

//增加评论(对司机发布的路线评论）
export const addCommentUrl = API_ROOT + '/front/comment/addComment';

//删除评论（待定）
export const delCommentUrl = API_ROOT + '/front/comment/delComment';

//获取需求的评论列表
export const getCommentOfDemandUrl = API_ROOT + '/front/comment/getCommentByDemandId';

//获取路线的评论列表
export const getCommentOfRouteUrl = API_ROOT + '/front/comment/getCommentByRouteId';

//增加评价

//查看员工评价

//关注好友 

//查看好友列表

//查看好友详情 

//查看信息
export const getMessageUrl = API_ROOT + '/front/message/getMessage';
//更新状态信息
export const UpdateMessageUrl = API_ROOT + '/front/message/UpdateMessage';

//用户是否登陆
export const isLoginInUrl = API_ROOT + '/front/user/isLoginIn';

//获取用户最新信息
export const getUserByNoUrl = API_ROOT + '/front/user/getUserByNo';
//获取用户最新信息
export const getFriendsUrl = API_ROOT + '/front/user/getFriends';
//获取用户最新信息
export const addFriendUrl = API_ROOT + '/front/user/addFriend';
//获取用户最新信息
export const delFriendUrl = API_ROOT + '/front/user/delFriend';

