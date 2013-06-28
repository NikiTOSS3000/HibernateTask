package com.epam.hibernate.dao;

import com.epam.hibernate.model.Address;
import com.epam.hibernate.model.City;
import com.epam.hibernate.model.Company;
import com.epam.hibernate.model.Country;
import com.epam.hibernate.model.Employee;
import com.epam.hibernate.model.Office;
import com.epam.hibernate.model.Position;
import com.epam.hibernate.model.Workplace;
import com.epam.hibernate.util.MessageManager;
import com.epam.hibernate.util.SQLQueryUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public final class EmployeeDAOJdbcCImpl extends AbstractDAO implements EmployeeDAO {

    @Autowired
    protected EmployeeDAOJdbcCImpl(DataSource s) {
        super(s);
    }

    @Override
    public List<Employee> getList(int firstResult, int maxResult) {
        final String GET_EMPLOYEES = MessageManager.getStr("GET_EMPLOYEELIST");
        HashMap<Integer, Employee> employeeList = new HashMap<Integer, Employee>();
        HashMap<Integer, Country> countryList = new HashMap<Integer, Country>();
        HashMap<Integer, City> cityList = new HashMap<Integer, City>();
        HashMap<Integer, Company> companyList = new HashMap<Integer, Company>();
        HashMap<Integer, Office> officeList = new HashMap<Integer, Office>();
        try {
            if (prepareStatement(GET_EMPLOYEES)) {
                preparedStatement.setInt(1, firstResult);
                preparedStatement.setInt(2, maxResult);
                executeQuery();
                while (resultSet.next()) {
                    int countryId = resultSet.getInt("country_id");
                    Country country = null;
                    if (countryList.containsKey(countryId)) {
                        country = countryList.get(countryId);
                    } else {
                        country = new Country(resultSet.getString("country_name"), new ArrayList<City>());
                        country.setId(countryId);
                        countryList.put(countryId, country);
                    }
                    int cityId = resultSet.getInt("city_id");
                    City city = null;
                    if (cityList.containsKey(cityId)) {
                        city = cityList.get(cityId);
                    } else {
                        city = new City(resultSet.getString("city_name"), country);
                        city.setId(cityId);
                        cityList.put(cityId, city);
                    }
                    country.getCityList().add(city);
                    Address address = new Address(resultSet.getString("address_address"), city);
                    address.setId(resultSet.getInt("address_id"));
                    city.getAddressList().add(address);
                    Employee employee = new Employee(resultSet.getString("firstname"), resultSet.getString("lastname"), address, new ArrayList<Workplace>());
                    employeeList.put(resultSet.getInt("employee_id"), employee);

                }
            }
            closeAll();
            String qs = SQLQueryUtil.generateQsForIn(employeeList.size());
            final String GET_WORKPLACES = String.format(MessageManager.getStr("GET_WORKPLACELIST"), qs);
            if (prepareStatement(GET_WORKPLACES)) {
                Iterator<Integer> iterator = employeeList.keySet().iterator();
                for (int i = 0; i < employeeList.size(); i++) {
                    preparedStatement.setInt(i + 1, iterator.next());
                }
                executeQuery();
                while (resultSet.next()) {
                    int countryId = resultSet.getInt("country_id");
                    Country country = null;
                    if (countryList.containsKey(countryId)) {
                        country = countryList.get(countryId);
                    } else {
                        country = new Country(resultSet.getString("country_name"), new ArrayList<City>());
                        country.setId(countryId);
                        countryList.put(countryId, country);
                    }
                    int cityId = resultSet.getInt("city_id");
                    City city = null;
                    if (cityList.containsKey(cityId)) {
                        city = cityList.get(cityId);
                    } else {
                        city = new City(resultSet.getString("city_name"), country);
                        city.setId(cityId);
                        cityList.put(cityId, city);
                    }
                    country.getCityList().add(city);
                    Address address = new Address(resultSet.getString("address_address"), city);
                    address.setId(resultSet.getInt("address_id"));
                    city.getAddressList().add(address);
                    int companyId = resultSet.getInt("company_id");
                    Company company = null;
                    if (companyList.containsKey(companyId)) {
                        company = companyList.get(companyId);
                    } else {
                        company = new Company(resultSet.getString("company_name"), new ArrayList<Office>());
                        company.setId(companyId);
                        companyList.put(companyId, company);
                    }
                    int officeId = resultSet.getInt("office_id");
                    Office office = null;
                    if (officeList.containsKey(officeId)) {
                        office = officeList.get(officeId);
                    } else {
                        office = new Office(address, new ArrayList<Workplace>(), company);
                        office.setId(officeId);
                        office.setEmployeesCount(resultSet.getInt("employee_count"));
                        officeList.put(officeId, office);
                    }
                    Position position = new Position(resultSet.getString("position_name"));
                    position.setId(resultSet.getInt("position_id"));
                    Workplace workplace = new Workplace(position, office, null);
                    workplace.setId(resultSet.getInt("workplace_id"));
                    int employeeId = resultSet.getInt("employee_id");
                    Employee employee = employeeList.get(employeeId);
                    employee.getWorkplaceList().add(workplace);
                    workplace.setEmployee(employee);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            closeAll();
        }
        return new ArrayList<Employee>(employeeList.values());
    }

    @Override
    public long employeeCount() {
        long count = 0;
        String employeeCount = MessageManager.getStr("EMPLOYEE_COUNT");
        try {
            if (prepareStatement(employeeCount)) {
                executeQuery();
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            closeAll();
        }
        return count;
    }
}
