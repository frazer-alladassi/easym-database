package com.easym.database.test;

import com.easym.database.domains.Employee;
import com.easym.database.repositories.EmployeeRepository;
import com.easym.database.test.config.MultiTenantMongoTestDBFactory;
import com.easym.database.test.config.TenantTest;
import com.easym.database.test.config.TenantTestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {MultiTenantMongoTestDBFactory.class})
@Transactional
@TestPropertySource("classpath:easym-database-test.properties")
public class EmployeeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void verif() {
        Employee employee = Employee.builder()
                .firstName("Divyam")
                .lastName("ALLADASSI")
                .email(String.format("%s%s", "divyam", "@localhost.com" ))
                .build();
        TenantTestContext.setTenant(new TenantTest("admin", "rtadmin", "rtadminpwd".toCharArray()));
        employee = employeeRepository.save(employee);
        TenantTestContext.clear();
        Assert.assertNotNull(employee.getId());
    }
}
