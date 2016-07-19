package PokeLoli;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.translation.I18n;

import java.util.List;

public class plItemMobegg extends ItemMonsterPlacer {
    public plItemMobegg() {
        super();
        this.setRegistryName("monster_placer_custom");
    }

    @Override
    public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
        if (par1ItemStack.getItemDamage() == 0) {
            return ("" + I18n.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
        }
        return super.getUnlocalizedNameInefficiently(par1ItemStack);
    }

/*    @Override
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return 0xFFFFFF;
    }*/

    //Entityを右クリックした時に呼ばれるメソッド．なぜか呼ばれない
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target.worldObj.isRemote) {
            return false;
        }
        if (stack != null && stack.getItemDamage() == 0 && target != null) {
            String entityID = EntityList.getEntityString(target);
            if (!EntityList.ENTITY_EGGS.containsKey(entityID)) {
                return false;
            }
            target.setDead();
            ItemStack newEgg = new ItemStack(Items.SPAWN_EGG, 1, 0);
            newEgg.setTagCompound(new NBTTagCompound());
            newEgg.getTagCompound().setTag("EntityTag", new NBTTagCompound());
            newEgg.getTagCompound().getCompoundTag("EntityTag").setString("id", entityID);
            if (--stack.stackSize <= 0) {
                stack = null;
            }

            if (target.entityDropItem(newEgg, 1.0F) != null)
                return true;
        }
        return false;
    }

     //左クリックでも動作するように．こちらは動く．
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target,
                             EntityLivingBase attacker) {
        this.itemInteractionForEntity(stack, (EntityPlayer) attacker, target, EnumHand.MAIN_HAND);
        return true;
    }

    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }
}
