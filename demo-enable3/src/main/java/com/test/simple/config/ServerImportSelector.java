package com.test.simple.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class ServerImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        Map<String,Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
        Server.Type type = (Server.Type) annotationAttributes.get("type");
        String[] importClassNames = new String[0];

        switch (type){
            case FTp:
                importClassNames = new String[]{FTPServer.class.getName()};
                break;
            case HTTP:
                importClassNames = new String[]{HTTPServer.class.getName()};
                break;

        }
        return importClassNames;
    }
}
