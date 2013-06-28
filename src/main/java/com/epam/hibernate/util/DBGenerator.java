package com.epam.hibernate.util;

import com.epam.hibernate.model.Address;
import com.epam.hibernate.model.City;
import com.epam.hibernate.model.Company;
import com.epam.hibernate.model.Country;
import com.epam.hibernate.model.Employee;
import com.epam.hibernate.model.Office;
import com.epam.hibernate.model.Position;
import com.epam.hibernate.model.Workplace;
import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public final class DBGenerator {

    private static final Random random = new Random();
    @Autowired
    private static SessionFactory sf;

    private DBGenerator() {
    }

    private static int count() {
        Session session = sf.getCurrentSession();
        int answer = ((Long) session.createQuery("select count(*) from Employee").uniqueResult()).intValue();
        return answer;
    }

    private static String randStr(int size) {
        return RandomStringUtils.randomAlphabetic(size);
    }

    public static void generateDB(int amount) {
        Session session = null;
        do {
            ArrayList<Address> addresses = new ArrayList<Address>();
            ArrayList<Employee> employees = new ArrayList<Employee>();
            ArrayList<Position> positions = new ArrayList<Position>();
            Country country = new Country(randStr(8), new ArrayList<City>());
            int cityAmount = random.nextInt(3) + 1;
            for (int i = 0; i < cityAmount; i++) {
                City city = new City(randStr(6), country);
                country.getCityList().add(city);
                int addressAmount = random.nextInt(15) + 1;
                for (int j = 0; j < addressAmount; j++) {
                    Address address = new Address(randStr(10), city);
                    city.getAddressList().add(address);
                    addresses.add(address);
                }
            }
            int employeeAmount = random.nextInt(100) + 1;
            for (int i = 0; i < employeeAmount; i++) {
                Address address = addresses.get(random.nextInt(addresses.size()));
                Employee employee = new Employee(randStr(5), randStr(5), address, new ArrayList<Workplace>());
                employees.add(employee);
            }
            int companyAmount = random.nextInt(5) + 1;
            for (int i = 0; i < companyAmount; i++) {
                Company company = new Company(randStr(7), new ArrayList<Office>());
                Position position = new Position(randStr(15));
                int officeAmount = random.nextInt(10) + 1;
                for (int j = 0; j < officeAmount; j++) {
                    positions.add(position);
                    Address address = addresses.get(random.nextInt(addresses.size()));
                    Office office = new Office(address, new ArrayList<Workplace>(), company);
                    company.getOfficeList().add(office);
                    int workplaceAmount = random.nextInt(20) + 1;
                    for (int k = 0; k < workplaceAmount; k++) {
                        Employee employee = employees.get(random.nextInt(employees.size()));
                        position = positions.get(random.nextInt(positions.size()));
                        Workplace workplace = new Workplace(position, office, employee);
                        office.getWorkplaceList().add(workplace);
                        employee.getWorkplaceList().add(workplace);
                    }
                }
            }
            int i = 0;
            for (Employee employee : employees) {
                session = sf.getCurrentSession();
                session.save(employee);
                if (++i % 50 == 0) {
                 session.flush();
                 session.clear();
                 }
            }
        } while (amount > count());
    }
}
