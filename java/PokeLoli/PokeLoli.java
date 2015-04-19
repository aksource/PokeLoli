package PokeLoli;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid="PokeLoli", name="PokeLoli", version="@VERSION@",dependencies="required-after:FML", useMetadata = true)
public class PokeLoli
{
	@Mod.Instance("PokeLoli")
	public static PokeLoli instance;
	public static Item pokeEgg;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		pokeEgg = (new plItemMobegg()).setUnlocalizedName("monsterPlacer").setTextureName("spawn_egg");
		GameRegistry.registerItem(pokeEgg, "monster_placer_custom");
	}
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
//		MinecraftForge.EVENT_BUS.register(new PokeLoliEventHandler());
		GameRegistry.addShapelessRecipe(new ItemStack(pokeEgg, 1, 0), Items.egg, Items.redstone);
	}
}