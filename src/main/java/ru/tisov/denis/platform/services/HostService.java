package ru.tisov.denis.platform.services;

import ru.tisov.denis.platform.domain.Host;

import java.util.List;

public interface HostService {

    Host getById(Long id);

    List<Host> getAll();

    List<Host> getAll(List<Long> ids);


}
