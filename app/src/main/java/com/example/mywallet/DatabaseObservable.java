package com.example.mywallet;

public interface DatabaseObservable {
    //register the observer with this method
    void registerDbObserver(DatabaseObserver databaseObserver);
    //unregister the observer with this method
    void removeDbObserver(DatabaseObserver databaseObserver);
    //call this method upon database change
    void notifyDbChanged();

}
