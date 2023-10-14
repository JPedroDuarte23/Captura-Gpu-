package org.example.gui.components;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.util.List;

public class Gpu {
    private SystemInfo systemInfo;
    private HardwareAbstractionLayer hardware;
    private OperatingSystem os;
    private List<GraphicsCard> listasGpus;

    public Gpu() {
        this.systemInfo = new SystemInfo();
        this.hardware = systemInfo.getHardware();
        this.os = systemInfo.getOperatingSystem();
    }
    public List<GraphicsCard> getListaGPU (){
        listasGpus = hardware.getGraphicsCards();
        return listasGpus;
    }
}
