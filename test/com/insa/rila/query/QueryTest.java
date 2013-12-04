/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.query;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author strokyl
 */
public class QueryTest {
	
	public QueryTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of getAllDocument method, of class Query.
	 */
	@Test
	public void testGetAllDocument() throws Exception {
		System.out.println("getAllDocument");
		Query instance =new Query(1,"balade au Mont Blanc");
		instance.parseExpectedParagraph();
		assertEquals(instance.getExpectedParagraph().size(), 127);
		System.out.println("-----------\n");
		for(Paragraph p : instance.getExpectedParagraph()) {
			//System.out.println(p);
		}
		instance.searchParagraph();
		System.out.println(instance.avoirPrecission(5));
		System.out.println(instance.avoirPrecission(10));
		System.out.println(instance.avoirPrecission(25));
		// TODO review the generated test code and remove the default call to fail.
	}

}
