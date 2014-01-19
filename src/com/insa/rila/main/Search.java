/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.main;

import com.insa.rila.query.Paragraph;
import com.insa.rila.query.Query;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author strokyl
 */
public class Search {
	public static void main(String args[]) throws IOException, SQLException {
		Scanner scanner = new Scanner(System.in);
		String search;
		Query query;
		String next;
		do {
			System.out.println("Rentrez votre recherche (si vous rentrez rien le programme s'arrete)  : ");
			search = scanner.nextLine();
			if(search.length() > 0) {
				query = new Query(-1, search);
				for(Paragraph p : query.searchParagraph()) {
					System.out.println(p.getText());	
					System.out.println("Pour afficher un autre parapgraph appuyer sur entrer sinon entrer non : ");
					if(scanner.nextLine().length() != 0) {
						break;
					}
				}
			}
		} while(search.length() > 0);
	}
}
