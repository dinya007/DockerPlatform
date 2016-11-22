package ru.tisov.denis.platform.config;

import ru.tisov.denis.platform.domain.Environment;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.JVMOption;

public interface JVMConfigurator {

    JVMOption getJVMOptions(Environment environment, Host host, String image);

}
