package com.mysite.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "runmodesConfigs")
public @interface runmodesconfigstest {
    @AttributeDefinition(name = "cc1")
    int cc1() default 0;

    @AttributeDefinition(name = "cc2")
    int cc2() default 0;
}
