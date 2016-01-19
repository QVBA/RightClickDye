package com.github.QVBA.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class PlayerRightClick {
	
	@SubscribeEvent
	public void onEvent(PlayerInteractEvent event) {
		EntityPlayer player = event.entityPlayer;
		ItemStack itemUse = player.getHeldItem();
		if(itemUse == null || itemUse.getItem() != Items.dye) return;
		Block clickedBlock = event.world.getBlock(event.x, event.y, event.z);
		if(clickedBlock != Blocks.wool) return;
		
		ItemDye dye = (ItemDye)itemUse.getItem();
		BlockColored block = (BlockColored) clickedBlock;
		for(ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
			block.recolourBlock(event.world, event.x, event.y, event.z, side, BlockColored.func_150032_b(dye.getDamage(itemUse)));
		}
		event.entityPlayer.inventory.consumeInventoryItem(dye);
	}
	

}
