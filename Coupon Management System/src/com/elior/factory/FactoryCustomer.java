package com.elior.factory;
import java.util.ArrayList;
import java.util.List;

import com.elior.beans.Customer;

public class FactoryCustomer {
	public static List<Customer> initList() {
		List<Customer> list = new ArrayList<>();
		list.add(new Customer("Kobi", "Perez", "kobi@gmail.com", "kobi1234"));
		list.add(new Customer("Lora", "Valenski", "Lora.Val@gmail.com", "Lora1111"));
		list.add(new Customer("Ronit", "Noiman", "RonitNoiman@gmail.com", "Ronit5432"));
		list.add(new Customer("Avi", "Bashir", "AviB@gmail.com", "Avi7979"));
		list.add(new Customer("Paz", "Goldman", "Paz.Goldman@gmail.com", "Paz4321"));
		list.add(new Customer("Joni", "Kalifa", "Joni-k@gmail.com", "JoniKalifa22"));
		list.add(new Customer("Shira", "Ron", "ShiraR97@gmail.com", "Shira1122"));
		list.add(new Customer("Tzach", "Sharabi", "TzachShar76@gmail.com", "Tzach8654"));
		list.add(new Customer("Dani", "Shmit", "DaniS12@gmail.com", "DaniS9090"));
		list.add(new Customer("Sapir", "Chen", "SapirChen2020@gmail.com", "Sapir2020"));
		list.add(new Customer("Ben", "Lolo", "BenLolo432@gmail.com", "BenBen432"));
		return list;
	}
}
