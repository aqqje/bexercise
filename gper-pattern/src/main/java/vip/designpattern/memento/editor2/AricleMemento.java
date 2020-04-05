package vip.designpattern.memento.editor2;

import lombok.Data;

/** 备忘录对象*/
@Data
public class AricleMemento {

    public String title;

    public AricleMemento(String title){
        this.title = title;
    }
}
