package com.mysite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ImportantLinksModel {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @SlingObject
    ResourceResolver rs;

    @Inject
    private List<ImportantLinksPojo> links;

    public List<ImportantLinksPojo> getLinks() {
        return links;
    }

}













