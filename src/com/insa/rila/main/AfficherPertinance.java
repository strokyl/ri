/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.main;

import com.insa.rila.query.Query;
import com.insa.rila.xml.querie.Parser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 *
 * @author strokyl
 */
public class AfficherPertinance {

	/**
	 *
	 * @param args
	 * @throws JAXBException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws JAXBException, IOException, SQLException {
		List<Query> queries = Parser.getAllQuery();
		int numQueries = 0;
		int pertinance5;
		int pertinance10;
		int pertinance25;

		double totPertinance5 = 0.0;
		double totPertinance10 = 0.0;
		double totPertinance25 = 0.0;

		for (Query q : queries) {
			numQueries++;
			q.searchParagraph();
			q.parseExpectedParagraph();

			pertinance5 = q.avoirPrecission(5);
			pertinance10 = q.avoirPrecission(10);
			pertinance25 = q.avoirPrecission(25);

			System.out.println(String.format("requete : %d , 5 : %d %.2f , 10 : %d %.2f, 25 : %d %.2f",
				q.getId(), pertinance5, pertinance5/5.0*100, pertinance10, pertinance10*10.0, pertinance25, pertinance25/25.0*100));

			totPertinance5 += pertinance5;
			totPertinance10 += pertinance10;
			totPertinance25 += pertinance25;
		}

		totPertinance5 = totPertinance5/numQueries;
		totPertinance10 = totPertinance10/numQueries;
		totPertinance25 = totPertinance25/numQueries;

		System.out.println(String.format("5 : %.2f %.2f , 10 : %.2f %.2f, 25 : %.2f %.2f",
			totPertinance5, totPertinance5/5.0*100, totPertinance10,totPertinance10*10.0, totPertinance25, totPertinance25/25.0*100));


	}

}
