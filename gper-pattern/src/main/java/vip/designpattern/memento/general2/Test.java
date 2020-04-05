package vip.designpattern.memento.general2;

public class Test {
    public static void main(String[] args) {
        // 创建原对象
        Originator originator = new Originator(2);
        // 创建备忘管理对象
        Caretaker caretaker = new Caretaker();

        // 存储备忘录
        caretaker.stroememento(originator.createMemento());

        // ----------------第一次修改------------------->
        originator.setStatus(222222);
        System.out.println(originator.getStatus());

        // ----------------第一次回滚------------------->
        originator.restoreMemento(caretaker.getMemento());
        System.out.println(originator.getStatus());
    }
}
