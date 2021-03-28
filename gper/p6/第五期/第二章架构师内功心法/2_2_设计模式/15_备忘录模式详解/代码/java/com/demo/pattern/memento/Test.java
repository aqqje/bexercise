package com.demo.pattern.memento;

public class Test {

    public static void main(String[] args) {
        DraftsBox draftsBox = new DraftsBox();

        Editor editor = new Editor("枯枯厅厅起作用人脸地一1", "一厅人发械人一要要要发了人卫1", "test.png");

        ArticleMemento articleMemento = editor.saveToMemento();
        draftsBox.addMemento(articleMemento);

        System.out.println(editor.toString());

        editor.setContent("枯枯厅厅起作用人脸地一2222222");

        ArticleMemento articleMemento1 = editor.saveToMemento();
        draftsBox.addMemento(articleMemento1);

        System.out.println(editor.toString());

        ArticleMemento memento = draftsBox.getMemento();
        editor.undoFromMemento(memento);

        System.out.println(editor.toString());

        ArticleMemento memento2 = draftsBox.getMemento();
        editor.undoFromMemento(memento2);

        System.out.println(editor.toString());

    }
}
