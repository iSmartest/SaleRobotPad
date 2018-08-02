package com.freeintelligence.robotclient.base;


import com.freeintelligence.robotclient.net.HttpFactroy;
import com.freeintelligence.robotclient.net.IHttp;

public interface BaseModel {
    public static IHttp iHttp = HttpFactroy.create();

}
