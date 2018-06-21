package net.zhenghao.zh.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 🙃
 * 🙃 dubbo demo 实体类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/6/21 14:36
 * DubboDemo.java
 */
public class DubboDemo implements Serializable {

    private static final long serialVersionUID = -1644650237295816239L;

    private Integer id;

    private String name;

    private Integer age;

    private Date birthday;

    public DubboDemo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
