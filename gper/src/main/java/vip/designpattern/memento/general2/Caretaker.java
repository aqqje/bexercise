package vip.designpattern.memento.general2;

import lombok.Data;

/** 备忘录管理对象*/
@Data
public class Caretaker {

    private Memento memento;

    public void stroememento(Memento memento){
        this.memento = memento;
    }

}
