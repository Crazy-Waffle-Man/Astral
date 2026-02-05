package io.cwm.engine.helpers;

import java.io.InputStream;
import java.util.Objects;

public class ResourceLocator {
    private final String path;
    public static final ResourceLocator MISSING_TEXTURE = new ResourceLocator("assets/textures/missing.png");
    public ResourceLocator(String resource) {
        this.path = resource.startsWith("/") ? resource : "/" + resource;
    }

    public String getPath() {
        return path;
    }

    public boolean equals(ResourceLocator resourceLocator) {
        return Objects.equals(this.getPath(), resourceLocator.getPath());
    }

    public InputStream getResourceAsStream() {
        return getClass().getResourceAsStream(getPath());
    }
}
