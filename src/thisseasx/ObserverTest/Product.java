package thisseasx.ObserverTest;

import java.util.ArrayList;
import java.util.List;

class Product implements Observable {

    private String productName;
    private boolean available;
    private List<Observer> observers = new ArrayList<>();

    Product(String productName, boolean available) {
        this.productName = productName;
        this.available = available;
    }

    String getProductName() {
        return this.productName;
    }

    boolean isAvailable() {
        return this.available;
    }
    void setAvailable(boolean available) {
        if (this.available != available) {
            this.available = available;
            notifyObservers();
        }
    }

    List<Observer> getObservers() {
        return observers;
    }

    boolean contains(Observer o) {
        return observers.contains(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void clearObservers() {
        observers.clear();
    }
}
