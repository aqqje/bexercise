package vip.designpattern.memento.general2;

import lombok.Data;

@Data
public class Memento {
    private int status;

    public Memento(int status){
        this.status = status;
    }

}
