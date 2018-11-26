
package TDAs;

import java.util.Iterator;

/**
 *
 * @author carlasanchez
 */
public interface List <E>{
   boolean addFirst(E element);
   boolean addLast(E element);
   boolean removeFirst();
   boolean removeLast();  
   boolean isEmpty();
   
   boolean contains(E element);
   E get(int index);
   E getFirst();
   E getLast();
   int size();
   boolean remove(int index);
   boolean insert(E element, int index);
   Iterator<E> iterator();
  
    
}
