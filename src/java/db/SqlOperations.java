package db;

import java.util.Set;

/**
 * Required sql operations blueprint for all entity
 * 
 * @author i-am-prinx
 */
public interface SqlOperations<T> {
    
    // retrieves an instance of the specified return type
    public T get(Integer... Id);
    
    // retrieves all instances of the specified return type
    public Set<T> getAll();
    
    // helps to handle insertion of data
    public T insertInto();
    
    // helps to handle deletion of data
//    public int deleteFrom();
    
    // helps to handle data update
    public T updateData(T obj);
    
}
