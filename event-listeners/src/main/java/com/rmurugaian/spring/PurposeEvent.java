package com.rmurugaian.spring;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Value
public class PurposeEvent {
    SomeUpdater someUpdater;
    int i;

}
