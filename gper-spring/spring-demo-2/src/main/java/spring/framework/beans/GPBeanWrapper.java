package spring.framework.beans;

/**
 * @author AqqJE
 */
public class GPBeanWrapper {

    private Object wrappedInstance;
    private Class<?> wrappedClass;

    public GPBeanWrapper(Object instance) {
        this.wrappedInstance = instance;
        this.wrappedClass = instance.getClass();
    }

    public Object getWrappedInstance() {
        return wrappedInstance;
    }

    public void setWrappedInstance(Object wrappedInstannce) {
        this.wrappedInstance = wrappedInstannce;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }

    public void setWrappedClass(Class<?> wrappedClass) {
        this.wrappedClass = wrappedClass;
    }
}
