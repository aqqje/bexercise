package vip.designpattern.memento.editor2;

public class Test {

    public static void main(String[] args) {
        // --------------------->> 初始次创建
        Editor editor = new Editor("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        DraftsBox draftsBox = new DraftsBox();
        draftsBox.addMemento(editor.saveMemento());

        // --------------------->> 第一次修改

        editor.setTitle("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        draftsBox.addMemento(editor.saveMemento());

        // --------------------->> 第二次修改
        editor.setTitle("cccccccccccccccccccccccccccccccccccccc");
        draftsBox.addMemento(editor.saveMemento());

        // --------------------->> 第三次修改
        editor.setTitle("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
        draftsBox.addMemento(editor.saveMemento());

        //^^^^^^^^^^^^^^^^^^^^^^^**撤销
        int size = draftsBox.getSTACK().size();
        for (int i = 0; i < size; i++) {
            System.out.println("第"+(i + 1)+"次撤销");
            System.out.println(draftsBox.getMemento().toString());
            System.out.println("--------------------------------------\n");
        }

    }
}
