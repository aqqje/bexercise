package vip.designpattern.memento.editor2;

import lombok.Data;

/** 富文本对象*/
@Data
public class Editor {
    public String title;

    public Editor(String title){
        this.title = title;
    }

    /** 保存备记录*/
    public AricleMemento saveMemento(){
        return new AricleMemento(this.title);
    }

    /** 撤销操作*/
    public void undoFromMemento(AricleMemento aricleMemento){
        this.title = aricleMemento.title;
    }
}
