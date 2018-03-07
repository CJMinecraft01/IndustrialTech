package cjminecraft.industrialtech.init;

import cjminecraft.industrialtech.blocks.BlockPhotonGenerator;
import cjminecraft.industrialtech.utils.registries.annotations.RegisterBlock;
import cjminecraft.industrialtech.utils.registries.annotations.RegisterItem;
import cjminecraft.industrialtech.utils.registries.annotations.RegisterItemBlock;
import net.minecraft.block.Block;

public class ITBlocks {

    @RegisterBlock(registryName = "photon_generator")
    @RegisterItemBlock(registryName = "photon_generator")
    public static BlockPhotonGenerator PHOTON_GENERATOR;

}
