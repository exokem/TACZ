package com.tacz.guns.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public enum ExtAmmo
{
    light_cartridge(64),
    rifle_cartridge(64),
    precision_cartridge(32),
    amr_cartridge(16),
    high_explosive_cartridge(8),
    shotgun_shell(16)
    ;

    public final String identifier = name().toLowerCase();

    private final int stackSize;

    private RegistryObject<Item> item;

    ExtAmmo(int stackSize)
    {
        this.stackSize = stackSize;
    }

    public Item getItem()
    {
        return this.item.get();
    }

    public ItemStack getStack()
    {
        return new ItemStack(this.getItem());
    }

    public RegistryObject<Item> register(DeferredRegister<Item> items)
    {
        this.item = items.register(this.identifier, () -> new ExtAmmoItem(this.identifier, this.stackSize));
        return this.item;
    }
}
