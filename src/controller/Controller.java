package controller;

import model.Model;
import model.StreamImpl;
import model.StreamModel;
import model.SubscriptionModel;
import view.AddBookPanelImpl;
import view.AddEmployeePanelImpl;
import view.AddSubscriptionPanelImpl;
import view.LoginPanelImpl;
import view.BookshopPanelImpl;
import view.MainView;
import view.NorthPanel;
import view.NorthPanelImpl;
import view.WarehousePanelImpl;
import view.observer.NorthPanelObserver;
import view.observer.ViewObserver;

import java.io.IOException;

import model.BookImpl;
import model.BookModel;
import model.EmployeeModel;

public class Controller implements NorthPanelObserver, ViewObserver{
	private Model model;
	private MainView mainView;
	private StreamModel<Integer, EmployeeModel> smEmployee;
	private StreamModel<BookModel, Integer> smBook;
	private StreamModel<Integer, SubscriptionModel> smSubscription;
	private StreamModel<Integer, BookModel> smInvoice;
	private StreamModel<BookModel, Integer> smBookShop;
	
	/**
	 * Crea un nuovo Controller senza dare alcun parametro come input.
	 * 
	 * @param model
	 *            il model utilizzato per salvare i dati
	 */
	public Controller(Model model) {
			this.model = model;
			smEmployee = new StreamImpl<Integer, EmployeeModel>();
			smBook = new StreamImpl<BookModel, Integer>();
			smSubscription = new StreamImpl<Integer, SubscriptionModel>();
			smInvoice = new StreamImpl<Integer, BookModel>();
			smBookShop = new StreamImpl<BookModel, Integer>();
	}


	public void setView(MainView mainView) {
		this.mainView = mainView;
		this.mainView.attachObserver(this);
		NorthPanel northpanel = (NorthPanelImpl) this.mainView.getNorthPanel();
		northpanel.attachObserver(this);
	}
	
	/*public void createLoginPanel() {
		LoginPanelImpl lp = new LoginPanelImpl();
		LoginPanelController lpc = new LoginPanelController(this.mainView, model);
		lpc.setView(lp);
		this.mainView.replaceMainPanel(lp);
	}*/


	@Override
	public void buttonHomeClicked() {
		BookshopPanelImpl bsp = new BookshopPanelImpl();
		BookshopController bsc = new BookshopController(this.mainView, model);
		bsc.setView(bsp);
		this.mainView.replaceMainPanel(bsp);
	}
	
	@Override
	public void buttonLoginClicked() {
		LoginPanelImpl lp = new LoginPanelImpl();
		LoginPanelController lpc = new LoginPanelController(this.mainView, model);
		lpc.setView(lp);
		this.mainView.replaceMainPanel(lp);
	}
	
	//Rimpiazza la view corrente con quella del login
	@Override
	public void buttonLogoutClicked() {
		LoginPanelImpl lp = new LoginPanelImpl();
		LoginPanelController lpc = new LoginPanelController(this.mainView, model);
		lpc.setView(lp);
		this.mainView.replaceMainPanel(lp);
		mainView.changeLogStatus(false);
		mainView.getNorthPanel().changeLogStatus(false);
	}

	@Override
	public void warehouseClicked() {
		WarehousePanelImpl wp = new WarehousePanelImpl();
		WarehouseController wc = new WarehouseController(model);
		wc.setView(wp);
		this.mainView.replaceMainPanel(wp);
	}


	@Override
	public void addBooksClicked() {
		AddBookPanelImpl ab = new AddBookPanelImpl();
		InsertBookController ibc = new InsertBookController(this.mainView, model);
		ibc.setView(ab);
		this.mainView.replaceMainPanel(ab);
	}


	@Override
	public void bookShopClicked() {
		BookshopPanelImpl bsp = new BookshopPanelImpl();
    	BookshopController bsc = new BookshopController(this.mainView, model);
    	bsc.setView(bsp);
    	mainView.replaceMainPanel(bsp);
		
	}


	@Override
	public void addEmployeeClicked() {
		AddEmployeePanelImpl ae = new AddEmployeePanelImpl();
		InsertEmployeeController ie = new InsertEmployeeController(this.mainView, model);
		ie.setView(ae);
		mainView.replaceMainPanel(ae);	
	}


	@Override
	public void addSubscriptionClicked() {
		AddSubscriptionPanelImpl sp = new AddSubscriptionPanelImpl();
		InsertSubscriptionController sc = new InsertSubscriptionController(model);
		sc.setView(sp);
		mainView.replaceMainPanel(sp);
	}
	
	@Override
	public void exitCommand() {
		saveData();
		System.exit(0);
	}

	@Override
	public void saveData() {
		smEmployee.writeFile("Employees.dat", model.employees().getEmployees());
		smBook.writeFile("BooksInWareHouse.dat", model.warehouse().getBooks());
		smBookShop.writeFile("BooksInShop.dat", model.shop().getBooks());
		smSubscription.writeFile("Subscriptions.dat", model.subscriptions().getSubscriptions());
	}

	@Override
	public void saveData(String path) {
		// TODO Auto-generated method stub
		System.out.println("Siamo qui");
	}

	@Override
	public void dataLoad() {
		// TODO Auto-generated method stub
		try {
		    
			model.employees().updateEmployees(smEmployee.readFile("Employees.dat"));
			/*for(int i = 0;i<model.employees().getEmployees().size();i++){
	            System.out.println(model.employees().getEmployees().get(i).getUsername());
			}*/
		    model.warehouse().update(smBook.readFile("BooksInWareHouse.dat"));
		    //System.out.println(model.warehouse().getBooks().);
		    for ( BookModel key : model.warehouse().getBooks().keySet() ) {
		        System.out.println( key.getTitle());
		    }
		    model.subscriptions().updateSubscriptions(smSubscription.readFile("Subscriptions.dat"));
		    for (int j = 0; j<model.subscriptions().getSubscriptions().size(); j++){
		    	System.out.println(model.subscriptions().getSubscriptions().get(j).getName());
		    	System.out.println(model.subscriptions().getSubscriptions().get(j).getSurname());
		    	System.out.println(model.subscriptions().getSubscriptions().get(j).getType());
		    }
		    model.shop().update(smBookShop.readFile("BooksInShop.dat"));
		    /*for ( BookModel key : model.shop().getBooks().keySet() ) {
		        System.out.println( key.getTitle());
		    }*/
		    
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void dataLoad(String path) {
		// TODO Auto-generated method stub
		
	}	
}
