package vip.designpattern.memento.editor2;

import lombok.Data;

import java.util.Stack;

/** 垃圾箱*/
@Data
public class DraftsBox {
    /** 使用栈存储备忘录 */
    private final Stack<AricleMemento> STACK = new Stack<>();

    /** 保存备忘录对象*/
    public void addMemento(AricleMemento aricleMemento){
        STACK.push(aricleMemento);
    }

    public AricleMemento getMemento(){
        return STACK.pop();
    }
}
