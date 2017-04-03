package ru.tisov.denis.platform.async.callbacks;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PullResponseItem;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tisov.denis.platform.config.JVMConfigurator;
import ru.tisov.denis.platform.domain.JVMOption;
import ru.tisov.denis.platform.domain.StartContainerParams;
import ru.tisov.denis.platform.service.impl.ContainerServiceImpl;

import java.io.Closeable;

public class StartAfterPullImageCallback implements ResultCallback<PullResponseItem> {

    private final Logger logger = LoggerFactory.getLogger(ContainerServiceImpl.class);

    private final DockerClient dockerClient;
    private final boolean startAfterCreate;
    private final StartContainerParams params;
    private final JVMConfigurator jvmConfigurator;


    public StartAfterPullImageCallback(boolean startAfterCreate, DockerClient dockerClient, StartContainerParams params, JVMConfigurator jvmConfigurator) {
        this.dockerClient = dockerClient;
        this.startAfterCreate = startAfterCreate;
        this.params = params;
        this.jvmConfigurator = jvmConfigurator;
    }

    @Override
    public void onStart(Closeable closeable) {
        logger.debug("Start loading image");
    }

    @Override
    public void onNext(PullResponseItem object) {

    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("Error while loading image", throwable);
    }

    @Override
    public void onComplete() {
        logger.info("Image has loaded " + params.getImageName());

        CreateContainerCmd containerCmd = dockerClient.
                createContainerCmd(params.getImageName()).withName(params.getAppName());

        if (params.getPortsBinding() != null)
            containerCmd = containerCmd
                    .withExposedPorts(new ExposedPort(ru.tisov.denis.platform.enums.Ports.DEFAULT_PORT.getPort()))
                    .withPortBindings(params.getPortsBinding());

        if (!StringUtils.isEmpty(params.getNetworkName()))
            containerCmd = containerCmd
                    .withNetworkDisabled(false)
                    .withNetworkMode(params.getNetworkName());

        JVMOption jvmOptions = jvmConfigurator.getJVMOptions(params.getEnvironment(), params.getHost(), params.getImageName());

        if (jvmOptions != null) containerCmd.withEnv("JAVA_OPTS=" + jvmOptions.getOptions() + " -Dfirst.name=Denis -Dlast.name=Tisov");

        CreateContainerResponse createContainerResponse = containerCmd.exec();

        if (startAfterCreate) dockerClient.startContainerCmd(createContainerResponse.getId()).exec();
    }

    @Override
    public void close() {

    }
}