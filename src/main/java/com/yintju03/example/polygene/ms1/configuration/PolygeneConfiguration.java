package com.yintju03.example.polygene.ms1.configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.polygene.api.activation.ActivationException;
import org.apache.polygene.api.activation.PassivationException;
import org.apache.polygene.api.structure.Application;
import org.apache.polygene.bootstrap.ApplicationAssembly;
import org.apache.polygene.bootstrap.Energy4Java;
import org.apache.polygene.bootstrap.LayerAssembly;
import org.apache.polygene.bootstrap.ModuleAssembly;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PolygeneConfiguration {
    @Bean
    public Energy4Java polygene() {
        return new Energy4Java();
    }

    @Bean
    public Application application(Map<String,CreateLayer> mcl, Map<String,CreateModule> mcm) throws ActivationException {
        Application application = polygene().newApplication(factory -> {
            ApplicationAssembly assembly = factory.newApplicationAssembly();
            Map<String,ImmutablePair<LayerAssembly,String[]>> result = new HashMap<>();
            mcl.entrySet().forEach(v -> {
                ImmutablePair<LayerAssembly,String[]> las = v.getValue().create(assembly);
                mcm.entrySet().stream()
                    .filter(u -> v.getKey().equals(u.getValue().layer))
                    .forEach(u -> u.getValue().create.apply(las.getLeft()));
                result.put(v.getKey(), las);
            });
            result.entrySet().forEach(v ->
                Arrays.asList(v.getValue().getRight())
                    .stream()
                    .distinct()
                    .forEach(u ->
                        v.getValue().getLeft()
                            .uses(result.get(u).getLeft())));
            return assembly;
        });
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    application.passivate();
                } catch (PassivationException e) {
                    e.printStackTrace();
                }
            }
            
        }));
        application.activate();
        return application;
    }

    @FunctionalInterface
    public interface CreateLayer {
        ImmutablePair<LayerAssembly,String[]> create(ApplicationAssembly app);
    }

    public static class CreateModule {
        private String layer;
        private Function<LayerAssembly,ModuleAssembly> create;

        public CreateModule(String layer, Function<LayerAssembly,ModuleAssembly> create) {
            this.layer = layer;
            this.create = create;
        }
    }

    @FunctionalInterface
    public interface FindService<T,R> {
        R find(T t);
    }

    @Bean
    public <T> FindService<Class<T>,T> findService(Application application) {
        return serviceType -> application.layers()
            .flatMap(v -> v.modules())
            .filter(v -> v.findService(serviceType).isAvailable())
            .map(v -> v.findService(serviceType).get())
            .findFirst()
            .orElse(null);
    }

}
