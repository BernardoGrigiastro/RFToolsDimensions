package mcjty.rftoolsdim.config;

import mcjty.lib.varia.EntityTools;
import mcjty.lib.varia.Logging;
import mcjty.rftoolsdim.dimensions.description.MobDescriptor;
import mcjty.rftoolsdim.varia.GenericTools;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class MobConfiguration {
    public static final String CATEGORY_MOBS = "mobs";

    public static final Map<String,MobDescriptor> mobClasses = new HashMap<>();
    public static int chance, mingroup, maxgroup, maxentity;

    public static void init(Configuration cfg) {
        chance = cfg.get(CATEGORY_MOBS, "default.chance", 6).getInt();
        mingroup = cfg.get(CATEGORY_MOBS, "default.mingroup", 1).getInt();
        maxgroup = cfg.get(CATEGORY_MOBS, "default.maxgroup", 3).getInt();
        maxentity = cfg.get(CATEGORY_MOBS, "default.maxentity", 10).getInt();

        initMobItem(cfg, "Zombie", 100, 8, 8, 60);
        initMobItem(cfg, "Skeleton", 100, 8, 8, 60);
        initMobItem(cfg, "Enderman", 20, 2, 4, 20);
        initMobItem(cfg, "Blaze", 20, 2, 4, 20);
        initMobItem(cfg, "Creeper", 100, 8, 8, 60);
        initMobItem(cfg, "CaveSpider", 100, 8, 8, 60);
        initMobItem(cfg, "Ghast", 20, 2, 4, 20);
        initMobItem(cfg, "VillagerGolem", 20, 1, 2, 6);
        initMobItem(cfg, "LavaSlime", 50, 2, 4, 30);
        initMobItem(cfg, "PigZombie", 20, 2, 4, 10);
        initMobItem(cfg, "Slime", 50, 2, 4, 30);
        initMobItem(cfg, "SnowMan", 50, 2, 4, 30);
        initMobItem(cfg, "Spider", 100, 8, 8, 60);
        initMobItem(cfg, "Witch", 10, 1, 1, 20);
        initMobItem(cfg, "Bat", 10, 8, 8, 20);
        initMobItem(cfg, "Endermite", 6, 2, 4, 10);
        initMobItem(cfg, "Silverfish", 6, 2, 4, 10);
        initMobItem(cfg, "Rabbit", 10, 3, 4, 20);
        initMobItem(cfg, "Chicken", 10, 3, 4, 40);
        initMobItem(cfg, "Cow", 10, 3, 4, 40);
        initMobItem(cfg, "Horse", 10, 3, 4, 40);
        initMobItem(cfg, "MushroomCow", 10, 3, 4, 40);
        initMobItem(cfg, "Ozelot", 5, 2, 3, 20);
        initMobItem(cfg, "Pig", 10, 3, 4, 40);
        initMobItem(cfg, "Sheep", 10, 3, 4, 40);
        initMobItem(cfg, "Squid", 10, 3, 4, 40);
        initMobItem(cfg, "Wolf", 10, 3, 4, 20);
        initMobItem(cfg, "Villager", 10, 3, 4, 20);
        initMobItem(cfg, "WitherBoss", 5, 1, 2, 5);
        initMobItem(cfg, "Guardian", 8, 1, 3, 7);
        initMobItem(cfg, "EnderDragon", 4, 1, 2, 4);
    }

    public static Class<? extends EntityLiving> getEntityClass(String id) {
        EntityEntry entry = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(id));
        return entry == null ? null : GenericTools.castClass(entry.getEntityClass(), EntityLiving.class);
    }

    // Accepts an old-style (1.10) entity ID or a new-style string representation of a resourcelocation
    private static void initMobItem(Configuration cfg, String name,
                                    int chance, int mingroup, int maxgroup, int maxentity) {
        String id = EntityTools.fixEntityId(name);
        Class<? extends EntityLiving> entityClass = getEntityClass(id);
        if (entityClass == null) {
            Logging.logError("Cannot find mob with id '" + id +"'!");
            return;
        }
        if (cfg != null) {
            chance = cfg.get(CATEGORY_MOBS, id + ".chance", chance).getInt();
            mingroup = cfg.get(CATEGORY_MOBS, id + ".mingroup", mingroup).getInt();
            maxgroup = cfg.get(CATEGORY_MOBS, id + ".maxgroup", maxgroup).getInt();
            maxentity = cfg.get(CATEGORY_MOBS, id + ".maxentity", maxentity).getInt();
        }
        mobClasses.put(id, new MobDescriptor(entityClass, chance, mingroup, maxgroup, maxentity));
    }

}
