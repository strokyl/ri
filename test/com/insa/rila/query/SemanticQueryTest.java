/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.query;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author strokyl
 */
public class SemanticQueryTest {
	@Test
	public void testSomeMethod() throws IOException {
		SemanticQuery sq = new SemanticQuery("les lac en France Balade peche");
		System.out.println("\n\n\n");
		System.out.println(sq.getVector());
		
	}
	
}
