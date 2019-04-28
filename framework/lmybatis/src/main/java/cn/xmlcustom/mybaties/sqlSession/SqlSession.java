package cn.xmlcustom.mybaties.sqlSession;

public interface SqlSession {

    /**
     * 核心交互对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 关闭资源
     */
    void close();
}
