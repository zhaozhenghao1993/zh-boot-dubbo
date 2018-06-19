package net.zhenghao.zh.common.entity;

/**
 * 🙃
 * 🙃 邮件封装类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/6/13 17:49
 * SysMailEntity.java
 */
public class SysMailEntity {

    /**
     * 发件人名称
     */
    private String sendPersonal;

    /**
     * 收件人邮箱
     */
    private String receiveMail;

    /**
     * 收件人名称
     */
    private String receivePersonal;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

    public String getSendPersonal() {
        return sendPersonal;
    }

    public void setSendPersonal(String sendPersonal) {
        this.sendPersonal = sendPersonal;
    }

    public String getReceiveMail() {
        return receiveMail;
    }

    public void setReceiveMail(String receiveMail) {
        this.receiveMail = receiveMail;
    }

    public String getReceivePersonal() {
        return receivePersonal;
    }

    public void setReceivePersonal(String receivePersonal) {
        this.receivePersonal = receivePersonal;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
