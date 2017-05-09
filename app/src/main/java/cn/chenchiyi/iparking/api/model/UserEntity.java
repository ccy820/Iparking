package cn.chenchiyi.iparking.api.model;

/**
 * Created by ccy820 on 2017/5/10.
 */

public class UserEntity {

    /**
     * id:446
     * name:chen
     * password：****
     * isDel:0
     */

    private String id;
    private String name;
    private String password;
    private int isDel;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getIsDel() {
        return isDel;
    }
}
