/*******************************************************************************
 * ItemEggFemale.java
 * Copyright (c) 2014 WildBamaBoy.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package mca.item;

import mca.api.VillagerRegistryMCA;
import mca.entity.EntityVillagerAdult;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

/**
 * Defines what an Female Spawner Egg is and how it behaves.
 */
public class ItemEggFemale extends Item
{
	/**
	 * Constructor
	 * 
	 * @param 	id	The item's ID.
	 */
	public ItemEggFemale(int itemId)
	{
		super(itemId);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("SpawnFemaleVillager");
	}

	/**
	 * Called when the player right clicks a block while holding this item.
	 * 
	 * @param	itemStack	The item stack that the player was holding when they right clicked.
	 * @param	player		The player that right clicked.
	 * @param	world		The world that the player right clicked in.
	 * @param	posX		X coordinate of the block that the player right clicked.
	 * @param	posY		Y coordinate of the block that the player right clicked.
	 * @param	posZ		Z coordinate of the block that the player right clicked.
	 * @param	meta		Metadata associated with the block clicked.
	 * @param	xOffset		X offset of the point where the block was clicked.
	 * @param	yOffset		Y offset of the point where the block was clicked.
	 * @param	zOffset		Z offset of the point where the block was clicked.
	 * 
	 * @return	True or false depending on if placing the item into the world was successful.
	 */
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int meta, float xOffset, float yOffset, float zOffset)
	{
		if (world.isRemote)
		{
			return true;
		}

		final int itemId = world.getBlockId(posX, posY, posZ);
		double verticalOffset = 0.0D;

		posX += Facing.offsetsXForSide[meta];
		posY += Facing.offsetsYForSide[meta];
		posZ += Facing.offsetsZForSide[meta];

		if (meta == 1 && itemId == Block.fence.blockID || itemId == Block.netherFence.blockID)
		{
			verticalOffset = 0.5D;
		}

		if (spawnVillager(world, posX + 0.5D, posY + verticalOffset, posZ + 0.5D) && !player.capabilities.isCreativeMode)
		{
			itemStack.stackSize--;
		}

		return true;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("mca:EggFemale");
	}

	/**
	 * Spawns a villager into the world.
	 * 
	 * @param 	world		The world that the villager is being spawned in.
	 * @param 	posX		X coordinates that the player clicked.
	 * @param 	posY		Y coordinates that the player clicked.
	 * @param 	posZ		Z coordinates that the player clicked.
	 * 
	 * @return	True or false depending on if placing the villager into the world was successful.
	 */
	public static boolean spawnVillager(World world, double posX, double posY, double posZ)
	{
		if (world.isRemote)
		{	
			return true;
		}

		else
		{
			final EntityVillagerAdult entityVillager = new EntityVillagerAdult(world, false, world.rand.nextInt(VillagerRegistryMCA.getNumberOfRegisteredVillagers() - 1));
			entityVillager.setLocationAndAngles(posX, posY, posZ, world.rand.nextFloat() * 360F, 0.0F);

			if (!world.isRemote)
			{
				world.spawnEntityInWorld(entityVillager);
			}

			return true;
		}
	}
}