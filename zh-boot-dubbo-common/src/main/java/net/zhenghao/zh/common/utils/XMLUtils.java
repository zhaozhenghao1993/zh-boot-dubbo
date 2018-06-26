package net.zhenghao.zh.common.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.lang.StringUtils;

import java.io.Writer;

/**
 * 🙃
 * 🙃 xml解析工具类
 * 🙃 使用dom4j转换
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/4/20 15:12
 * XMLUtils.java
 */
public class XMLUtils {

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream newXStreamInstance() {
        return new XStream(new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 对所有xml节点的转换都增加CDATA标记
                    boolean cdata = true;

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (this.cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
    }

    /**
     * xml转bean实体类
     * @param xml xml格式字符串
     * @param clazz
     * @return 该实体类对象
     */
    public static <T> T xmlToBean(String xml, Class<T> clazz) {
        if (StringUtils.isNotBlank(xml)) {
            XStream xstream = newXStreamInstance();
            xstream.processAnnotations(clazz);
            return (T) xstream.fromXML(xml);
        }
        return null;
    }

    /**
     * bean实体类转xml
     *
     * @param object
     * @return
     */
    public static String beanToXml(Object object) {
        if(object != null) {
            XStream xStream = newXStreamInstance();
            xStream.processAnnotations(object.getClass());
            return xStream.toXML(object);
        }
        return "";
    }

}
