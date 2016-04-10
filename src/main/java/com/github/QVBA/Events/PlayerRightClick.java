package com.github.QVBA.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class PlayerRightClick {
	
	@SubscribeEvent
	public void onEvent(PlayerInteractEvent event) {
		
		int x = event.x;
		int y = event.y;
		int z = event.z;
		EntityPlayer player = event.entityPlayer;
		ItemStack itemUse = player.getHeldItem();
		Block clickedBlock = event.world.getBlock(event.x, event.y, event.z);
		World world = event.world;
		
		if(itemUse == null || itemUse.getItem() != Items.dye) return;
		if(clickedBlock != Blocks.wool && clickedBlock != Blocks.carpet && clickedBlock != Blocks.stained_glass) return;
		
		int color = BlockColored.func_150032_b(((ItemDye)itemUse.getItem()).getDamage(itemUse));
		int meta = world.getBlockMetadata(x, y, z);
		if(meta != color) {
			world.setBlockMetadataWithNotify(x, y, z, color, 3);
			player.inventory.consumeInventoryItem(itemUse.getItem());
		}
		
		
	}
}
