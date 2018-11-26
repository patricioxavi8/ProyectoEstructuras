
package TDAs;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author carlasanchez
 */
public class CircularDoublyList<E> implements List<E>, Iterable<E> {

    public Node first;
   public Node last;
    public int efectivo;

    public CircularDoublyList() {

        this.first = this.last=null;       
        this.efectivo = 0;
    }

    @Override
    public boolean addFirst(E element) {
        Node newNode = new Node(element);
        if (element == null) {
            return false;
        }
        if (this.first == null) {
            newNode.setNext(newNode);
            newNode.setPrevious(newNode);
            this.first = newNode;
            this.last = this.first;
        } else {
            newNode.setPrevious(this.last);
            this.last.setNext(newNode);
            this.first.setPrevious(newNode);
            newNode.setNext(first);
            this.first = newNode;
        }
        this.efectivo++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Node newNode = new Node(element);
        if (this.first == null) {
            newNode.setNext(newNode);
            newNode.setPrevious(newNode);
            this.first = newNode;
            this.last = first;
        } else {
            newNode.setPrevious(last);
            this.last.setNext(newNode);
            this.first.setPrevious(newNode);
            newNode.setNext(first);
            this.last = newNode;
        }
        this.efectivo++;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if (first.getNext() == null || efectivo == 1) {
            this.first = null;
            this.last = null;

        } else {
            Node temp = first.getNext();
            first.setNext(null);
            first.setPrevious(null);
            temp.setPrevious(last);
            this.last.setNext(temp);
            this.first = temp;
        }
        this.efectivo--;
        return true;
    }

    @Override
    public boolean removeLast() {
        if (last.getPrevious() == null || efectivo == 1) {
            last = null;
            first = null;

        } else {
            Node temp = last.getPrevious();
            last.setPrevious(null);
            last.setNext(null);
            temp.setNext(first);
            first.setPrevious(temp);
            last = temp;

        }
        this.efectivo--;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return efectivo == 0;
    }

   

    @Override
    public boolean contains(E element) {
        Node aux=first;
         while((aux.getNext()!=first) && (!(aux.getData().equals(element))))
                   aux=aux.getNext();
         return aux.getData().equals(element);
    }

    @Override
    public E get(int index) {
     if (index < 0 || index >= this.efectivo) {
            throw new IndexOutOfBoundsException();
        } else {
            Node current = first;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return (E) current.getData();
     }   
    }

    @Override
    public E getFirst() {
        return (E) this.first.getData();
    }

    @Override
    public E getLast() {
        return (E) this.last.getData();
    }

    @Override
    public int size() {
        return efectivo;
    }

    @Override
    public boolean remove(int index) {
        if (efectivo < index) {
            return false;
        } else {
            if (index == 1) {
                if (efectivo == 1) {
                    first = null;
                    last = null;

                }
                first = first.getNext();
                first.setPrevious(last);
                last.setNext(first);

            }
            if (index == efectivo) {
                last = last.getPrevious();
                last.setNext(first);
                first.setPrevious(last);

            }
            Node ptr = first.getNext();
            for (int i = 1; i <= efectivo; i++) {
                if (i == index) {
                    Node p = ptr.getPrevious();
                    Node n = ptr.getNext();

                    p.setNext(n);
                    n.setPrevious(p);

                }
                ptr = ptr.getNext();
            }
        }
        this.efectivo--;
        return true;
    }

    @Override
    public boolean insert(E element, int index) {
        if (index > efectivo || element == null) {
            return false;
        } 
        int cont = 1;
        Node auxiliar = first;
        while (auxiliar.getNext() != first) {
            if (cont == index) {
                Node nuevo = new Node(element);
                
                Node auxiliar2 = auxiliar.getNext();
                auxiliar.setNext( nuevo);
                nuevo.setPrevious(auxiliar);
                nuevo.setNext(auxiliar2);
                auxiliar2.setPrevious(nuevo);
                
                break;
            }

            cont++;
            auxiliar = auxiliar.getNext();
        
        }
        this.efectivo++;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {
            Node current = first;
            Node lastReturned;
            int index = 0;

            public boolean hasNext() {
                return index < efectivo;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = current;
                E item = (E) current.getData();
                current = current.getNext();
                index++;
                return item;
            }
        };
        return iterator;

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node current = this.first;
        do {
            s.append(current.getData()).append(" ");
            current = current.getNext();

        }while(current!=first);
                   s.append("");
        return "[" + s + "]";

    }

}
