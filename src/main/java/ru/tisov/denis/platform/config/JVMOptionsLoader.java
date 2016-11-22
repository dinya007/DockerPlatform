package ru.tisov.denis.platform.config;

import ru.tisov.denis.platform.domain.JVMOption;

public interface JVMOptionsLoader {

    void addJVMOption(JVMOption options);

}
