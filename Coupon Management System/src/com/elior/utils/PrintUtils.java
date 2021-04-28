package com.elior.utils;

import java.util.List;

import com.elior.beans.Category;
import com.elior.utils.CommandLineTable.CommandLineTableIntf;

public class PrintUtils {
	private static CommandLineTable commandLineTable;

	public static void printResultList(List<? extends CommandLineTableIntf> list) {
		commandLineTable = new CommandLineTable();
		commandLineTable.setShowVerticalLines(true);
		if (list.isEmpty()) {
			return;
		}
		commandLineTable.setHeaders(list.get(0).getHeder());
		for (CommandLineTableIntf row : list) {
			commandLineTable.addRow(row);
		}
		System.out.println("\r\n");
		commandLineTable.print();
	}

	public static void printCategoriesList() {
		commandLineTable = new CommandLineTable();
		commandLineTable.setShowVerticalLines(true);
		commandLineTable.setHeaders("Id", "Name");
		for (Category c : Category.values()) {
			commandLineTable.addRow(String.valueOf(c.ordinal() + 1), c.name());
		}
		System.out.println("\r\n");
		commandLineTable.print();
	}

	public static void printResult(CommandLineTableIntf c) {
		commandLineTable = new CommandLineTable();
		commandLineTable.setShowVerticalLines(true);
		commandLineTable.setHeaders(c.getHeder());
		commandLineTable.addRow(c);
		System.out.println("\r\n");
		commandLineTable.print();
	}

	public static void printTest(String str) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("              " + str + "                                          ");
		System.out.println("-------------------------------------------------------------------");
	}
}
