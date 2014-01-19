/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.main;

import com.insa.rila.query.Paragraph;
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
		final int numParagraphToShow = 10;
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

			System.out.println(String.format("requete %d : %s", q.getId(), q.getQuery()));

			System.out.println("	Expected paragraph : ");

			System.out.println(String.format("	Vecteur de recherche : %s", q.getVector()));
			System.out.println(String.format("	Pertinance à 5 	: %d /5		%.2f %%", pertinance5, pertinance5 / 5.0 * 100));
			System.out.println(String.format("	Pertinance à 10 : %d /10	%.2f %%", pertinance10, pertinance10 / 10.0 * 100));
			System.out.println(String.format("	Pertinance à 25 : %d /25	%.2f %%", pertinance25, pertinance25 / 25.0 * 100));

			System.out.println("\n Expected paragraph");
			int i = 0;
			for (Paragraph p : q.getExpectedParagraph()) {
				if (i == numParagraphToShow) {
					break;
				}
				System.out.println(p.getText());
				i++;
				System.out.println("\n");
			}

			System.out.println("	Founded paragraph : ");

			i = 0;
			for (Paragraph p : q.getResult()) {
				if (i == numParagraphToShow) {
					break;
				}

				System.out.println(p.getText());
				i++;
				System.out.println("\n");
			}

			totPertinance5 += pertinance5;
			totPertinance10 += pertinance10;
			totPertinance25 += pertinance25;

			System.out.println("\n\n");
		}

		totPertinance5 = totPertinance5 / numQueries;
		totPertinance10 = totPertinance10 / numQueries;
		totPertinance25 = totPertinance25 / numQueries;

		System.out.println(String.format("	Pertinance à 5 	: %.3f /5		%.2f %%", totPertinance5, totPertinance5 / 5.0 * 100));
		System.out.println(String.format("	Pertinance à 10 : %.3f /10	%.2f %%", totPertinance10, totPertinance10 / 10.0 * 100));
		System.out.println(String.format("	Pertinance à 25 : %.3f /25	%.2f %%", totPertinance25, totPertinance25 / 25.0 * 100));

	}

}
