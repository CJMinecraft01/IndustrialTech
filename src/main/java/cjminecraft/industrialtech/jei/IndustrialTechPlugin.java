//package cjminecraft.industrialtech.jei;
//
//import javax.annotation.Nonnull;
//
//import mezz.jei.api.BlankModPlugin;
//import mezz.jei.api.IGuiHelper;
//import mezz.jei.api.IItemRegistry;
//import mezz.jei.api.IJeiHelpers;
//import mezz.jei.api.IJeiRuntime;
//import mezz.jei.api.IModRegistry;
//import mezz.jei.api.JEIPlugin;
//
//@JEIPlugin
//public class IndustrialTechPlugin extends BlankModPlugin {
//
//	private static IJeiRuntime jeiRuntime = null;
//	
//	@Override
//	public void register(@Nonnull IModRegistry registry) {
//		IItemRegistry itemRegistry = registry.getItemRegistry();
//		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
//		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
//		
//		GeneratorRecipeCatagory.register(registry, guiHelper);
//	}
//	
//	@Override
//	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
//		IndustrialTechPlugin.jeiRuntime = jeiRuntime;
//		
//	}
//	
//	public static void setFilterText(@Nonnull String filterText) {
//		jeiRuntime.getItemListOverlay().setFilterText(filterText);
//	}
//	
//	public static @Nonnull String getFilterText() {
//		return jeiRuntime.getItemListOverlay().getFilterText();
//	}
//	
//}
