package me.ibhh.BookShop.BookHandler;

import java.util.List;
import me.ibhh.BookShop.InvalidBookException;
import me.ibhh.BookShop.Tools.Tools;
import me.ibhh.BookShop.intern.BukkitBuildNOTSupportedException;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a CraftWrittenBook
 */
public class BookHandlerUtility {

    private BookHandler132 handler132;
    private BookHandler145 handler145;
    private BookHandler146 handler146;
    private BookHandler147 handler147;
    private BookHandler15 handler15;
    private BookHandler151 handler151;

    public BookHandlerUtility(String title, String author, List<String> pages, int selled) throws InvalidBookException {
        if (Tools.packagesExists(
                "net.minecraft.server.v1_4_6.Item",
                "net.minecraft.server.v1_4_5.NBTTagCompound",
                "net.minecraft.server.v1_4_5.NBTTagList",
                "net.minecraft.server.v1_4_5.NBTTagString",
                "org.bukkit.craftbukkit.v1_4_5.inventory.CraftItemStack")) {
            handler145 = new BookHandler145(title, author, pages, selled);
        } else if (Tools.packagesExists(
                "net.minecraft.server.NBTTagCompound",
                "net.minecraft.server.NBTTagList",
                "net.minecraft.server.NBTTagString",
                "org.bukkit.craftbukkit.inventory.CraftItemStack")) {
            handler132 = new BookHandler132(title, author, pages, selled);
        } else if (Tools.packagesExists(
                "net.minecraft.server.v1_4_6.Item",
                "net.minecraft.server.v1_4_6.NBTTagCompound",
                "net.minecraft.server.v1_4_6.NBTTagList",
                "net.minecraft.server.v1_4_6.NBTTagString",
                "org.bukkit.craftbukkit.v1_4_6.inventory.CraftItemStack")) {
            handler146 = new BookHandler146(title, author, pages, selled);
        } else if (Tools.packagesExists(
                "net.minecraft.server.v1_4_R1.Item",
                "net.minecraft.server.v1_4_R1.NBTTagCompound",
                "net.minecraft.server.v1_4_R1.NBTTagList",
                "net.minecraft.server.v1_4_R1.NBTTagString",
                "org.bukkit.craftbukkit.v1_4_R1.inventory.CraftItemStack")) {
            handler147 = new BookHandler147(title, author, pages, selled);
        } else if (Tools.packagesExists(
                "net.minecraft.server.v1_5_R1.Item",
                "net.minecraft.server.v1_5_R1.NBTTagCompound",
                "net.minecraft.server.v1_5_R1.NBTTagList",
                "net.minecraft.server.v1_5_R1.NBTTagString",
                "org.bukkit.craftbukkit.v1_5_R1.inventory.CraftItemStack")) {
            handler15 = new BookHandler15(title, author, pages, selled);
        } else if (Tools.packagesExists(
                "net.minecraft.server.v1_5_R2.Item",
                "net.minecraft.server.v1_5_R2.NBTTagCompound",
                "net.minecraft.server.v1_5_R2.NBTTagList",
                "net.minecraft.server.v1_5_R2.NBTTagString",
                "org.bukkit.craftbukkit.v1_5_R2.inventory.CraftItemStack")) {
            handler151 = new BookHandler151(title, author, pages, selled);
        }

    }

    public BookHandlerUtility(ItemStack itemStack) throws InvalidBookException {
        if (Tools.packagesExists(
                "net.minecraft.server.v1_4_5.ItemWrittenBook",
                "net.minecraft.server.v1_4_5.NBTTagCompound",
                "net.minecraft.server.v1_4_5.NBTTagList",
                "net.minecraft.server.v1_4_5.NBTTagString",
                "org.bukkit.craftbukkit.v1_4_5.inventory.CraftItemStack")) {
            handler145 = new BookHandler145(itemStack);
        } else if (Tools.packagesExists(
                "net.minecraft.server.NBTTagCompound",
                "net.minecraft.server.NBTTagList",
                "net.minecraft.server.NBTTagString",
                "org.bukkit.craftbukkit.inventory.CraftItemStack")) {
            handler132 = new BookHandler132(itemStack);
        } else if (Tools.packagesExists(
                "net.minecraft.server.v1_4_6.ItemWrittenBook",
                "net.minecraft.server.v1_4_6.NBTTagCompound",
                "net.minecraft.server.v1_4_6.NBTTagList",
                "net.minecraft.server.v1_4_6.NBTTagString",
                "org.bukkit.craftbukkit.v1_4_6.inventory.CraftItemStack")) {
            handler146 = new BookHandler146(itemStack);
        } else if (Tools.packagesExists(
                "net.minecraft.server.v1_4_R1.ItemWrittenBook",
                "net.minecraft.server.v1_4_R1.NBTTagCompound",
                "net.minecraft.server.v1_4_R1.NBTTagList",
                "net.minecraft.server.v1_4_R1.NBTTagString",
                "org.bukkit.craftbukkit.v1_4_R1.inventory.CraftItemStack")) {
            handler147 = new BookHandler147(itemStack);
        } else if (Tools.packagesExists(
                "net.minecraft.server.v1_5_R1.Item",
                "net.minecraft.server.v1_5_R1.NBTTagCompound",
                "net.minecraft.server.v1_5_R1.NBTTagList",
                "net.minecraft.server.v1_5_R1.NBTTagString",
                "org.bukkit.craftbukkit.v1_5_R1.inventory.CraftItemStack")) {
            handler15 = new BookHandler15(itemStack);
        } else if (Tools.packagesExists(
                "net.minecraft.server.v1_5_R2.Item",
                "net.minecraft.server.v1_5_R2.NBTTagCompound",
                "net.minecraft.server.v1_5_R2.NBTTagList",
                "net.minecraft.server.v1_5_R2.NBTTagString",
                "org.bukkit.craftbukkit.v1_5_R2.inventory.CraftItemStack")) {
            handler151 = new BookHandler151(itemStack);
        }
    }

    public BookHandler getBookHandler() throws BukkitBuildNOTSupportedException {
        if (handler132 != null) {
            return handler132;
        } else if (handler145 != null) {
            return handler145;
        } else if (handler146 != null) {
            return handler146;
        } else if (handler147 != null) {
            return handler147;
        } else if (handler15 != null) {
            return handler15;
        } else if (handler151 != null) {
            return handler151;
        } else {
            throw new BukkitBuildNOTSupportedException("Your bukkit version is NOT supported, or an error occured!");
        }
    }
//
//    public void setBookHandler(BookHandler handler) {
//        if(BookShop.getRawBukkitVersion().equalsIgnoreCase("1.4.5")){
//            handler145 = (BookHandler145) handler;
//        } else if(BookShop.getRawBukkitVersion().equalsIgnoreCase("1.3")){
//            handler132 = (BookHandler132) handler;
//        }
//    }
}