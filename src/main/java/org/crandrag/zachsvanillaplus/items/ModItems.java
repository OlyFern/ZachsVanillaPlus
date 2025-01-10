package org.crandrag.zachsvanillaplus.items;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.crandrag.zachsvanillaplus.main;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(main.MODID);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
