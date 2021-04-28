package com.elior.login;
import java.sql.SQLException;
import com.elior.facade.AdminFacade;
import com.elior.facade.ClientFacade;
import com.elior.facade.CompanyFacade;
import com.elior.facade.CustomerFacade;

public class LoginManager {
	private static LoginManager instance = null;

	private LoginManager() {}

	public static LoginManager getInstance() {
		if (instance == null) {
			synchronized (LoginManager.class) {
				if (instance == null)
					instance = new LoginManager();
			}
		}
		return instance;
	}

	public static ClientFacade login(String email, String password, ClientType clientType)
			throws SQLException, InterruptedException {
		ClientFacade clientFacade = null;
		boolean loginSuccessfully;
		switch (clientType) {
		case ADMINISTRATOR:
			clientFacade = AdminFacade.getInstance();
			break;
		case COMPANY:
			clientFacade = new CompanyFacade();
			break;
		case CUSTOMER:
			clientFacade = new CustomerFacade();
			break;
		}
		loginSuccessfully = clientFacade.login(email, password);
		if (loginSuccessfully != true)
			System.err.println("Error: " + clientType.name().toString() + " Login details are incorrect!");
		return loginSuccessfully ? clientFacade : null;
	}
}
