package ru.tisov.denis.platform.services;


import ru.tisov.denis.platform.domain.Application;
import ru.tisov.denis.platform.domain.Host;

import java.util.List;

public interface ApplicationService {

    List<Application> getRunningApplications(Host host);

}
