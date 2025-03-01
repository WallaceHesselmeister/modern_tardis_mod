package com.code.tama.mtm.DataGen;

import com.code.tama.mtm.Blocks.MBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.code.tama.mtm.mtm.MODID;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
//        this.tag(ItemTags.TRIMMABLE_ARMOR)
//                .add(MItems.SAPPHIRE_HELMET.get(),
//                        MItems.SAPPHIRE_CHESTPLATE.get(),
//                        MItems.SAPPHIRE_LEGGINGS.get(),
//                        MItems.SAPPHIRE_BOOTS.get());

//        this.tag(ItemTags.MUSIC_DISCS)
//                .add(MItems.BAR_BRAWL_MUSIC_DISC.get());

//        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS)
//                .add(MItems.BAR_BRAWL_MUSIC_DISC.get());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(MBlocks.GALLIFREYAN_OAK_LOG.get().asItem())
                .add(MBlocks.GALLIFREYAN_OAK_WOOD.get().asItem())
                .add(MBlocks.STRIPPED_GALLIFREYAN_OAK_LOG.get().asItem())
                .add(MBlocks.STRIPPED_GALLIFREYAN_OAK_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(MBlocks.GALLIFREYAN_OAK_PLANKS.get().asItem());
    }
}