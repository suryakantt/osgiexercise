package com.mysite.core.services.impl;

import com.mysite.core.services.runmodesconfigstest;
import com.mysite.core.services.student;
import com.mysite.core.services.runmodesinterface;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Component(service = runmodesinterface.class)
@Designate(ocd= runmodesconfigstest.class)
public class runmodesservice implements runmodesinterface {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public static int cc1,cc2;

    @Activate
    public void activate(runmodesconfigstest conf){
        cc1=conf.cc1();
        cc2=conf.cc2();
        LOGGER.info("activate");

    }


    @Modified
    public void modify(runmodesconfigstest conf){
        cc1=conf.cc1();
        cc2=conf.cc2();
        LOGGER.info("modified");
    }

    @Deactivate
    public void deactivate() {
        LOGGER.info("deactivated");
    }


    @Override
    public int getcc1() {
        return cc1;
    }

    @Override
    public int getcc2() {
        return cc2;
    }
}
