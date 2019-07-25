package com.suyan.cloud.network.service;

import com.suyan.cloud.bean.response.LoginRes;
import com.suyan.cloud.network.BaseDataResponse;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IUserService {



    //清除绑定作业人员
    @FormUrlEncoded
    @POST("doLogin")
    Observable<BaseDataResponse<LoginRes>> login(@Field("username") String username, @Field("password") String password);

}
