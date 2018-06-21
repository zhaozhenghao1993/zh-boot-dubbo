package net.zhenghao.zh.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 🙃
 * 🙃 通用java序列化的工具
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/6/12 15:57
 * SerializableUtils.java
 */
public class SerializableUtils {

    /**
     * 对象转byte[]
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj) {
        if (null == obj) {
            return null;
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("serialize object error", e);
        }
    }

    /**
     * byte[]转对象
     * @param bytes
     * @return
     */
    public static Object deserialize(byte[] bytes) {
        if (null == bytes) {
            return null;
        }
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize object error", e);
        }
    }
}
