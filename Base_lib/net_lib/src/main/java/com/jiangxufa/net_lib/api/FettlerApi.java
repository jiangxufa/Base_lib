package com.jiangxufa.net_lib.api;

/*
 * 创建时间：2018/1/6
 * 编写人：lenovo
 * 功能描述：
 */

public interface FettlerApi {

//    /**
//     * 获取师傅端用户信息
//     *
//     * @param id 用户id
//     * @return UserFettler-AppDetial
//     */
//    @GET("fettler/{id}")
//    Observable<DoResponse1<User>> getFettlerInfo(@Path("id") String id);
//
//    /**
//     * 获取订单详情
//     *
//     * @param id 订单id
//     * @return OrderDetail
//     */
//    @GET("order/{id}")
//    Observable<DoResponse1<Order>> getOrderInfo(@Path("id") String id);
//
//    /**
//     * 获取当前用户的订单列表（待接抢）
//     *
//     * @param begin 起点
//     * @param count 数量
//     * @return List[Order-SIMPLE]
//     */
//    @GET("waiting-order")
//    Observable<DoResponse1<Data<List<Order>>>> getWaitingOrderList(@Query("begin") int begin,
//                                                                   @Query("count") int count);
//
//    /**
//     * 获取未完成/正在进行订单列表
//     *
//     * @param begin 起点
//     * @param count 数量
//     * @return List[Order-SIMPLE]
//     */
//    @GET("solving-order")
//    Observable<DoResponse1<Data<List<2Order>>>> getSolvingOrderList(@Query("begin") int begin,
//                                                                   @Query("count") int count);
//
//    /**
//     * 获取进行中订单数量
//     *
//     * @return 数量
//     */
//    @GET("solving-order-count")
//    Observable<String> getSolvingOrderCount();
//
//    /**
//     * 获取当前用户的订单列表（历史）
//     *
//     * @param begin 起点
//     * @param count 数量
//     * @return List[Order-SIMPLE]
//     */
//    @GET("history-order")
//    Observable<String> getHistoryOrderList(@Query("begin") int begin,
//                                           @Query("count") int count);
//
//    /**
//     * 获得本企业的维修工的列表(status!==0),按照rank分降序排列
//     *
//     * @param begin   起点
//     * @param count   数量
//     * @param name    名字
//     * @param orderId 订单ID
//     * @return PageDO
//     */
//    @GET("fettler")
//    Observable<DoResponse1<Data<List<User>>>> getColleagueList(@Query("begin") int begin,
//                                                               @Query("count") int count,
//                                                               @Query("name") String name,
//                                                               @Query("orderId") String orderId);
//
//    /**
//     * 获取零件列表
//     * 拉取系统定义的已启用的零件列表（status=1，type=0），按照使用次数降序排列（服务器择期实现）
//     *
//     * @param begin   開始
//     * @param count   数量
//     * @param name    搜索名称
//     * @param orderId 订单Id
//     * @param spuId   所属品类
//     * @return List[PartDO]
//     */
//    @GET("part")
//    Observable<DoResponse1<Data<List<OrderBill>>>> getPartList(@Query("begin") int begin,
//                                                               @Query("count") int count,
//                                                               @Query("name") String name,
//                                                               @Query("spuId") String spuId,
//                                                               @Query("orderId") String orderId);
//
//    /**
//     * 获取维修点列表
//     * 拉取系统定义的已启用的维修点列表（status=1，type=0）
//     *
//     * @param begin   開始
//     * @param count   数量
//     * @param orderId 订单Id
//     * @param spuId   所属品类
//     * @return List[RprojectDO]
//     */
//    @GET("rproject")
//    Observable<DoResponse1<Data<List<OrderBill>>>> getRprojectList(@Query("begin") int begin,
//                                                                   @Query("count") int count,
//                                                                   @Query("name") String name,
//                                                                   @Query("spuId") String spuId,
//                                                                   @Query("orderId") String orderId);
//
//    /**
//     * 获取Spu列表
//     *
//     * @param begin 開始
//     * @param count 数量
//     * @param name  名字
//     * @return List[SpuDO]
//     */
//    @GET("spu")
//    Observable<DoResponse1<Data<List<Spu>>>> getSpuList(@Query("begin") int begin,
//                                                        @Query("count") int count,
//                                                        @Query("name") String name,
//                                                        @Query("categoryId") String categoryId);
//
//
//    /**
//     * 获取维修回执标签列表
//     *
//     * @return List[String]
//     */
//    @GET("receipt-label")
//    Observable<DoResponse1<List<String>>> getFeedbackLabelList(@Query("type") int type);
//
//
//    /**
//     * 获取当前用户的消息列表
//     *
//     * @return List[MessageLog]
//     */
//    @GET("receipt-label")
//    Observable<String> getMessageList();
//
//    /**
//     * 获取消息列表
//     *
//     * @return Page[Message]
//     */
//    @GET("message")
//    Observable<DoResponse1<Data<List<Message>>>> getMessage(@Query("begin") int begin,
//                                                            @Query("count") int count);
//
//    /**
//     * 获取理由列表
//     *
//     * @param type 1=转给同事，2=企业拒绝，3=个人拒绝，4=放弃订单，5=维修师傅取消，6=门店取消，7=转给第三方
//     * @return List[String]
//     */
//    @GET("reason")
//    Observable<DoResponse1<List<String>>> getReasonList(@Query("type") int type);
//
//    /**
//     * 获取本企业的维修提供方的列表
//     *
//     * @param name 搜索名称
//     * @param id   ID
//     * @return PageDO
//     */
//    @GET("company/{id}/provider")
//    Observable<String> getProviderList(@Query("name") String name, @Path("id") String id);
//
//    /**
//     * 获取企业的接口人联系方式列表
//     * 获得本企业的维修提供方的列表。维修方包含供应商和维修公司。
//     *
//     * @param orderId Id
//     * @return PageDO
//     */
//    @GET("company/{companyId}/incharge")
//    Observable<DoResponse1<List<User>>> getCompanyInchangeList(@Query("orderId") String orderId,
//                                                               @Path("companyId") String companyId);
//
//    /**
//     * 获取企业的维修提供方管理员列表
//     * 获得本企业的维修提供方的列表。维修方包含供应商和维修公司。
//     *
//     * @param companyId 公司的id
//     * @return PageDO
//     */
//    @GET("company/{id}/admin")
//    Observable<DoResponse1<List<User>>> getCompanyAdminList(@Path("companyId") String companyId);
//
//    /**
//     * 飞修接口人获取第三方公司列表
//     *
//     * @param begin   开始
//     * @param count   数量
//     * @param name    搜索名称
//     * @param orderId id
//     * @return PageDO
//     */
//    @GET("third-party-company")
//    Observable<DoResponse1<Data<List<TransCompany>>>> getThirdCompanyList(@Query("begin") int begin,
//                                                                          @Query("count") int count,
//                                                                          @Query("name") String name,
//                                                                          @Query("orderId") String orderId);
//
//    /**
//     * 飞修接口人获取指定第三方公司内的维修工列表
//     *
//     * @param begin     开始
//     * @param count     数量
//     * @param orderId   订单id
//     * @param companyId 公司id
//     * @return PageDO
//     */
//    @GET("company/{id}/fettler")
//    Observable<DoResponse1<Data<List<User>>>> getThirdCompanyFettlerList(@Query("begin") int begin,
//                                                                         @Query("count") int count,
//                                                                         @Query("orderId") String orderId,
//                                                                         @Path("companyId") String companyId);
//
//    /**
//     * 获取当前工作状态
//     *
//     * @param id 维修工Id
//     * @return int - status
//     */
//    @GET("fettler/{id}/workstatus")
//    Observable<String> getWorkstatus(@Path("id") String id);
//
//    /**
//     * 切换工作状态
//     *
//     * @param id   维修工Id
//     * @param type 状态类型（1:停止 2:开始接单）
//     * @return int - type
//     */
//    @FormUrlEncoded
//    @PUT("fettler/{id}/workstatus")
//    Observable<DoResponse1<String>> switchWorkstatus(@Path("id") String id,
//                                                     @Field("type") int type);
//
//    /**
//     * user/{id}/head
//     *
//     * @param id   维修工用户id
//     * @param head 质量评价
//     * @return ImageDO
//     */
//    @FormUrlEncoded
//    @PUT("user/{id}/head")
//    Observable<DoResponse1<Image>> changeHead(@Path("id") String id,
//                                              @Field("head") String head);
//
//    /**
//     * 接单
//     *
//     * @param id 订单Id
//     * @return OrderDetail
//     */
//    @PUT("order/{id}/accept")
//    Observable<DoResponse1<Order>> acceptOrder(@Path("id") String id);
//
//    /**
//     * 抢单
//     *
//     * @param id 订单Id
//     * @return OrderDetail
//     */
//    @PUT("order/{id}/grap")
//    Observable<DoResponse1<Order>> grapOrder(@Path("id") String id);
//
//    /**
//     * 个人拒接指派单
//     * 指派订单派给A，A在未接单前，可以调用此接口。
//     *
//     * @param id     订单Id
//     * @param reason 理由
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/personal-refuse")
//    Observable<DoResponse1<Order>> personalRefuse(@Path("id") String id,
//                                                  @Field("reason") String reason);
//
//    /**
//     * 企业拒接指派单
//     *
//     * @param id     订单id
//     * @param reason 理由
//     * @return Order
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/company-refuse")
//    Observable<DoResponse1<Order>> companyRefuse(@Path("id") String id,
//                                                 @Field("reason") String reason);
//
//    /**
//     * 放弃已接的第三方单
//     *
//     * @param id     订单Id
//     * @param reason 理由
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/abandon")
//    Observable<DoResponse1<Order>> abandon(@Path("id") String id,
//                                           @Field("reason") String reason);
//
//    /**
//     * 转发给同事
//     *
//     * @param id        OrderId
//     * @param fettlerId 同事id
//     * @param reason    理由
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/transmit-colleague")
//    Observable<DoResponse1<Order>> transmitColleague(@Path("id") String id,
//                                                     @Field("fettlerId") String fettlerId,
//                                                     @Field("reason") String reason);
//
//    /**
//     * 转发给合作公司
//     *
//     * @param id           OrderId
//     * @param companyId    公司id
//     * @param warrantyType 是否保修
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/transmit-company")
//    Observable<DoResponse1<Order>> transmitCompany(@Path("id") String id,
//                                                   @Field("warrantyType") byte warrantyType,
//                                                   @Field("companyId") String companyId);
//
//    /**
//     * 转发给飞修(也就是第三方)
//     *
//     * @param id     OrderId
//     * @param reason 理由
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/transmit-feixiu")
//    Observable<DoResponse1<Order>> transmitFeixiu(@Path("id") String id,
//                                                  @Field("reason") String reason);
//
//    /**
//     * 出发上路
//     *
//     * @param id OrderId
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/departure")
//    Observable<DoResponse1<Order>> departure(@Path("id") String id,
//                                             @Field("locationX") double locationX,
//                                             @Field("locationY") double locationY);
//
//    /**
//     * 开始维修
//     *
//     * @param id OrderId
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/start-fix")
//    Observable<DoResponse1<Order>> startFix(@Path("id") String id,
//                                            @Field("locationX") double locationX,
//                                            @Field("locationY") double locationY,
//                                            @Field("force") int force);
//
//    /**
//     * 维修结束
//     *
//     * @param id OrderId
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/finish-fix")
//    Observable<DoResponse1<Order>> finishFix(@Path("id") String id,
//                                             @Field("locationX") double locationX,
//                                             @Field("locationY") double locationY);
//
//    /**
//     * 提交报价/修改报价单
//     *
//     * @param orderId OrderId
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{orderId}/quote")
//    Observable<DoResponse1<Order>> quote(@Path("orderId") String orderId,
//                                         @Field("type") int type,
//                                         @Field("partBillList") String partBillList,
//                                         @Field("rprojectBillList") String rprojectBillList,
//                                         @Field("additionBillList") String additionBillList,
//                                         @Field("systemBillList") String systemBillList,
//                                         @Field("quoteVersion") String quoteVersion,
//                                         @Field("proposalWarrantyType") int proposalWarrantyType,
//                                         @Field("completeRepaired") int completeRepaired);
//
//
//    /**
//     * 确认门店的取消订单申请
//     *
//     * @param id OrderId
//     * @return OrderDetail
//     */
//    @PUT("order/{id}/confirm—cancel")
//    Observable<DoResponse1<Order>> confirmCancel(@Path("id") String id);
//
//    /**
//     * 拒绝门店的取消订单申请
//     *
//     * @param id OrderId
//     * @return OrderDetail
//     */
//    @PUT("order/{id}/refuse-cancel")
//    Observable<DoResponse1<Order>> refuseCancel(@Path("id") String id);
//
//    /**
//     * 提交回执
//     *
//     * @param id          OrderId
//     * @param content     回执内容(0-256字)
//     * @param imgNameList 回执评价图片
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/receipt")
//    Observable<DoResponse1<String>> receipt(@Path("id") String id,
//                                            @Field("content") String content,
//                                            @Field("imgNameList") String imgNameList);
//
//    /**
//     * 添加自定义零件
//     *
//     * @param name  名称
//     * @param price 单价
//     * @param unit  单位
//     * @param spuId 所属品类
//     * @return PartDO
//     */
//    @FormUrlEncoded
//    @POST("part")
//    Observable<DoResponse1<OrderBill>> addDiyPart(@Field("name") String name,
//                                                  @Field("price") float price,
//                                                  @Field("unit") String unit,
//                                                  @Field("spuId") String spuId,
//                                                  @Field("orderId") String orderId);
//
//    /**
//     * 添加自定义维修点
//     *
//     * @param name    名称
//     * @param price   单价
//     * @param unit    单位
//     * @param spuId   所属品类
//     * @param orderId 所属品类
//     * @return PartDO
//     */
//    @FormUrlEncoded
//    @POST("addDiyRproject")
//    Observable<DoResponse1<OrderBill>> addDiyRproject(@Field("name") String name,
//                                                      @Field("price") float price,
//                                                      @Field("unit") String unit,
//                                                      @Field("spuId") String spuId,
//                                                      @Field("orderId") String orderId);
//
//
//    /**
//     * 添加自定义费用
//     *
//     * @param name        项目
//     * @param price       价格
//     * @param description 说明
//     * @param imgNameList 凭证图片
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("custom-cost")
//    Observable<DoResponse1<OrderBill>> addDiyCost(@Field("name") String name,
//                                                  @Field("price") String price,
//                                                  @Field("description") String description,
//                                                  @Field("imgNameList") String imgNameList);

}
