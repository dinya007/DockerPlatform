package ru.tisov.denis.platform.da;

import java.util.List;

public interface JVMDao {

    List<String> get(Long environmentId, Long hostId, String imageName);

}
