/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.xml.querie;

import com.insa.rila.query.Query;
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
public class ParserTest {
	
	public ParserTest() {
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
	 * Test of getAllQuery method, of class Parser.
	 */
	@Test
	public void testGetAllQuery() throws Exception {
		System.out.println("getAllQuery");
		List<Query> result = Parser.getAllQuery();
		assertEquals(result.size(), 11);
	}
	
}
