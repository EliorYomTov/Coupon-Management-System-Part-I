package com.elior.factory;
import java.util.ArrayList;
import java.util.List;
import com.elior.beans.Company;

public class FactoryCompany {
	public static List<Company> initList() {
		List<Company> list = new ArrayList<>();
		list.add(new Company("Coca Cola", "CocaCola@Coca Cola.com", "CocaCola1234"));
		list.add(new Company("Bugaboo", "Bugaboo@Bugaboo.com", "Bugaboo2020"));
		list.add(new Company("Dell", "Dell@Dell.com", "Dell5432"));
		list.add(new Company("Adidas", "Adidas@Adidas.com", "Adidas7979"));
		list.add(new Company("Johnson & Johnson", "Johnson&Johnson@JohnsonAndJohnson.com", "Johnson4321"));
		list.add(new Company("Happy Pet", "HappyPet@Pet.com", "Pet22"));
		list.add(new Company("McDonald's", "McDonalds@McDonalds.com", "MC1122"));
		list.add(new Company("Booking", "Booking@Booking.com", "Booking8654"));
		list.add(new Company("Holmes Place", "HolmesPlace@comp.com", "HolmesPlaceS99"));
		list.add(new Company("LEGO", "lego@lego.com", "LEGO2000"));
		return list;
	}
}
