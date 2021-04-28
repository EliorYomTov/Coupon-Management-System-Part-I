package com.elior.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.elior.beans.Category;
import com.elior.beans.Coupon;
import com.elior.utils.DateUtils;

public class FactoryCoupon {
	public static List<Coupon> initList() {
		List<Coupon> list = new ArrayList<>();
		list.add(new Coupon(1, Category.FOOD, "Free shipping", "Free Shipping on Orders Over $50", DateUtils.addDays(new Date(), -7),
				DateUtils.addDays(new Date(), -2), 100, 70.0f,
				"https://static.seekingalpha.com/uploads/2016/10/5847171_14775636683576_rId5_thumb.jpg"));
		list.add(new Coupon(2, Category.BABY, "Up to 40% Off", "Up to 40% Off Accessories at Outlet" + "", new Date(),
				DateUtils.addDays(new Date(), 7), 20, 449.99f,
				"https://adelehorin.com.au/wp-content/uploads/2020/10/Bugaboo-pic.png"));
		list.add(new Coupon(3, Category.PC, "Save an $50", "Save an additional $50 on laptops & desktops", DateUtils.addDays(new Date(), -7),
				DateUtils.addDays(new Date(), -1), 230, 1200.0f,
				"https://i.dell.com/sites/csimages/Banner_Imagery/all/547478_us_cs_co_dir_fy22q1w9_site_SpringEvent-Con1-4-1--4-28_400x250.jpg"));
		list.add(new Coupon(4, Category.FASHION, "15% discount", "Extra 15% off first purchase", DateUtils.addDays(new Date(), -7),
				new Date(), 5000, 79.99f,
				"https://st-adidas-isr.mncdn.com/content/images/thumbs/0070456_3-stripes-tee_cw1203_standard-view.jpeg"));
		list.add(new Coupon(5, Category.HEALTH, "$ 3 Off lotion", "$3 Off JOHNSONS Baby Lotion", DateUtils.addDays(new Date(), -14),
				DateUtils.addDays(new Date(), -7), 175, 29.99f,
				"https://static.seekingalpha.com/uploads/2016/10/5847171_14775636683576_rId5_thumb.jpg"));
		list.add(new Coupon(6, Category.PETS, "discount on diet products", "20% Off Select Veterinary Diet Products", new Date(),
				DateUtils.addDays(new Date(), 7), 0, 99.99f,
				"https://static.seekingalpha.com/uploads/2016/10/5847171_14775636683576_rId5_thumb.jpg"));
		list.add(new Coupon(7, Category.RESTAURANTS, "Free delivery", "Free Delivery on Orders $15+ at McDonald's Through UberEats", new Date(),
				DateUtils.addDays(new Date(), 7), 10000, 12.49f,
				"https://static.seekingalpha.com/uploads/2016/10/5847171_14775636683576_rId5_thumb.jpg"));
		list.add(new Coupon(8, Category.VACTION, "Save up to 15% ", "Book Ahead & save up to 15% at Hotels Worldwide", new Date(),
				DateUtils.addDays(new Date(), 7), 50, 2799.0f,
				"https://static.seekingalpha.com/uploads/2016/10/5847171_14775636683576_rId5_thumb.jpg"));
		list.add(new Coupon(1, Category.FOOD, "$5 discount", "$5 Off Every 6 Custom Bottles", DateUtils.addDays(new Date(), -7),
				DateUtils.addDays(new Date(), 7), 50, 24.99f,
				"https://static.seekingalpha.com/uploads/2016/10/5847171_14775636683576_rId5_thumb.jpg"));
		list.add(new Coupon(9, Category.SPORT, "30% discount", "30% Off Your First Membership Order", new Date(),
				DateUtils.addDays(new Date(), 7), 145, 79.99f,
				"https://static.seekingalpha.com/uploads/2016/10/5847171_14775636683576_rId5_thumb.jpg"));
		list.add(new Coupon(10, Category.TOYS, "Park Picnic Set", "Free Park Picnic Set With Sitewide Order of $75", new Date(),
				DateUtils.addDays(new Date(), 7), 200, 199.99f,
				"https://static.seekingalpha.com/uploads/2016/10/5847171_14775636683576_rId5_thumb.jpg"));
		return list;
	}
}
