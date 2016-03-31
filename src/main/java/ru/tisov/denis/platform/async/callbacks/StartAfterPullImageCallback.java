package ru.tisov.denis.platform.async.callbacks;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.api.model.PullResponseItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tisov.denis.platform.manager.impl.DockerManagerImpl;

import java.io.Closeable;

public class StartAfterPullImageCallback implements ResultCallback<PullResponseItem> {

    private final Logger logger = LoggerFactory.getLogger(DockerManagerImpl.class);

    private final String imageName;
    private final DockerClient dockerClient;
    private final Ports portsBinding;
    private final boolean startAfterCreate;

    public StartAfterPullImageCallback(String imageName, DockerClient dockerClient, Ports portsBinding, boolean startAfterCreate) {
        this.imageName = imageName;
        this.dockerClient = dockerClient;
        this.portsBinding = portsBinding;
        this.startAfterCreate = startAfterCreate;
    }

    @Override
    public void onStart(Closeable closeable) {
        logger.info("Starting load image");
    }

    @Override
    public void onNext(PullResponseItem object) {

    }

    @Override
    public void onError(Throwable throwable) {
        logger.info("Error while load image", throwable);
    }

    @Override
    public void onComplete() {
        logger.info("Image has loaded");
        CreateContainerResponse createContainerResponse = dockerClient.
                createContainerCmd(imageName).
                withPortBindings(portsBinding).exec();
        if (startAfterCreate) dockerClient.startContainerCmd(createContainerResponse.getId()).exec();
    }

    @Override
    public void close() {

    }
}