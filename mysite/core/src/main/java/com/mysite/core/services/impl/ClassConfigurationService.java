package com.mysite.core.services.impl;

import com.mysite.core.services.Configs;
import com.mysite.core.services.student;
import com.mysite.core.services.ConfigsInterface;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component(service = ClassConfigurationService.class)
@Designate(ocd=Configs.class)
public class ClassConfigurationService implements ConfigsInterface{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    int maxsize,minmarks;

    @Activate
    public void activate(Configs conf){
        maxsize=conf.limit();
        minmarks=conf.passMarks();
        LOGGER.info("activate");

    }


    @Modified
    public void modify(Configs conf){
        maxsize=conf.limit();
        minmarks=conf.passMarks();
        LOGGER.info("modified");
    }

    @Deactivate
    public void deactivate() {
        LOGGER.info("deactivated");
    }

    @Override
    public boolean isClassLimitReached(List<student> studentList){
        if(studentList.size()<maxsize)
            return true;
        return false;
    }
    @Override
    public int getPassingMarks(){
        return minmarks;
    }

}
