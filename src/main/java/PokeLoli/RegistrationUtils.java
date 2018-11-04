package PokeLoli;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

import static PokeLoli.PokeLoli.POKE_EGG;

/**
 * Utility for registration
 * Created by A.K. on 2018/01/29.
 */
public class RegistrationUtils {

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(POKE_EGG);
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistry<IRecipe> registry = event.getRegistry();
        registry.register(new ShapelessOreRecipe(POKE_EGG.getRegistryName(), new ItemStack(POKE_EGG, 1, 0), Items.EGG, Items.REDSTONE).setRegistryName(POKE_EGG.getRegistryName()));
    }
}
