package de.jonas.customentities;

import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;

public class Custom_entitiesLoader implements PluginLoader{

    @Override
    public void classloader(PluginClasspathBuilder classpathBuilder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();
        resolver.addRepository(new RemoteRepository.Builder(
            "MavenCentralLoader",
            "default",
            "https://repo1.maven.org/maven2/"
        ).build());
        resolver.addDependency(new Dependency(
            new DefaultArtifact("dev.jorel:commandapi-bukkit-shade-mojang-mapped:9.5.1"),
            null));                
        classpathBuilder.addLibrary(resolver);
    }

}

