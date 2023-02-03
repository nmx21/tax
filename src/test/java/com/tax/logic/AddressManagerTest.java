package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.exception.DBException;
import com.tax.db.entity.Address;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressManagerTest {

    @Test
    @Order(1)
    void findAdressById() throws SQLException, DBException {
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.findAddressByCompanyId(any(), anyInt())).thenReturn(new Address());
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            AddressManager addressManager = AddressManager.getInstance();
            Address address = addressManager.findAddressById(1);
            assertNotNull(address);

            when(connectionPool.findAddressByCompanyId(any(), anyInt())).thenThrow(SQLException.class);
            Exception exception = assertThrows(DBException.class, () ->
                    addressManager.findAddressById(1));
            assertEquals("Cannot find address by id", exception.getMessage());
        }
    }
    @Test
    @Order(2)
    void createAddress()  throws SQLException, DBException{
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            Connection con = Mockito.mock(Connection.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.getConnection()).thenReturn(con);
            Address address = new Address();
            when(connectionPool.createAddress(any(), any(Address.class))).thenReturn(1);
            AddressManager addressManager = AddressManager.getInstance();
            addressManager.createAddress(address);
//            when(connectionPool.createAddress(any(), any(Address.class))).thenThrow(SQLException.class);
//            Exception exception = assertThrows(DBException.class,
//                    () -> addressManager.createAddress(address)
//            );
//            assertEquals("Cannot create address ", exception.getMessage());
        }
    }

    @Test
    @Order(4)
    void createAddressVerify() throws DBException, SQLException {
        AddressManager addressManager = Mockito.mock(AddressManager.class);
        Address address = new Address();
        doNothing().when(addressManager).createAddress(address);
        addressManager.createAddress(address);
        verify(addressManager, times(1)).createAddress(address);
    }


    @Test
    @Order(3)
    void findAllAddress() throws SQLException, DBException {
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.findAllAddress(any())).thenReturn(new ArrayList<>());
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            AddressManager addressManager = AddressManager.getInstance();
            List<Address> addresses = addressManager.findAllAddress();
            assertNotNull(addresses);

//            when(connectionPool.findAllAddress(any())).thenThrow(SQLException.class);
//            Exception exception = assertThrows(DBException.class, () ->
//                    addressManager.findAllAddress());
//            assertEquals("Cannot find all addresses ", exception.getMessage());

        }
    }
}