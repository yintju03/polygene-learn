package com.yintju03.example.polygene.ms1.echo.activator;

import com.yintju03.example.polygene.ms1.echo.Echo;

import org.apache.polygene.api.activation.ActivatorAdapter;
import org.apache.polygene.api.service.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoActivator extends ActivatorAdapter<ServiceReference<Echo>> {
    private static final Logger logger = LoggerFactory.getLogger(EchoActivator.class);

    @Override
    public void afterActivation(ServiceReference<Echo> activated) throws Exception {
        logger.info("afterActivation, {}", activated);
        super.afterActivation(activated);
    }

    @Override
    public void afterPassivation(ServiceReference<Echo> passivated) throws Exception {
        logger.info("afterPassivation, {}", passivated);
        super.afterPassivation(passivated);
    }

    @Override
    public void beforeActivation(ServiceReference<Echo> activating) throws Exception {
        logger.info("beforeActivation, {}", activating);
        super.beforeActivation(activating);
    }

    @Override
    public void beforePassivation(ServiceReference<Echo> passivating) throws Exception {
        logger.info("beforePassivation, {}", passivating);
        super.beforePassivation(passivating);
    }
    
}
