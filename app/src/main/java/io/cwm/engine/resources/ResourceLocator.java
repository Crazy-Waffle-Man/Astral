package io.cwm.engine.resources;

import java.io.InputStream;

public class ResourceLocator {
    private String resourceLocation;

    public ResourceLocator(String path) {
        new ResourceLocator(path, false);
    }

    public ResourceLocator(String path, boolean safely) {
        resourceLocation = path;
        if (getResource() == null) {
            if (!safely) {throw new IllegalArgumentException("Missing file: "+resourceLocation);}
            else {resourceLocation = null;}
        }
    }
    public String getResourceLocation() {return resourceLocation;}
    public InputStream getResource() {
        return ResourceLocator.class.getResourceAsStream(resourceLocation);
    }
}
