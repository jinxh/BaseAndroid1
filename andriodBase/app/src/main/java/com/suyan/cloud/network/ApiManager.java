package com.suyan.cloud.network;

import com.suyan.cloud.network.service.IUserService;

public class ApiManager {
    public static String BaseUrl = "http://58.214.9.90:88/poseidon-server/";
    private static ApiManager s_Instance = null;
    private IUserService mUserEmpService;

    private ApiManager() {

    }

    public static synchronized ApiManager getInstance() {
        if (s_Instance == null) {
            s_Instance = new ApiManager();
        }
        return s_Instance;
    }

    public IUserService getUserEmpService() {

        if (mUserEmpService == null) {
            mUserEmpService = NetWorkManager.getInstance().create(BaseUrl, IUserService.class);
        }
        return mUserEmpService;
    }


}
