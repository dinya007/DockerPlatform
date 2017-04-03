package ru.tisov.denis.platform.service;

import ru.tisov.denis.platform.domain.Host;

import java.util.List;

public interface HostService {

    Host getById(Long id);

    Host getByName(String hostName);

    List<Host> getAll();

    Host getRegistryHost();

}
