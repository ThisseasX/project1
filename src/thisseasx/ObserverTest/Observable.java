package thisseasx.ObserverTest;

@SuppressWarnings("unused")
public interface Observable {
    void notifyObservers();

    void addObserver(Observer o);

    void removeObserver(Observer o);

    void clearObservers();
}
