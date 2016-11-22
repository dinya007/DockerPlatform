package ru.tisov.denis.platform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.domain.JVMOption;
import ru.tisov.denis.platform.service.EnvironmentService;
import ru.tisov.denis.platform.service.HostService;

import javax.annotation.PostConstruct;

@Component
public class JVMOptionsLoaderImpl implements JVMOptionsLoader {

    private final JVMConfiguratorImpl jvmConfigurator;

    //TODO remove it
    @Autowired
    private EnvironmentService environmentService;
    @Autowired
    private HostService hostService;
    private String imageName = "127.0.0.1:5000/docker_1:latest";

    @Autowired
    public JVMOptionsLoaderImpl(JVMConfiguratorImpl jvmConfigurator) {
        this.jvmConfigurator = jvmConfigurator;
    }

    //TODO remove it
    @PostConstruct
    private void init() {
        addJVMOption(new JVMOption(environmentService.getById(0L), hostService.getById(0L), imageName, "-Xmx512m -Xms256m"));
    }

    @Override
    public void addJVMOption(JVMOption options) {
        jvmConfigurator.addJVMOptions(options);
    }
}
