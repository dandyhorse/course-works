package com.epam.yarn.appmaster;

import com.epam.yarn.models.RequestedResources;
import com.epam.yarn.web.EmbeddedServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.api.records.impl.pb.PriorityPBImpl;
import org.apache.hadoop.yarn.api.records.impl.pb.ResourcePBImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.yarn.am.AbstractEventingAppmaster;
import org.springframework.yarn.am.YarnAppmaster;
import org.springframework.yarn.am.allocate.AbstractAllocator;

import java.util.ArrayList;
import java.util.List;

@ComponentScan("com.epam.yarn.web")
@EnableAutoConfiguration
public class Appmaster extends AbstractEventingAppmaster implements YarnAppmaster {

    private static final Log log = LogFactory.getLog(Appmaster.class);
    @Autowired
    private EmbeddedServer server;
    private volatile boolean isAppStarted;
    private volatile RequestedResources res;

    @Override
    protected void onInit() throws Exception {
        super.onInit();
        server.start();
        log.debug("Appmaster initialized");
    }

    @Override
    public void submitApplication() {
        log.debug("Submitting  application");
    }

    public void submitApplication(RequestedResources res) {
        this.res = res;
        registerAppmaster();
        start();
        if (getAllocator() instanceof AbstractAllocator) {
            ((AbstractAllocator) getAllocator()).setApplicationAttemptId(getApplicationAttemptId());
        }
        getAllocator().allocateContainers(res.getContainerCount());
        isAppStarted = true;
    }

    @Override
    protected void onContainerAllocated(Container container) {
        log.info(res);
        final List<String> commands = new ArrayList<>(getCommands());
        commands.add("-Xmx" + res.getMemory() + "m");
        commands.add("-Xms" + (res.getMemory() / 2) + "m");
        Resource resource = new ResourcePBImpl();
        resource.setMemory(res.getMemory());
        resource.setVirtualCores(res.getVCores());
        container.setResource(resource);
        container.setPriority(PriorityPBImpl.newInstance(res.getContainerPriority()));
        getLauncher().launchContainer(container, getCommands());
    }

    @Override
    protected void doStop() {
        if (isAppStarted) {
            log.debug("stopping appmaster");
            super.doStop();
        }
        isAppStarted = false;
        server.stop();
    }
}
