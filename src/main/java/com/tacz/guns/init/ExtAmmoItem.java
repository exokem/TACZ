package com.tacz.guns.init;

import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.IAmmo;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.resource.index.CommonGunIndex;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Optional;

public class ExtAmmoItem extends Item implements IAmmo
{
    private final String name;
    private final ResourceLocation id;

    public ExtAmmoItem(String name, int stackSize)
    {
        super(new Properties().stacksTo(stackSize));
        this.name = name;
        this.id = new ResourceLocation("tacz", name);
    }

    @Override
    public ResourceLocation getAmmoId(ItemStack itemStack)
    {
        return id;
    }

    @Override
    public void setAmmoId(ItemStack itemStack, @Nullable ResourceLocation resourceLocation)
    {

    }

    @Override
    public boolean isAmmoOfGun(ItemStack gun, ItemStack ammo)
    {
        if (gun.getItem() instanceof IGun iGun && ammo.getItem() instanceof IAmmo iAmmo) {
            ResourceLocation gunId = iGun.getGunId(gun);
            ResourceLocation ammoId = iAmmo.getAmmoId(ammo);

            Optional<CommonGunIndex> opt = TimelessAPI.getCommonGunIndex(gunId);

            if (opt.isEmpty())
            {
                return false;
            }

            CommonGunIndex index = opt.get();

            return TimelessAPI.getCommonGunIndex(gunId).map(gunIndex -> gunIndex.getGunData().getAmmoId().equals(ammoId)).orElse(false);
        }
        return false;
    }
}
