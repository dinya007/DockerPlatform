package ru.tisov.denis.platform.service;

import ru.tisov.denis.platform.domain.docker.Container;

/**
 * @author dinyat
 *         03/04/2017
 */
public interface PropertyService {

    Property get(Container container);


 class Property {

     public final String name;
     public final String value;

     public Property(String name, String value) {
         this.name = name;
         this.value = value;
     }

     @Override
     public boolean equals(Object o) {
         if (this == o) {
             return true;
         }
         if (o == null || getClass() != o.getClass()) {
             return false;
         }

         Property property = (Property) o;

         return name != null ? name.equals(property.name) : property.name == null;
     }

     @Override
     public int hashCode() {
         return name != null ? name.hashCode() : 0;
     }

     @Override
     public String toString() {
         return "Property{" +
             "name='" + name + '\'' +
             ", value='" + value + '\'' +
             '}';
     }
 }

}
