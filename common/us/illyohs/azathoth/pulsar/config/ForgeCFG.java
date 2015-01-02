/**
 *  Copyright (c) 2014, Runix-Minecraft
 *  All rights reserved.
 *  
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  
 *  * Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *    
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *  
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *  DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *  CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package us.illyohs.azathoth.pulsar.config;

import java.io.File;

import us.illyohs.azathoth.pulsar.pulse.PulseMeta;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

/**
 * Azathoth specific pulsar addon class to support using the forge CFG format for configurations
 * @author progwml6
 * 
 * Note: this class was written for <a href="https://github.com/SlimeKnights/Mantle/">Mantel</a> 
 * and reused in Azathoth
 * @implementation LordIllyohs 
 */
public class ForgeCFG implements IConfiguration{

    private static Configuration config;
    private final String confPath;
    private final String description;

    /**
     * Creates a new Configuration object.
     *
     * Do NOT make this the same as the overall mod configuration; it will clobber it!
     *
     * @param confName The config file name (without path or .cfg suffix)
     * @param description The description for the group that the config entries will be placed in.
     */
    public ForgeCFG(String confName, String description) {
        this.confPath = Loader.instance().getConfigDir().toString() + File.separator + confName + ".cfg";
        this.description = description;
    }

    @Override
    public void load() {
        config = new Configuration(new File(confPath));
        config.load();
    }

    @Override
    public boolean isModuleEnabled(PulseMeta meta) {
        return config.get(description, meta.getId(), meta.isEnabled(), meta.getDescription()).getBoolean(meta.isEnabled());
    }

    @Override
    public void flush() {
        if(config.hasChanged())
            config.save();
    }

}