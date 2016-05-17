package com.epam.homework_6_1.consumers;

import com.epam.homework_6_1.chaches.annotations.InjectCache;
import com.epam.homework_6_1.chaches.interfaces.ICache;

public class Consumer {
    @InjectCache(name = "integer")
    ICache ICache;

}
