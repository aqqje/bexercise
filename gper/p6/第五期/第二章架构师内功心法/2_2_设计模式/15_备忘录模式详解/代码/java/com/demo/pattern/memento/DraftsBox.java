package com.demo.pattern.memento;

import java.util.Stack;

public class DraftsBox {

    private final Stack<ArticleMemento> STACK = new Stack<ArticleMemento>();

    public void addMemento(ArticleMemento articleMemento){
        STACK.push(articleMemento);
    }

    public ArticleMemento getMemento(){
        ArticleMemento articleMemento = STACK.pop();
        return articleMemento;
    }
}
