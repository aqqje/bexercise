package com.demo.pattern.prototype.deep;

import lombok.Data;

import java.io.*;
import java.util.Date;

@Data
public class QiTianDaSheng extends Monkey implements Cloneable, Serializable {

    private JinGuBang jinGuBang;

    public QiTianDaSheng(){
        this.setBirthday(new Date());
        this.jinGuBang = new JinGuBang();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    /**
     * 深克隆
     * @return
     */
    public Object deepClone() {
        try {

            // 内存中完成操作对象读写，是通过字节码直接操作
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            oos.close();
            bos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois =new ObjectInputStream(bis);

            QiTianDaSheng copy = (QiTianDaSheng) ois.readObject();
            copy.setBirthday(new Date());

            ois.close();
            bis.close();

            return copy;

        } catch (Exception e){
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    /**
     * 浅克隆
     */
    public QiTianDaSheng shallowClone(QiTianDaSheng target){
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        qiTianDaSheng.setHeight(target.getHeight());
        qiTianDaSheng.setWeight(target.getWeight());
        qiTianDaSheng.setJinGuBang(target.getJinGuBang());
        qiTianDaSheng.setBirthday(new Date());
        return qiTianDaSheng;
    }
}
