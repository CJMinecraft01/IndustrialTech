package cjminecraft.industrialtech.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {
	
	public static enum IngotTypes implements IStringSerializable {
		
		COPPER("copper", 0),
		SILVER("silver", 1),
		TIN("tin", 2),
		ZINC("zinc", 3),
		LEAD("lead", 4),
		NICKEL("nickel", 5);
		
	    private String name;
		private int ID;
		
	    private IngotTypes(String name, int ID) {
	        this.name = name;
	        this.ID = ID;
	    }
	    
	    @Override
	    public String getName() {
	        return name;
	    }

	    public int getID() {
	        return ID;
	    }
	    
	    @Override
	    public String toString() {
	        return getName();
	    }
	    
	}
	
	public static enum CompressedTypes implements IStringSerializable {
		
		SINGLE_COMPRESSED("single_compressed", 0),
		DOUBLE_COMPRESSED("double_compressed", 1),
		TRIPLE_COMPRESSED("triple_compressed", 2),
		QUADRUPLE_COMPRESSED("quadruple_compressed", 3),
		PENTUPLE_COMPRESSED("pentuple_compressed", 4),
		SEXTUPLE_COMPRESSED("sextuple_compressed", 5),
		SEPTUPLE_COMPRESSED("septuple_compressed", 6),
		OCTUPLE_COMPRESSED("octuple_compressed", 7);
		
		private String name;
		private int ID;
		
	    private CompressedTypes(String name, int ID) {
	        this.name = name;
	        this.ID = ID;
	    }
	    
	    @Override
	    public String getName() {
	        return name;
	    }

	    public int getID() {
	        return ID;
	    }
	    
	    @Override
	    public String toString() {
	        return getName();
	    }
	}
	
	public static enum GeneratorTypes implements IStringSerializable {

		FURNACE("furnace", 0),
		FOOD("food", 1),
		REDSTONE("redstone", 2);
		
		private String name;
		private int ID;
		
		private GeneratorTypes(String name, int ID) {
			this.name = name;
			this.ID = ID;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		public int getID() {
			return ID;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
	}
	
	public static enum ChipTypes implements IStringSerializable {
		
		BASIC("basic", 0),
		ADVANCED("advanced", 1);
		
		private String name;
		private int ID;
		
		private ChipTypes(String name, int ID) {
			this.name = name;
			this.ID = ID;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		public int getID() {
			return ID;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
	}
	
	public static enum ConnectionTypes implements IStringSerializable {
		NONE("none", 0),
		PIPE("pipe", 1),
		BLOCK("block", 2);
		
		private String name;
		private int ID;
		
		private ConnectionTypes(String name, int ID) {
			this.name = name;
			this.ID = ID;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		public int getID() {
			return ID;
		}
		
		@Override
		public String toString() {
			return getName();
		}
	}

}
