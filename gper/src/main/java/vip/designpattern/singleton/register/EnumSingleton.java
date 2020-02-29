package vip.designpattern.singleton.register;

/**
 * 注册式单例
 * Create by aqqje on 2020/2/29.
 */
public enum EnumSingleton {
    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
