package vip.designpattern.memento.general2;

import lombok.Data;

/** 原对象*/
@Data
public class Originator {
    private int status;

    public Originator(int status){
        this.status = status;
    }

    /** 创建一个备忘录对象*/
    public Memento createMemento(){
        return new Memento(this.status);
    }

    /** 回滚*/
    public void restoreMemento(Memento memento){
        this.setStatus(memento.getStatus());
    }
}
