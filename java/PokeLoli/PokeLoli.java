package PokeLoli;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid="PokeLoli", name="PokeLoli", version="@VERSION@",dependencies="required-after:FML", useMetadata = true)
public class PokeLoli
{
    public static final String MOD_ID = "PokeLoli";
	@Mod.Instance("PokeLoli")
	public static PokeLoli instance;
	public static Item pokeEgg;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		pokeEgg = (new plItemMobegg()).setUnlocalizedName("monsterPlacer")/*.setTextureName("spawn_egg")*/;
		GameRegistry.registerItem(pokeEgg, "monster_placer_custom");
//        if (event.getSide().isClient()) {
//            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(pokeEgg, 0, new ModelResourceLocation(MOD_ID + ":" + "monster_placer_custom", "inventory"));
//        }
	}
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
        if (event.getSide().isClient()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(pokeEgg, 0, new ModelResourceLocation(MOD_ID + ":" + "monster_placer_custom", "inventory"));
        }
//		MinecraftForge.EVENT_BUS.register(new PokeLoliEventHandler());
		GameRegistry.addShapelessRecipe(new ItemStack(pokeEgg, 1, 0), Items.egg, Items.redstone);
	}
}