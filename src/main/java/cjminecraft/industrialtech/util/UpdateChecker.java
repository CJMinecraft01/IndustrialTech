package cjminecraft.industrialtech.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import cjminecraft.industrialtech.IndustrialTech;
import cjminecraft.industrialtech.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UpdateChecker {

	@SideOnly(Side.CLIENT)
	public static void checkForUpdates(EntityPlayer player) {
		URL updateURL = null;
		BufferedReader in = null;
		String version = "";
		try {
			updateURL = new URL("https://www.dropbox.com/s/3rp5clrf62l03g6/industrialtechupdater.txt?dl=1");
			in = new BufferedReader(new InputStreamReader(updateURL.openStream()));
			version = in.readLine();
		} catch (Exception e) {
			player.addChatComponentMessage(new TextComponentString(TextFormatting.RED + IndustrialTech.lang.localize("update.connection_failed")));
		} finally {
			if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    Utils.getLogger().info(e.getStackTrace());
                }
            }
		}
		if(!version.contains(Reference.VERSION)) {
			player.addChatComponentMessage(new TextComponentString(TextFormatting.RED + IndustrialTech.lang.localize("update.outofdate")));
		}
	}

}
