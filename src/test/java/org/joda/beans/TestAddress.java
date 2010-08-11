/*
 *  Copyright 2001-2010 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.beans;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import java.util.Map;
import java.util.NoSuchElementException;

import org.testng.annotations.Test;

/**
 * Test property using Person.
 */
@Test
public class TestAddress {

    private static final int NUM_PROPERTIES = 3;
    private static final String STREET = "street";
    private static final String CITY = "city";
    private static final String NUMBER = "number";

    public void test_bean() {
        Bean test = Address.meta().createBean();
        
        assertEquals(test instanceof Address, true);
        
        assertEquals(test.metaBean(), Address.meta());
        
        assertEquals(test.propertyExists(STREET), true);
        assertEquals(test.propertyExists(CITY), true);
        assertEquals(test.propertyExists(NUMBER), true);
        assertEquals(test.propertyExists("Rubbish"), false);
        
        assertEquals(test.property(STREET).name(), STREET);
        assertEquals(test.property(CITY).name(), CITY);
        assertEquals(test.property(NUMBER).name(), NUMBER);
    }

    @Test(expectedExceptions=NoSuchElementException.class)
    public void test_bean_invalidPropertyName() {
        Bean test = Address.meta().createBean();
        try {
            test.property("Rubbish");
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    //-----------------------------------------------------------------------
    public void test_metaBean() {
        MetaBean test = Address.meta();
        
        assertEquals(test.beanType(), Address.class);
        
        assertEquals(test.beanName(), Address.class.getName());
        
        assertEquals(test.metaPropertyCount(), NUM_PROPERTIES);
        
        assertEquals(test.metaPropertyExists(STREET), true);
        assertEquals(test.metaPropertyExists(CITY), true);
        assertEquals(test.metaPropertyExists(NUMBER), true);
        assertEquals(test.metaPropertyExists("Rubbish"), false);
        
        assertEquals(test.metaProperty(STREET).name(), STREET);
        assertEquals(test.metaProperty(CITY).name(), CITY);
        assertEquals(test.metaProperty(NUMBER).name(), NUMBER);
        
        Map<String, MetaProperty<Object>> map = test.metaPropertyMap();
        assertEquals(map.size(), NUM_PROPERTIES);
        assertEquals(map.containsKey(STREET), true);
        assertEquals(map.containsKey(CITY), true);
        assertEquals(map.containsKey(NUMBER), true);
    }

    @Test(expectedExceptions=NoSuchElementException.class)
    public void test_metaBean_invalidPropertyName() {
        MetaBean test = Address.meta();
        try {
            test.metaProperty("Rubbish");
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    //-----------------------------------------------------------------------
    public void test_namedPropertyMethod() {
        Address address = new Address();
        Property<String> test = address.street();
        
        assertSame(test.bean(), address);
        assertSame(test.metaProperty(), Address.meta().street());
        
        assertEquals(test.get(), null);
        address.setStreet("A");
        assertEquals(test.get(), "A");
        test.set("B");
        assertEquals(test.get(), "B");
        assertEquals(test.put("C"), "B");
        assertEquals(test.get(), "C");
    }

    //-----------------------------------------------------------------------
    public void test_property_String() {
        Address address = new Address();
        Property<String> test = address.property(STREET);
        
        assertSame(test.bean(), address);
        assertSame(test.metaProperty(), Address.meta().street());
        
        assertEquals(test.get(), null);
        address.setStreet("A");
        assertEquals(test.get(), "A");
        test.set("B");
        assertEquals(test.get(), "B");
        assertEquals(test.put("C"), "B");
        assertEquals(test.get(), "C");
    }

    //-----------------------------------------------------------------------
    public void test_propertyMap() {
        Address address = new Address();
        PropertyMap test = address.propertyMap();
        
        assertSame(test.size(), NUM_PROPERTIES);
        assertEquals(test.containsKey(STREET), true);
        assertEquals(test.containsKey(CITY), true);
        assertEquals(test.containsKey(NUMBER), true);
    }

    public void test_propertyMap_flatten() {
        Address address = new Address();
        address.setStreet("A");
        address.setCity("B");
        address.setNumber(3);
        Map<String, Object> test = address.propertyMap().flatten();
        
        assertSame(test.size(), NUM_PROPERTIES);
        assertEquals(test.get(STREET), "A");
        assertEquals(test.get(CITY), "B");
        assertEquals(test.get(NUMBER), 3);
    }

    //-----------------------------------------------------------------------
    public void test_namedMetaPropertyMethod() {
        Address address = new Address();
        MetaProperty<String> test = Address.meta().street();
        
        assertSame(test.metaBean().beanType(), Address.class);
        assertSame(test.propertyType(), String.class);
        assertSame(test.name(), STREET);
        assertEquals(test.readWrite(), PropertyReadWrite.READ_WRITE);
        
        assertEquals(test.get(address), null);
        address.setStreet("A");
        assertEquals(test.get(address), "A");
        test.set(address, "B");
        assertEquals(test.get(address), "B");
        assertEquals(test.put(address, "C"), "B");
        assertEquals(test.get(address), "C");
    }

    //-----------------------------------------------------------------------
    public void test_metaProperty_String() {
        Address address = new Address();
        MetaProperty<String> test = Address.meta().metaProperty(STREET);
        
        assertSame(test.metaBean().beanType(), Address.class);
        assertSame(test.propertyType(), String.class);
        assertSame(test.name(), STREET);
        assertEquals(test.readWrite(), PropertyReadWrite.READ_WRITE);
        
        assertEquals(test.get(address), null);
        address.setStreet("A");
        assertEquals(test.get(address), "A");
        test.set(address, "B");
        assertEquals(test.get(address), "B");
        assertEquals(test.put(address, "C"), "B");
        assertEquals(test.get(address), "C");
    }

}