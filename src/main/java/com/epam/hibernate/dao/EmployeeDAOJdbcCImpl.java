package com.epam.hibernate.dao;

import com.epam.hibernate.model.Address;
import com.epam.hibernate.model.City;
import com.epam.hibernate.model.Country;
import com.epam.hibernate.model.Employee;
import com.epam.hibernate.model.Workplace;
import com.epam.hibernate.util.MessageManager;
import com.epam.hibernate.util.SQLQueryUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public final class EmployeeDAOJdbcCImpl extends AbstractDAO implements EmployeeDAO {

    private static final String SELECT_EMPLOYEE = "select id from employee where id = ?";

    @Autowired
    protected EmployeeDAOJdbcCImpl(DataSource s) {
        super(s);
    }

    @Override
    public List<Employee> getList(int firstResult, int maxResult) {
        final String GET_EMPLOYEES = MessageManager.getStr("GET_EMPLOYEELIST");
        String qs = SQLQueryUtil.generateQsForIn(maxResult);
        final String GET_WORKPLACES = String.format(MessageManager.getStr("GET_WORKPLACELIST"), qs);
        HashMap<Integer, Employee> employeeList = new HashMap<Integer, Employee>();
        HashMap<Integer, Country> countryList = new HashMap<Integer, Country>();
        HashMap<Integer, City> cityList = new HashMap<Integer, City>();
        try {
            if (prepareStatement(GET_EMPLOYEES)) {
                preparedStatement.setInt(1, firstResult);
                preparedStatement.setInt(2, maxResult);
                executeQuery();
                while (resultSet.next()) {
                    int country_id = resultSet.getInt("country_id");
                    Country country = null;
                    if (countryList.containsKey(country_id)) {
                        country = countryList.get(country_id);
                    } else {
                        country = new Country(resultSet.getString("country_name"), new ArrayList<City>());
                        country.setId(country_id);
                        countryList.put(country_id, country);
                    }
                    int city_id = resultSet.getInt("city_id");
                    City city = null;
                    if (cityList.containsKey(city_id)) {
                        city = cityList.get(city_id);
                    } else {
                        city = new City(resultSet.getString("city_name"), country);
                        city.setId(city_id);
                        cityList.put(city_id, city);
                    }
                    country.getCityList().add(city);
                    Address address = new Address(resultSet.getString("address"), city);
                    address.setId(resultSet.getInt("address_id"));
                    city.getAddressList().add(address);
                    Employee employee = new Employee(resultSet.getString("firstname"), resultSet.getString("lastname"), address, new ArrayList<Workplace>());
                    employeeList.put(resultSet.getInt("employee_id"), employee);
                    
                }
            }
            closeAll();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            closeAll();
        }
        return null;
    }
}
