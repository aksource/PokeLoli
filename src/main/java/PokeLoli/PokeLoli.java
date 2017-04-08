package PokeLoli;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
    public static final String MOD_MC_VERSION = "[1.11,1.99.99]";

    public static Item pokeEgg;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        pokeEgg = (new plItemMobegg()).setUnlocalizedName("monsterPlacer");
        GameRegistry.register(pokeEgg);
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        if (event.getSide().isClient()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(pokeEgg, 0, new ModelResourceLocation(MOD_ID + ":" + "monster_placer_custom", "inventory"));
        }
        GameRegistry.addShapelessRecipe(new ItemStack(pokeEgg, 1, 0), Items.EGG, Items.REDSTONE);
    }
}