package vip.designpattern.composite.homework;

import java.util.ArrayList;
import java.util.List;

public class BookCatalog extends BookStack{


    private List<BookStack> catalogs;
    private Integer level;

    public BookCatalog(String name, Integer level) {
        super(name);
        this.level = level;
        this.catalogs = new ArrayList<>();
    }

    public boolean add(BookStack catalog){
        return this.catalogs.add(catalog);
    }

    public boolean remove(BookStack catalog){
        return this.catalogs.remove(catalog);
    }

    public BookStack get(Integer index){
        return this.catalogs.get(index);
    }

    @Override
    public void show() {
        System.out.println(this.name);
        for (BookStack dir : this.catalogs) {
            //控制显示格式
            if(this.level != null){
                for(int  i = 0; i < this.level; i ++){
                    //打印空格控制格式
                    System.out.print("  ");
                }
                for(int  i = 0; i < this.level; i ++){
                    //每一行开始打印一个+号
                    if(i == 0){ System.out.print("+"); }
                    System.out.print("-");
                }
            }
            //打印名称
            dir.show();
        }
    }

    public void list(){
        for (BookStack catalog : this.catalogs) {
            System.out.println(catalog.name);
        }
    }
}
