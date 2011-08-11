package me.babarix.MoveInventory.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DoubleChest {
	Chest c1 = null;
	Chest c2 = null;

	boolean dc = false;

	Inventory ic1;
	Inventory ic2;

	int size;

	public DoubleChest(Block c1) {
		this.c1 = (Chest) c1;
		ic1 = this.c1.getInventory();
		size = ic1.getSize();

		c2 = GetDoubleChest(c1);
		if (c2 != null) {
			dc = true;
			ic2 = c2.getInventory();
			size += ic2.getSize();
		}

	}

	private Chest GetDoubleChest(Block block) {
		Chest chest = null;
		if (block.getRelative(BlockFace.NORTH).getTypeId() == 54) {
			chest = (Chest) block.getRelative(BlockFace.NORTH).getState();
			return chest;
		} else if (block.getRelative(BlockFace.EAST).getTypeId() == 54) {
			chest = (Chest) block.getRelative(BlockFace.EAST).getState();
			return chest;
		} else if (block.getRelative(BlockFace.SOUTH).getTypeId() == 54) {
			chest = (Chest) block.getRelative(BlockFace.SOUTH).getState();
			return chest;
		} else if (block.getRelative(BlockFace.WEST).getTypeId() == 54) {
			chest = (Chest) block.getRelative(BlockFace.WEST).getState();
			return chest;
		}
		return chest;
	}

	public int getSize() {
		return size;
	}

	// KEEP
	public HashMap<Integer, ItemStack> addItem(ItemStack... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void clear() {
		ic1.clear();
		if (dc) {
			ic2.clear();
		}
	}

	public int firstEmpty() {
		int ret = -1;
		if (ic1.firstEmpty() != -1) {
			ret = ic1.firstEmpty();
		} else if (dc) {
			if (ic2.firstEmpty() != -1) {
				ret = ic2.firstEmpty();
			}
		}
		return ret;
	}

	public ItemStack[] getContents() {
	ItemStack[] ret = new ItemStack[size];
		List<ItemStack> lret = new ArrayList<ItemStack>();
		for (ItemStack item : ic1.getContents()) {
			lret.add(item);
		}
		if (dc) {
			for (ItemStack item : ic2.getContents()) {
				lret.add(item);
			}
		}
		ret = lret.toArray(ret);
		return ret;
	}

	// KEEP
	public HashMap<Integer, ItemStack> removeItem(ItemStack... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
