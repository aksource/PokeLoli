package PokeLoli;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class PokeLoliEventHandler
{
	@SubscribeEvent
	public void EntityInteractEvent(EntityInteractEvent event)
	{
		if(event.target instanceof EntityLivingBase)
		{
			EntityPlayer player = event.entityPlayer;
			EntityLivingBase living = (EntityLivingBase) event.target;
			if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemMonsterPlacer)
			{
				ItemMonsterPlacer item = (ItemMonsterPlacer) player.getCurrentEquippedItem().getItem();
				item.itemInteractionForEntity(player.getCurrentEquippedItem(), player, living);
			}
		}
	}
}