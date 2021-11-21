package com.mysite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImportantLinksPojo {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String url;

    @ValueMapValue
    private String option;

    @PostConstruct
    public void init() {
        if (url.startsWith("/content")) {
            url += ".html";
        }
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getOption() {
        return option;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public void setOption(String option) {
        this.option = option;
    }


    }
