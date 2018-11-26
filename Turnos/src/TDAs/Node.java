
package TDAs;

/**
 *
 * @author carlasanchez
 */
public class Node<E> {
    private E data;
    private Node <E> next;
    private Node<E> previous;

    public Node(E data) {
        this.data = data;
        this.next=null;
        this.previous=null;
            }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }
    
}
