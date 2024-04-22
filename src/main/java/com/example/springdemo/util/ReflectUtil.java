package com.example.springdemo.util;

/**
 * 反射工具类
 */
public class ReflectUtil {

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 根据属性名获取对应的get方法
     * @param fieldName
     * @param fieldTypeClazz
     * @return
     */
    public static String getMethodNameByFieldName(String fieldName, Class<?> fieldTypeClazz) {
       if (fieldTypeClazz != null && fieldTypeClazz == Boolean.class) {
           return "is" + upperCase(fieldName);
       }
        return "get" + upperCase(fieldName);
    }

    public static void main(String[] args) {
        System.out.println(getMethodNameByFieldName("aaa", Boolean.class));
        System.out.println(getMethodNameByFieldName("bbb", String.class));
        System.out.println(getMethodNameByFieldName("ccc", Integer.class));
        System.out.println(getMethodNameByFieldName("ddd", null));
        // Java项目避免下面这两种属性名，否则需要根据实际情况进行处理
        System.out.println(getMethodNameByFieldName("Eee", null));
        System.out.println(getMethodNameByFieldName("_ff", null));

        // 输出结果：
        // isAaa
        // getBbb
        // getCcc
        // getDdd
        // getEee
        // get_ff
    }

}
