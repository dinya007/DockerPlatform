package ru.tisov.denis.platform.service;

import java.util.List;

public interface JVMService {

    List<String> get(Long environmentId, Long hostId, String imageName);

}
