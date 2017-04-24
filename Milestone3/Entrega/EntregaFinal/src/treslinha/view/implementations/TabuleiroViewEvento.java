

package treslinha.view.implementations;

import javafx.event.Event;
import javafx.event.EventType;

/**
 *
 * @author Morai
 */
public class TabuleiroViewEvento extends Event{
    
    private final int indiceLinha;

    public int getIndiceLinha() {
        return indiceLinha;
    }
    
    public static final EventType<TabuleiroViewEvento> DROP =
                new EventType<>(Event.ANY, "DROP");
   
    public TabuleiroViewEvento(EventType<TabuleiroViewEvento> eventType, int indiceLinha) {
        super(DROP);
        this.indiceLinha = indiceLinha;
    }
    
}
