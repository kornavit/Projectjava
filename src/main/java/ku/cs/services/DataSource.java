package ku.cs.services;

public interface DataSource<T> {
    T readData();
    void writeData(T t,String user,String password);
}
