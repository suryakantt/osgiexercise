package com.mysite.core.services;

import java.util.List;

public interface ConfigsInterface {

    boolean isClassLimitReached(List<student> studentList);
    int getPassingMarks();
}
