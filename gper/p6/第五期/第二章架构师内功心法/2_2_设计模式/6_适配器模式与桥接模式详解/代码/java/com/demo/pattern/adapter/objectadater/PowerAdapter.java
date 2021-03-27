package com.demo.pattern.adapter.objectadater;

public class PowerAdapter implements DC5 {

    private AC220 ac220;

    public PowerAdapter(AC220 ac220){
        this.ac220 = ac220;
    }

    @Override
    public int output5v() {
        int adapterInput = this.ac220.outputAC220V();
        int adapterOutput = adapterInput / 44;
        System.out.println("使用Adapter输入AC" + adapterInput + "V, 输出DC" + adapterOutput);
        return adapterOutput;
    }
}
