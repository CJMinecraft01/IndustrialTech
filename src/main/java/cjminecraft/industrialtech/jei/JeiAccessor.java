//package cjminecraft.industrialtech.jei;
//
//import javax.annotation.Nonnull;
//
//public class JeiAccessor {
//	
//	static boolean jeiRuntimeAvailable = false;
//	
//	public static boolean isJeiRuntimeAvailable() {
//		return jeiRuntimeAvailable;
//	}
//	
//	public static void setFilterText(@Nonnull String filterText) {
//		if(jeiRuntimeAvailable) {
//			IndustrialTechPlugin.setFilterText(filterText);
//		}
//	}
//	
//	public static @Nonnull String getFilterText() {
//		if(jeiRuntimeAvailable) {
//			return IndustrialTechPlugin.getFilterText();
//		}
//		return "";
//	}
//
//}
