package com.demo.pattern.prototype.deep;

import lombok.Data;

import java.io.Serializable;

@Data
public class JinGuBang implements Serializable {
    private float h = 100;
    private float d = 10;

    public void big() {
        this.d *= 2;
        this.h *= 2;
    }

    public void small() {
        this.d /= 2;
        this.h /= 2;
    }
}
