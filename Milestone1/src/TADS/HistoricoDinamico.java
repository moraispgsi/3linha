package TADS;

import TADInterfaces.Historico;
import java.util.Iterator;

/**
 * Implementação dinâmica do Historico
 *
 * @see Historico<E>
 *
 * @author Ricardo José Horta Morais
 *
 * @param <E> tipo de elementos
 */
public class HistoricoDinamico<E> implements Historico<E> {

    private DNode<E> head;
    private DNode<E> tail;
    private int size;
    private int capacity;

    public HistoricoDinamico(int capacity) {

        if (capacity < 1) {
            throw new RuntimeException("Capacidade tem de ser maior que 0.");
        }

        this.head = null;
        this.tail = null;
        this.capacity = capacity;

        size = 0;

    }

    @Override
    public void add(E elemento) {

        DNode<E> novoNode;

        if (size == 0) {

            novoNode = new DNode<>(elemento, null, null);
            head = novoNode;
            tail = novoNode;

        } else {
            
            DNode<E> posterior = head;
            
            novoNode = new DNode<>(elemento, head, posterior);
            head = novoNode;
            
            posterior.setAnterior(head);

        }
        size++;
        
        if(size > capacity){
            
            size = capacity;
                
            tail = tail.getAnterior();
            tail.setPosterior(null);

        }

    }
    
     @Override
    public Iterator<E> iterator() {
        
        Iterator<E> iterador = new Iterator<E>() {

            private DNode<E> atual = head;

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public E next() {
                
                E elemento = atual.getElemento();
                
                atual = atual.getPosterior();
                
                return elemento;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        };
        
        return iterador;
    }
    

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }
    
    
    

}
