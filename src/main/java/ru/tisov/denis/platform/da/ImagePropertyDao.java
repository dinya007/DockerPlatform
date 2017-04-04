package ru.tisov.denis.platform.da;

import ru.tisov.denis.platform.domain.Property;

import java.util.List;

/**
 * @author dinyat
 *         04/04/2017
 */
public interface ImagePropertyDao {

    List<Property> get(String imageName);

}
