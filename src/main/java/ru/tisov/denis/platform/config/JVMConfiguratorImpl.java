package ru.tisov.denis.platform.config;

import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.domain.Environment;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.JVMOption;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class JVMConfiguratorImpl implements JVMConfigurator {

    private ConcurrentHashMap<Triple<Environment, Host, String>, JVMOption> configs = new ConcurrentHashMap<>();


    @Override
    public JVMOption getJVMOptions(Environment environment, Host host, String image) {
        return configs.get(new Triple<>(environment, host, image));
    }

    void addJVMOptions(JVMOption jvmOptions) {
        configs.put(new Triple<>(jvmOptions.getEnvironment(), jvmOptions.getHost(), jvmOptions.getImage()), jvmOptions);
    }

    private static class Triple<K, V, P> {

        private final K _1;
        private final V _2;
        private final P _3;

        private Triple(K _1, V _2, P _3) {
            this._1 = _1;
            this._2 = _2;
            this._3 = _3;
        }

        public K get_1() {
            return _1;
        }

        public V get_2() {
            return _2;
        }

        public P get_3() {
            return _3;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;

            if (_1 != null ? !_1.equals(triple._1) : triple._1 != null) return false;
            if (_2 != null ? !_2.equals(triple._2) : triple._2 != null) return false;
            return _3 != null ? _3.equals(triple._3) : triple._3 == null;

        }

        @Override
        public int hashCode() {
            int result = _1 != null ? _1.hashCode() : 0;
            result = 31 * result + (_2 != null ? _2.hashCode() : 0);
            result = 31 * result + (_3 != null ? _3.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Triple{" +
                    "_1=" + _1 +
                    ", _2=" + _2 +
                    ", _3=" + _3 +
                    '}';
        }
    }
}
