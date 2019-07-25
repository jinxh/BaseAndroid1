package com.suyan.cloud.dbmgr.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by bianyin on 2019/3/22.
 */

@Entity
public class UserBean {

    @Id(autoincrement = true)
    private Long id;

    private String userName;

    private String userId;

    private String ouid;

    private String userPhone;

    private String name;
    private String ouName;
    private String realUserId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOuName() {
        return ouName;
    }

    public void setOuName(String ouName) {
        this.ouName = ouName;
    }

    @Generated(hash = 1971231479)
    public UserBean(Long id, String userName, String userId, String ouid,
            String userPhone, String name, String ouName, String realUserId) {
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.ouid = ouid;
        this.userPhone = userPhone;
        this.name = name;
        this.ouName = ouName;
        this.realUserId = realUserId;
    }

    @Generated(hash = 1203313951)
    public UserBean() {
    }

    public String getRealUserId() {
        return realUserId;
    }

    public void setRealUserId(String realUserId) {
        this.realUserId = realUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOuid() {
        return ouid;
    }

    public void setOuid(String ouid) {
        this.ouid = ouid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
