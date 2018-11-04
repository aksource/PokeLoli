package PokeLoli;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;

public class plItemMobEgg extends ItemMonsterPlacer {
    public plItemMobEgg() {
        super();
        this.setRegistryName("monster_placer_custom");
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecated")
    public String getUnlocalizedNameInefficiently(@Nonnull ItemStack itemStack) {
        if (itemStack.getItemDamage() == 0) {
            return ("" + I18n.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
        }
        return super.getUnlocalizedNameInefficiently(itemStack);
    }

    //Entityを右クリックした時に呼ばれるメソッド．なぜか呼ばれない
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target.getEntityWorld().isRemote) {
            return false;
        }
        if (stack != null && stack.getItemDamage() == 0) {
            ResourceLocation entityID = EntityList.getKey(target);
            if (entityID == null || !EntityList.ENTITY_EGGS.containsKey(entityID)) {
                return false;
            }
            target.setDead();
            ItemStack newEgg = new ItemStack(Items.SPAWN_EGG, 1, 0);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("EntityTag", new NBTTagCompound());
            nbt.getCompoundTag("EntityTag").setString("id", entityID.toString());
            newEgg.setTagCompound(nbt);
            stack.shrink(1);
            return target.entityDropItem(newEgg, 1.0F) != null;
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
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (CreativeTabs.MISC.equals(tab)) {
            items.add(new ItemStack(this, 1, 0));
        }
    }
}
