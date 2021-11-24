package com.mysite.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import com.adobe.granite.asset.api.Asset;
import com.adobe.granite.asset.api.AssetManager;
import com.adobe.granite.asset.api.Rendition;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AssetFetchModel extends WCMUsePojo {
    List<String> myRenditions;
    boolean specificRendition;
    String specificResolution;


    @Override
    public void activate() throws Exception {
        ValueMap properties = getValueMapForRenditionComponent();
        String path = properties.get("assetpath", String.class);
        ResourceResolver resourceResolver = getResourceResolver();
        AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);
        Asset asset = assetManager.getAsset(path);

        Iterator<Rendition> renditions = (Iterator<Rendition>) (asset.listRenditions());
        myRenditions = new ArrayList<String>();

        for(int i = 0;renditions.hasNext(); ++i){
            Rendition rendition = renditions.next();
            String name = rendition.getPath();
            myRenditions.add(name);
        }
        String renderedImage="";
        String imgResolution=properties.get("resolution", String.class);

        for(int j = 0; j < myRenditions.size(); j++){
            String mypath = myRenditions.get(j);
            if(mypath.contains("original")){
                renderedImage = mypath;
            }
            specificRendition = mypath.contains(imgResolution);
            if(specificRendition){
                specificResolution = mypath;
                break;
            }
        }
        if(!specificRendition){
            specificResolution = renderedImage;
        }
    }

    private ValueMap getValueMapForRenditionComponent() {
        String specificPath = "/content/mysite/fr/jcr:content/root/container/container/assetfetch";
        Resource myResource = getResourceResolver().getResource(specificPath);
        ValueMap valueMap = myResource.adaptTo(ValueMap.class);
        return valueMap;
    }

    public List<String> getRenditionList() {return myRenditions;}
    public String getSpecificRendition() {return specificResolution;}


}








