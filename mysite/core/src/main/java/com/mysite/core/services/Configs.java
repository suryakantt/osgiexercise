package com.mysite.core.services;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="my site configs")
public @interface Configs {

    @AttributeDefinition(name = "my class limit", required = true)
    int limit() default 3;


    @AttributeDefinition(name = "my passing marks", required = true)
    int passMarks() default 36;
}
