package ShopManagerV2;

import java.util.List;

public interface TheWay<E> {
    public void create(E e);
    public void update(E e , int key);
    public void delete(E e);
    public List<E> getAll();
    
    
}
