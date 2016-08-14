package view;

import view.observer.WarehouseObserver;

public interface WarehousePanel {
	/**
	 * set for the WarehousePnaleImpl observer
	 * 
	 * @param observer
	 * */
	void attachObserver(WarehouseObserver observer);
	/**
	 * Cleans all textField in the panel 
	 * */
	void clearPanel();
	
}
