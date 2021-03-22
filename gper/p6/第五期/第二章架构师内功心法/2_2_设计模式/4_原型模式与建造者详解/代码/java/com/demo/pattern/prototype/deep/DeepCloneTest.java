package com.demo.pattern.prototype.deep;

/**
 * 深克隆测试
 */
public class DeepCloneTest {

    public static void main(String[] args) {
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        try{
            QiTianDaSheng deepClone = (QiTianDaSheng) qiTianDaSheng.clone();
            System.out.println("深克隆：" +  (qiTianDaSheng.getJinGuBang() == deepClone.getJinGuBang()));
            System.out.println();
        }catch (Exception e){
            e.printStackTrace();
        }
        QiTianDaSheng shallowClone = qiTianDaSheng.shallowClone(qiTianDaSheng);
        System.out.println("浅克隆：" +  (qiTianDaSheng.getJinGuBang() == shallowClone.getJinGuBang()));
    }
}
