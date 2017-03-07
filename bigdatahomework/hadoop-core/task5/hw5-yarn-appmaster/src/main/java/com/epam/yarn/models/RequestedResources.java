package com.epam.yarn.models;

import lombok.ToString;

/**
 * @author Anton_Solovev
 * @since 10/5/16.
 */
@ToString
public class RequestedResources {

    private int containerCount;
    private int memory;
    private int VCores;
    private int containerPriority;

    public RequestedResources(int containerCount, int memory, int VCores, int containerPriority) {
        this.containerCount = containerCount;
        this.memory = memory;
        this.VCores = VCores;
        this.containerPriority = containerPriority;
    }

    public int getMemory() {
        return memory;
    }

    public int getVCores() {
        return VCores;
    }

    public int getContainerPriority() {
        return containerPriority;
    }

    public int getContainerCount() {
        return containerCount;
    }

}
