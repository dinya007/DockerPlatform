package ru.tisov.denis.platform.service;

import ru.tisov.denis.platform.domain.Property;
import ru.tisov.denis.platform.domain.docker.Container;

import java.util.List;

/**
 * @author dinyat
 *         03/04/2017
 */
public interface PropertyService {

    List<Property> get(Container container);

}
