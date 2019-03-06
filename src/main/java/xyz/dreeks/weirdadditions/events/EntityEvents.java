package xyz.dreeks.weirdadditions.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.dreeks.weirdadditions.WeirdAdditions;
import xyz.dreeks.weirdadditions.capabilities.IHasSpore;
import xyz.dreeks.weirdadditions.utils.Constants;

import java.util.Random;

import static xyz.dreeks.weirdadditions.capabilities.HasSporeProvider.SPORE_CAPA;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class EntityEvents {

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent ejwe) {
        if (!ejwe.getWorld().isRemote) {
            Entity e = ejwe.getEntity();
            if (e instanceof EntityCow) {
                IHasSpore capa = e.getCapability(SPORE_CAPA, EnumFacing.DOWN);

                if (capa.getMaxSpore() == 0) {
                    // @Todo find a better random
                    Random r = new Random();
                    capa.setMaxSpore(r.nextInt(WeirdAdditions.config.MAX_SPORE_PER_COW));
                    capa.setAmountLeft(capa.getMaxSpore());
                }
            }
        }
    }
}
