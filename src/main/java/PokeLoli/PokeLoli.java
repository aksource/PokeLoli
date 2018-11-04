package PokeLoli;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PokeLoli.MOD_ID,
        name = PokeLoli.MOD_NAME,
        version = PokeLoli.MOD_VERSION,
        dependencies = PokeLoli.MOD_DEPENDENCIES,
        useMetadata = true,
        acceptedMinecraftVersions = PokeLoli.MOD_MC_VERSION)
public class PokeLoli {
    public static final String MOD_ID = "pokeloli";
    public static final String MOD_NAME = "PokeLoli";
    public static final String MOD_VERSION = "@VERSION@";
    public static final String MOD_DEPENDENCIES = "required-after:forge";
    public static final String MOD_MC_VERSION = "[1.12,1.99.99]";

    public static Item POKE_EGG = (new plItemMobEgg()).setUnlocalizedName("monsterPlacer");
    ;

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void preInit(FMLPreInitializationEvent event) {
        // 登録処理ハンドラクラスをイベントバスに登録
        MinecraftForge.EVENT_BUS.register(new RegistrationUtils());
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void load(FMLInitializationEvent event) {
        if (event.getSide().isClient()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(POKE_EGG, 0, new ModelResourceLocation(MOD_ID + ":" + "monster_placer_custom", "inventory"));
        }
    }
}