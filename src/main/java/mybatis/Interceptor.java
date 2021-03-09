package mybatis;

public interface Interceptor {
    /**
     * 具体拦截处理
     */
    Object intercept(Invocation invocation) throws Exception;
    /**
     *  插入目标类
     */
    <T> T  plugin(Object target);
}
