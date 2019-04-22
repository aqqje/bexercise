package cn.aqqje.dome.test;

public class Lenove implements SaleComputer{

    @Override
    public String sale(int menoey) {
        System.out.println(menoey);
        return "联想电脑";
    }

    @Override
    public void show() {
        System.out.println("show....");
    }
}
