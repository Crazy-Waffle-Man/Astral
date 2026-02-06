package io.cwm.engine.resources;

import java.io.InputStream;

public class ResourceLocator {
    private String resourceLocation;

    public ResourceLocator(String path) {
        if (path.charAt(0) != '/') {
            path = "/" + path;
        }
        resourceLocation = path;
        if (getResource() == null) {
            resourceLocation = null;
        }
    }
    public String getResourceLocation() {return resourceLocation;}
    public InputStream getResource() {
        try {
            return ResourceLocator.class.getResourceAsStream(resourceLocation);
        }
        catch (NullPointerException e) {
            return null; // Implement different handling for different resource types
        }
    }
}
