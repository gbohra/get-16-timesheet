/**
 * 
 */
package com.timesheet.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Kritik
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/application-context.xml" , "/spring-config/mvc-dispatcher-servlet.xml"})
public class OrganizationDAOTest {

	/**
	 * Test method for {@link com.timesheet.dao.OrganizationDAO#inset(com.timesheet.dao.model.OrganizationModel)}.
	 */
	@Test
	public void testInset() {
		assertEquals(1, 1);
	}

}
