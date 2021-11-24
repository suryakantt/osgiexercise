package com.mysite.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.tagging.JcrTagManagerFactory;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RelatedPagesPojo extends WCMUsePojo {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    List<String> listResources;
    List<String> listResources1;

    @Reference
    JcrTagManagerFactory jcrTagManagerFactory;

    @Override
    public void activate() throws Exception {

        LOGGER.info("Starting the activate method...");

        ValueMap properties = getValueMapForRelatedPages();

        String namespace = properties.get("namespace", String.class);

        //First fetch all the pages tagged with the authored namespace

        String[] tags = properties.get("additionalTags", String[].class);
        ResourceResolver resourceResolver = getResourceResolver();
        TagManager tagManager = resourceResolver.adaptTo(TagManager.class);

        Tag tag1 = tagManager.resolve(namespace);

        Iterator<Resource> resourceIterator1 = tag1.find();
        listResources1 = new ArrayList<String>();

        for(int i = 0; resourceIterator1.hasNext(); ++i){
            Resource resource1 = resourceIterator1.next();
            String resourcePath1 = resource1.getPath();
            listResources1.add(resourcePath1);
        }

        //Now fetch all the pages tagged with additional tags

        for(String value : tags){
            Tag tag = tagManager.resolve(value);

            //Finding all the resources tagged with the tag object
            Iterator<Resource> resourceIterator = tag.find();

            listResources = new ArrayList<String>();
            for(int i = 0; resourceIterator.hasNext(); ++i){
                Resource resource = resourceIterator.next();
                String resourcePath = resource.getPath();
                listResources.add(resourcePath);
            }
        }
        listResources.addAll(listResources1);
    }

    private ValueMap getValueMapForRelatedPages() {
        String pagePath = "/content/mysite/fr/jcr:content/root/container/container/relatedpages";
        Resource resource = getResourceResolver().getResource(pagePath);
        ValueMap valueMap = resource.adaptTo(ValueMap.class);
        return valueMap;
    }

    public List<String> getMyResources() {

        LOGGER.info("Returning the list of resources: {}", listResources);
        return listResources;
    }

}