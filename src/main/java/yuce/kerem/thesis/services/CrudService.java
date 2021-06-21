package yuce.kerem.thesis.services;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/31/21 4:39 PM
 */
public interface CrudService<T, ID> {

    public T save(T obj);
    public T getById(ID id);
    public void delete(T obj);
    public void deleteById(ID id);

}
