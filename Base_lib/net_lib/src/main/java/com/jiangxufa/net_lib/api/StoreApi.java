package com.jiangxufa.net_lib.api;

/*
 * 创建时间：2018/1/6
 * 编写人：lenovo
 * 功能描述：
 */
public interface StoreApi {

//    /**
//     * 获取门店端用户信息
//     *
//     * @param id 用户id
//     * @return UserStore-AppDetial
//     */
//    @GET("store/{id}")
//    Observable<DoResponse1<User>> getStoreInfo(@Path("id") String id);
//
//    /**
//     * 获取获取订单详情
//     *
//     * @param id 订单id
//     * @return Order-AppDetail
//     */
//    @GET("order/{id}")
//    Observable<DoResponse1<Order>> getOrderInfo(@Path("id") String id);
//
//    /**
//     * 获取未完成/正在进行的订单列表
//     *
//     * @return List[Order-SIMPLE]
//     */
//    @GET("solving-order")
//    Observable<DoResponse1<Data<List<Order>>>> getSolvingOrderList(@Query("begin") int begin,
//                                                                   @Query("count") int count);
//
//    /**
//     * 获取未完成/正在进行的订单列表
//     *
//     * @return List[Order-SIMPLE]
//     */
//    @GET("history-order")
//    Observable<String> getHistoryOrderList(@Query("begin") int begin,
//                                           @Query("count") int count);
//
//    /**
//     * 获取Category列表
//     *
//     * @return List[CategoryDO]
//     */
//    @GET("category")
//    Observable<DoResponse1<Data<List<Category>>>> getCategoryList(@Query("begin") int begin,
//                                                                  @Query("count") int count,
//                                                                  @Query("type") int type);
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
//    /**
//     * 获取Issue列表
//     *
//     * @return List[IssueDO]
//     */
//    @GET("issue")
//    Observable<DoResponse1<Data<List<Issue>>>> getIssueList(@Query("begin") int begin,
//                                                            @Query("count") int count,
//                                                            @Query("name") String name,
//                                                            @Query("spuId") String spuId);
//
//    /**
//     * 获取Stock列表
//     *
//     * @return List[StockDO]
//     */
//    @GET("stock")
//    Observable<DoResponse1<Data<List<Stock>>>> getStockList(@Query("begin") int begin,
//                                                            @Query("count") int count,
//                                                            @Query("name") String name,
//                                                            @Query("spuId") String spuId);
//
//    /**
//     * 获取单个Stock （扫码报修使用）
//     *
//     * @param id StockId
//     * @return StockDO
//     */
//    @GET("stock/{id}")
//    Observable<DoResponse1<Stock>> getStock(@Path("id") String id,
//                                            @Query("type") String type);
//
//
//    @GET("stock/{id}")
//    Observable<DoResponse1<Spw>> getSpw(@Query("spuId") String spuId);
//
//
//    /**
//     * 获得本企业的维修提供方的列表
//     *
//     * @param id 企业id
//     * @return List[CompanyDO]
//     */
//    @GET("company/{id}/provider")
//    Observable<DoResponse1<Data<List<Company>>>> getProviderList(@Path("id") String id,
//                                                                 @Query("begin") int begin,
//                                                                 @Query("count") int count,
//                                                                 @Query("name") String sk);
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
//
//    /**
//     * 获取理由列表
//     *
//     * @param type StockId1=转给同事，2=企业拒绝，3=个人拒绝，4=放弃订单，5=维修师傅取消，6=门店取消，7=转给第三方
//     * @return List[String]
//     */
//    @GET("reason")
//    Observable<String> getMessage(@Query("type") int type);
//
//    /**
//     * 门店发起新订单 描述：文字、语音、图片三者必须填写一项
//     *
//     * @param refType     1表示refId是spuId，2表示refId是stockId
//     * @param refId       表示是资产还是spu 资产2 spu 1
//     * @param issueId     问题id
//     * @param description 文字描述
//     * @param voiceName   语音文件名称
//     * @param voiceLength 语音长度，毫秒
//     * @param imgNameList 图片文件名称列表
//     * @param providerId  保修提供方id，如果给则表示这是一个保修单
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @POST("order")
//    Observable<DoResponse1<Order>> launchOrder(@Field("refType") int refType,
//                                               @Field("refId") String refId,
//                                               @Field("issueId") String issueId,
//                                               @Field("description") String description,
//                                               @Field("voiceName") String voiceName,
//                                               @Field("voiceLength") long voiceLength,
//                                               @Field("imgNameList") String imgNameList,
//                                               @Field("providerId") String providerId);
//
//
//    /**
//     * 门店取消订单
//     *
//     * @param reason 临时授权
//     * @return 不返回
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/cancel")
//    Observable<DoResponse1<Order>> storeCancel(@Path("id") String id,
//                                               @Field("reason") String reason);
//
//    /**
//     * 门店申请取消订单
//     *
//     * @param reason     取消原因
//     * @param cancelCost 补偿金
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/apply-cancel")
//    Observable<DoResponse1<Order>> applyStoreCancel(@Path("id") String id,
//                                                    @Field("reason") String reason,
//                                                    @Field("cancelCost") double cancelCost);
//
//    /**
//     * 门店接受报价
//     *
//     * @param version 确认的报价版本
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/accept-quotation")
//    Observable<DoResponse1<Order>> acceptQuotation(@Path("id") String id,
//                                                   @Field("version") String version);
//
//    /**
//     * 门店提交订单评价
//     *
//     * @param qualityRank 质量评价
//     * @param speedRank   速度评价
//     * @param mannerRank  态度评价
//     * @param remark      评价
//     * @return OrderDetail
//     */
//    @FormUrlEncoded
//    @PUT("order/{id}/judge")
//    Observable<DoResponse1<Order>> judge(@Path("id") String id,
//                                         @Field("speedRank") int speedRank,
//                                         @Field("qualityRank") int qualityRank,
//                                         @Field("mannerRank") int mannerRank,
//                                         @Field("remark") String remark);
//
//    /**
//     * user/{id}/head
//     *
//     * @param id   门店用户id
//     * @param head 质量评价
//     * @return ImageDO
//     */
//    @FormUrlEncoded
//    @PUT("user/{id}/head")
//    Observable<DoResponse1<Image>> changeHead(@Path("id") String id,
//                                              @Field("head") String head);
//
//    /**
//     * 门店修改店长名称
//     *
//     * @param id          用户id
//     * @param managerName 店长名
//     * @return String - managerName
//     */
//    @FormUrlEncoded
//    @PUT("user/{id}/manager-name")
//    Observable<DoResponse1<String>> changeManagerName(@Path("id") String id,
//                                                      @Field("managerName") String managerName);
//
//    /**
//     * 门店修改地理位置
//     *
//     * @param id          用户id
//     * @param addressNum  行政编号
//     * @param addressDesc 地址描述
//     * @param locationX   经度
//     * @param locationY   维度
//     * @return StoreDetail
//     */
//    @FormUrlEncoded
//    @PUT("user/{id}/address")
//    Observable<DoResponse1<String>> changeLocationInfo(@Path("id") String id,
//                                                       @Field("addressNum") String addressNum,
//                                                       @Field("addressDesc") String addressDesc,
//                                                       @Field("locationX") double locationX,
//                                                       @Field("locationY") double locationY);
//
//    /**
//     * 门店发送手机验证码 (验证手机合法)
//     *
//     * @param phone 门店手机号码
//     * @return String - phone
//     */
//    @FormUrlEncoded
//    @POST("captcha-phone")
//    Observable<DoResponse1<String>> sendCaptche(@Field("phone") String phone);
//
//    /**
//     * 修改绑定手机号
//     *
//     * @param captchaCode 验证码
//     * @param captchaId   验证码ID
//     * @return String - phone
//     */
//    @FormUrlEncoded
//    @PUT("user/{id}/phone")
//    Observable<DoResponse1<String>> changePhone(@Field("captchaCode") String captchaCode,
//                                                @Field("captchaId") String captchaId);
//

}
