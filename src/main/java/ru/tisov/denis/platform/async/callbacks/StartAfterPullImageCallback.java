package ru.tisov.denis.platform.async.callbacks;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.api.model.PullResponseItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tisov.denis.platform.services.impl.ContainerServiceImpl;

import java.io.Closeable;

public class StartAfterPullImageCallback implements ResultCallback<PullResponseItem> {

    private final Logger logger = LoggerFactory.getLogger(ContainerServiceImpl.class);

    private final String imageName;
    private final DockerClient dockerClient;
    private final Ports portsBinding;
    private final String appName;
    private final boolean startAfterCreate;

    public StartAfterPullImageCallback(String imageName, String appName, DockerClient dockerClient, Ports portsBinding, boolean startAfterCreate) {
        this.imageName = imageName;
        this.appName = appName;
        this.dockerClient = dockerClient;
        this.portsBinding = portsBinding;
        this.startAfterCreate = startAfterCreate;
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
        logger.info("Image has loaded " + imageName);

        CreateContainerCmd containerCmd = dockerClient.
                createContainerCmd(imageName).withName(appName);

        if (portsBinding != null) containerCmd = containerCmd.withPortBindings(portsBinding);


        CreateContainerResponse createContainerResponse = containerCmd.exec();
        if (startAfterCreate) dockerClient.startContainerCmd(createContainerResponse.getId()).exec();
    }

    @Override
    public void close() {

    }
}